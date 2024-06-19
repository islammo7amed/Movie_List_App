package com.example.movielistapp.ui.details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.movielistapp.R;
import com.example.movielistapp.api.MovieRepository;
import com.example.movielistapp.database.NowPlaying;
import com.example.movielistapp.database.Popular;
import com.example.movielistapp.database.TopRated;
import com.example.movielistapp.database.Upcoming;
import com.example.movielistapp.databinding.ActivityDetailsBinding;
import com.example.movielistapp.pojo.MovieResultModel;
import com.example.movielistapp.ui.home.nowplaying.NowPlayingFragment;
import com.example.movielistapp.ui.home.popular.PopularFragment;
import com.example.movielistapp.ui.home.toprated.TopRatedFragment;
import com.example.movielistapp.ui.home.upcoming.UpcomingFragment;
import com.example.movielistapp.ui.search.SearchActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    public static final String OBJECT_TYPE_NAME = "object_type";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String object_type = intent.getStringExtra(OBJECT_TYPE_NAME);


        switch (object_type){
            case NowPlayingFragment.OBJECT_TYPE_VALUE :
                NowPlaying nowPlaying = (NowPlaying) intent.getSerializableExtra(NowPlayingFragment.OBJECT_TYPE_VALUE);
                bind(nowPlaying.getTitle(), String.valueOf(nowPlaying.getVote_average()), nowPlaying.getOverview());
                getImageFromApi(nowPlaying.getPoster_path());
                break;
            case  PopularFragment.OBJECT_TYPE_VALUE:
                Popular popular = (Popular) intent.getSerializableExtra(PopularFragment.OBJECT_TYPE_VALUE);
                bind(popular.getTitle(), String.valueOf(popular.getVote_average()), popular.getOverview());
                getImageFromApi(popular.getPoster_path());
                break;
            case TopRatedFragment.OBJECT_TYPE_VALUE :
                TopRated topRated = (TopRated) intent.getSerializableExtra(TopRatedFragment.OBJECT_TYPE_VALUE);
                bind(topRated.getTitle(), String.valueOf(topRated.getVote_average()), topRated.getOverview());
                getImageFromApi(topRated.getPoster_path());
                break;
            case UpcomingFragment.OBJECT_TYPE_VALUE :
                Upcoming upcoming = (Upcoming) intent.getSerializableExtra(UpcomingFragment.OBJECT_TYPE_VALUE);
                bind(upcoming.getTitle(), String.valueOf(upcoming.getVote_average()), upcoming.getOverview());
                getImageFromApi(upcoming.getPoster_path());
                break;
            case SearchActivity.OBJECT_TYPE_VALUE :
                MovieResultModel movieResultModel = (MovieResultModel) intent.getSerializableExtra(SearchActivity.OBJECT_TYPE_VALUE);
                bind(movieResultModel.getTitle(), String.valueOf(movieResultModel.getVote_average()), movieResultModel.getOverview());
                getImageFromApi(movieResultModel.getPoster_path());
        }
    }

    public void getImageFromApi(String poster_path){
        binding.detailsProgress.setVisibility(View.VISIBLE);
        binding.detailsError.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(MovieRepository.getImageBaseUrl() + poster_path).into(binding.detailsMovieIv, new Callback() {
                            @Override
                            public void onSuccess() {
                                binding.detailsProgress.setVisibility(View.GONE);
                                binding.detailsError.setVisibility(View.GONE);
                                binding.detailsMovieIv.setScaleType(ImageView.ScaleType.FIT_XY);
                            }

                            @Override
                            public void onError(Exception e) {
                                binding.detailsMovieIv.setImageResource(R.drawable.baseline_error);
                                binding.detailsMovieIv.setScaleType(ImageView.ScaleType.CENTER);
                                binding.detailsError.setVisibility(View.VISIBLE);
                                binding.detailsProgress.setVisibility(View.GONE);
                            }
                        });
                    }
                });
            }
        }).start();
    }

    public void bind(String title, String vote, String overview){
        float v = (Float.valueOf(vote))/5;
        binding.detailsMovieTitle.setText(title);
        binding.vote.setRating(Float.valueOf(v));
        binding.detailsMovieOverview.setText(overview);
    }
}