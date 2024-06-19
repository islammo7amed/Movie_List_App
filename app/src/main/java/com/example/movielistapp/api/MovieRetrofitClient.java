package com.example.movielistapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitClient {
    private static MovieRetrofitClient instance = null;
    private MovieApi movieApi;

    private MovieRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieApi = retrofit.create(MovieApi.class);
    }

    public static synchronized MovieRetrofitClient getInstance(){
        if (instance == null)
            instance = new MovieRetrofitClient();
        return instance;
    }

    public MovieApi getMovieApi(){
        return movieApi;
    }
}
