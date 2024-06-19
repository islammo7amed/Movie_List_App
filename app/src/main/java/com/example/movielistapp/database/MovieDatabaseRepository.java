package com.example.movielistapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class MovieDatabaseRepository {
    NowPlayingDao nowPlayingDao;
    PopularDao popularDao;
    TopRatedDao topRatedDao;
    UpcomingDao upcomingDao;

    public MovieDatabaseRepository (Application application){
        MovieDatabase db = MovieDatabase.getDatabase(application);
        nowPlayingDao = db.nowPlayingDao();
        popularDao = db.popularDao();
        topRatedDao = db.topRatedDao();
        upcomingDao = db.upcomingDao();
    }


    // -- NowPlaying Methods -- //


    void insertNowPlayingMovie(NowPlaying movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                nowPlayingDao.insertNowPlayingMovie(movie);
            }
        });
    }

    void updateNowPlayingMovie(NowPlaying movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                nowPlayingDao.updateNowPlayingMovie(movie);
            }
        });
    }

    void deleteNowPlayingMovie(NowPlaying movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                nowPlayingDao.deleteNowPlayingMovie(movie);
            }
        });
    }

    public void deleteAllNowPlayingMovies(){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                nowPlayingDao.deleteAllNowPlayingMovies();
            }
        });
    }

    LiveData<List<NowPlaying>> getAllNowPlayingMovies(){
        return nowPlayingDao.getAllNowPlayingMovies();
    }

    LiveData<List<NowPlaying>> getNowPlayingMovieById(long id){
        return nowPlayingDao.getNowPlayingMovieById(id);
    }


    // -- Popular Methods -- //


    void insertPopularMovie(Popular movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                popularDao.insertPopularMovie(movie);
            }
        });
    }

    void updatePopularMovie(Popular movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                popularDao.updatePopularMovie(movie);
            }
        });
    }

    void deletePopularMovie(Popular movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                popularDao.deletePopularMovie(movie);
            }
        });
    }

    public void deleteAllPopularMovies(){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                popularDao.deleteAllPopularMovies();
            }
        });
    }

    LiveData<List<Popular>> getAllPopularMovies(){
        return popularDao.getAllPopularMovies();
    }

    LiveData<List<Popular>> getPopularMovieById(long id){
        return popularDao.getPopularMovieById(id);
    }


    // -- TopRated Methods -- //


    void insertTopRatedMovie(TopRated movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                topRatedDao.insertTopRatedMovie(movie);
            }
        });
    }

    void updateTopRatedMovie(TopRated movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                topRatedDao.updateTopRatedMovie(movie);
            }
        });
    }

    void deleteTopRatedMovie(TopRated movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                topRatedDao.deleteTopRatedMovie(movie);
            }
        });
    }

    public void deleteAllTopRatedMovies(){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                topRatedDao.deleteAllTopRatedMovies();
            }
        });
    }

    LiveData<List<TopRated>> getAllTopRatedMovies(){
        return topRatedDao.getAllTopRatedMovies();
    }

    LiveData<List<TopRated>> getTopRatedMovieById(long id){
        return topRatedDao.getTopRatedMovieById(id);
    }


    // -- Upcoming Methods -- //


    void insertUpcomingMovie(Upcoming movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                upcomingDao.insertUpcomingMovie(movie);
            }
        });
    }

    void updateUpcomingMovie(Upcoming movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                upcomingDao.updateUpcomingMovie(movie);
            }
        });
    }

    void deleteUpcomingMovie(Upcoming movie){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                upcomingDao.deleteUpcomingMovie(movie);
            }
        });
    }

    public void deleteAllUpcomingMovies(){
        MovieDatabase.dataBaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                upcomingDao.deleteAllUpcomingMovies();
            }
        });
    }

    LiveData<List<Upcoming>> getAllUpcomingMovies(){
        return upcomingDao.getAllUpcomingMovies();
    }

    LiveData<List<Upcoming>> getUpcomingMovieById(long id){
        return upcomingDao.getUpcomingMovieById(id);
    }
}
