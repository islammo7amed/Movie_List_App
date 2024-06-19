package com.example.movielistapp.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.movielistapp.api.MovieRepository;
import com.example.movielistapp.api.OnReceivedMovieListener;
import com.example.movielistapp.pojo.MovieModel;

public class SearchViewModel extends AndroidViewModel {

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMoviesFromApi(String query, OnReceivedMovieListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MovieRepository.searchForMovies(query, new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        listener.onReceivedMovie(movieModel);
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {
                        listener.onReceivedMovieFailure(message);
                    }
                });
            }
        }).start();
    }
}
