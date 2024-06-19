package com.example.movielistapp.ui.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.movielistapp.NetworkInformation;
import com.example.movielistapp.api.OnReceivedMovieListener;
import com.example.movielistapp.databinding.ActivitySearchBinding;
import com.example.movielistapp.pojo.MovieModel;
import com.example.movielistapp.pojo.MovieResultModel;
import com.example.movielistapp.ui.adapters.MovieRecyclerViewAdapter;
import com.example.movielistapp.ui.adapters.OnMovieClickListener;
import com.example.movielistapp.ui.details.DetailsActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    MovieRecyclerViewAdapter adapter;
    SearchViewModel searchViewModel;
    public static final String OBJECT_TYPE_VALUE = "movie_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        adapter = new MovieRecyclerViewAdapter(new ArrayList<>(), new OnMovieClickListener() {
            @Override
            public void onMovieClick(MovieResultModel model) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.OBJECT_TYPE_NAME, OBJECT_TYPE_VALUE);
                intent.putExtra(OBJECT_TYPE_VALUE, model);
                startActivity(intent);
            }
        }, this);


        binding.searchRv.setAdapter(adapter);
        binding.searchRv.setHasFixedSize(true);
        binding.searchRv.setLayoutManager(new GridLayoutManager(this, 2));

        if (NetworkInformation.isNetworkAvailable(this)){
            Intent intent = getIntent();
            if(Intent.ACTION_SEARCH.equals(intent.getAction())){
                String query = intent.getStringExtra(SearchManager.QUERY);
                searchViewModel.getMoviesFromApi(query, new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        adapter.setMovieResultModels(movieModel.getResults());
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {
                        Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{
            NetworkInformation.networkErrorDialog(this);
        }
    }
}