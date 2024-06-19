package com.example.movielistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.example.movielistapp.database.MovieViewModel;
import com.example.movielistapp.database.NowPlaying;
import com.example.movielistapp.databinding.ActivityMainBinding;
import com.example.movielistapp.ui.adapters.MoviePagerAdapter;
import com.example.movielistapp.ui.home.nowplaying.NowPlayingFragment;
import com.example.movielistapp.ui.home.popular.PopularFragment;
import com.example.movielistapp.ui.home.toprated.TopRatedFragment;
import com.example.movielistapp.ui.home.upcoming.UpcomingFragment;
import com.example.movielistapp.ui.search.SearchActivity;
import com.example.movielistapp.ui.workers.MovieWorker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<Fragment> movieFragments;
    List<String> tabs;
    MovieViewModel movieViewModel;
    MainViewModel mainViewModel;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public final static String CACHE = "cache";
    public final static String CACHE_KEY = "cache_key";
    public final static String ALARM_KEY = "alarm_key";
    public int cache_number = 0;
    public int set_alarm = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        binding.mainProgress.setVisibility(View.VISIBLE);

        sharedPreferences = getSharedPreferences(CACHE, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        cache_number = sharedPreferences.getInt(CACHE_KEY, 0);
        set_alarm = sharedPreferences.getInt(ALARM_KEY, 0);

        if (set_alarm == 0){
            setWork();
            set_alarm++;
            editor.putInt(ALARM_KEY, set_alarm);
            editor.apply();
        }

        movieFragments = new ArrayList<>();
        movieFragments.add(NowPlayingFragment.newInstance());
        movieFragments.add(PopularFragment.newInstance());
        movieFragments.add(TopRatedFragment.newInstance());
        movieFragments.add(UpcomingFragment.newInstance());

        tabs = new ArrayList<>();
        tabs.add("Now Playing");
        tabs.add("Popular");
        tabs.add("Top Rated");
        tabs.add("Upcoming");

        mainViewModel = new ViewModelProvider( this, new MainViewModelFactory(this.getApplication(), movieViewModel, new SetOnDownloadListener() {
            @Override
            public void onDownload() {
                binding.mainProgress.setVisibility(View.GONE);
            }
        })).get(MainViewModel.class);

        movieViewModel.getAllNowPlayingMovies().observe(this, new Observer<List<NowPlaying>>() {
            @Override
            public void onChanged(List<NowPlaying> nowPlayings) {
                if (nowPlayings.isEmpty()){
                    if (NetworkInformation.isNetworkAvailable(MainActivity.this)){
                        mainViewModel.getNowPlayingFromApi();
                        mainViewModel.getPopularFromApi();
                        mainViewModel.getTopRatedFromApi();
                        mainViewModel.getUpcomingFromApi();
                    }else {
                        binding.mainProgress.setVisibility(View.GONE);
                        NetworkInformation.networkErrorDialog(MainActivity.this);
                    }
                }else {
                    MoviePagerAdapter pagerAdapter = new MoviePagerAdapter(MainActivity.this, movieFragments);
                    binding.movieViewPager.setAdapter(pagerAdapter);

                    new TabLayoutMediator( binding.movieTaps, binding.movieViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            tab.setText(tabs.get(position));
                        }
                    }).attach();
                    binding.mainProgress.setVisibility(View.GONE);


                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.movie_search_menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        ComponentName name = new ComponentName(MainActivity.this,SearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(name));

        return true;
    }

    public void setWork(){
        WorkRequest workRequest = new PeriodicWorkRequest.Builder(MovieWorker.class, 4, TimeUnit.HOURS).build();
        WorkManager.getInstance(this).enqueue(workRequest);
    }
}

