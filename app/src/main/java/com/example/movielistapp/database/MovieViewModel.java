package com.example.movielistapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieDatabaseRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDatabaseRepository(application);
    }


    // -- NowPlaying Methods -- //


    public void insertNowPlayingMovie(NowPlaying movie){
        repository.insertNowPlayingMovie(movie);
    }

    public void updateNowPlayingMovie(NowPlaying movie){
        repository.updateNowPlayingMovie(movie);
    }

    public void deleteNowPlayingMovie(NowPlaying movie){
        repository.deleteNowPlayingMovie(movie);
    }

    public void deleteAllNowPlayingMovies(){
        repository.deleteAllNowPlayingMovies();
    }

    public LiveData<List<NowPlaying>> getAllNowPlayingMovies(){
        return repository.getAllNowPlayingMovies();
    }

    public LiveData<List<NowPlaying>> getNowPlayingMovieById(long id){
        return repository.getNowPlayingMovieById(id);
    }


    // -- Popular Methods -- //


    public void insertPopularMovie(Popular movie){
        repository.insertPopularMovie(movie);
    }

    public void updatePopularMovie(Popular movie){
        repository.updatePopularMovie(movie);
    }

    public void deletePopularMovie(Popular movie){
        repository.deletePopularMovie(movie);
    }

    public void deleteAllPopularMovies(){
        repository.deleteAllPopularMovies();
    }

    public LiveData<List<Popular>> getAllPopularMovies(){
        return repository.getAllPopularMovies();
    }

    public LiveData<List<Popular>> getPopularMovieById(long id){
        return repository.getPopularMovieById(id);
    }


    // -- TopRated Methods -- //


    public void insertTopRatedMovie(TopRated movie){
        repository.insertTopRatedMovie(movie);
    }

    public void updateTopRatedMovie(TopRated movie){
        repository.updateTopRatedMovie(movie);
    }

    public void deleteTopRatedMovie(TopRated movie){
        repository.deleteTopRatedMovie(movie);
    }

    public void deleteAllTopRatedMovies(){
        repository.deleteAllTopRatedMovies();
    }

    public LiveData<List<TopRated>> getAllTopRatedMovies(){
        return repository.getAllTopRatedMovies();
    }

    public LiveData<List<TopRated>> getTopRatedMovieById(long id){
        return repository.getTopRatedMovieById(id);
    }


    // -- Upcoming Methods -- //


    public void insertUpcomingMovie(Upcoming movie){
        repository.insertUpcomingMovie(movie);
    }

    public void updateUpcomingMovie(Upcoming movie){
        repository.updateUpcomingMovie(movie);
    }

    public void deleteUpcomingMovie(Upcoming movie){
        repository.deleteUpcomingMovie(movie);
    }

    public void deleteAllUpcomingMovies(){
        repository.deleteAllUpcomingMovies();
    }

    public LiveData<List<Upcoming>> getAllUpcomingMovies(){
        return repository.getAllUpcomingMovies();
    }

    public  LiveData<List<Upcoming>> getUpcomingMovieById(long id){
        return repository.getUpcomingMovieById(id);
    }
}
