package com.example.movielistapp.api;

import com.example.movielistapp.pojo.MovieModel;

public interface OnReceivedMovieListener {
    void onReceivedMovie(MovieModel movieModel);
    void onReceivedMovieFailure(String message);
}
