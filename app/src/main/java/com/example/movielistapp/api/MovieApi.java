package com.example.movielistapp.api;

import com.example.movielistapp.pojo.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/";
    String API_KEY = "54bb58ba0cea8a00fb1f35368c36de04";
    @GET("movie/now_playing")
    public Call<MovieModel> getNowPlaying(@Query("api_key") String api_key);
    @GET("movie/popular")
    public Call<MovieModel> getPopular(@Query("api_key") String api_key);
    @GET("movie/top_rated")
    public Call<MovieModel> getTopRated(@Query("api_key") String api_key);
    @GET("movie/upcoming")
    public Call<MovieModel> getUpcoming(@Query("api_key") String api_key);
    @GET("search/movie")
    public Call<MovieModel> searchForMovies(@Query("query") String query, @Query("api_key") String api_key);
}
