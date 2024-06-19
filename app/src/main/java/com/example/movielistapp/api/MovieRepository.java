package com.example.movielistapp.api;

import com.example.movielistapp.pojo.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    public static void getNowPlaying(OnReceivedMovieListener listener){
        Call<MovieModel> call = MovieRetrofitClient.getInstance()
                .getMovieApi().getNowPlaying(MovieApi.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                listener.onReceivedMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                listener.onReceivedMovieFailure(t.getMessage());
            }
        });
    }

    public static void getPopular(OnReceivedMovieListener listener){
        Call<MovieModel> call = MovieRetrofitClient.getInstance()
                .getMovieApi().getPopular(MovieApi.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                listener.onReceivedMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                listener.onReceivedMovieFailure(t.getMessage());
            }
        });

    }

    public static void getTopRated(OnReceivedMovieListener listener){
        Call<MovieModel> call = MovieRetrofitClient.getInstance()
                .getMovieApi().getTopRated(MovieApi.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                listener.onReceivedMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                listener.onReceivedMovieFailure(t.getMessage());
            }
        });

    }

    public static void getUpcoming(OnReceivedMovieListener listener){
        Call<MovieModel> call = MovieRetrofitClient.getInstance()
                .getMovieApi().getUpcoming(MovieApi.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                listener.onReceivedMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                listener.onReceivedMovieFailure(t.getMessage());
            }
        });

    }

    public static void searchForMovies(String search_query, OnReceivedMovieListener listener){
        Call<MovieModel> call = MovieRetrofitClient.getInstance()
                .getMovieApi().searchForMovies(search_query, MovieApi.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                listener.onReceivedMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                listener.onReceivedMovieFailure(t.getMessage());
            }
        });
    }

    public static String getImageBaseUrl(){
        return MovieApi.IMAGE_BASE_URL;
    }
}
