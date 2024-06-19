package com.example.movielistapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NowPlayingDao {
    @Insert
    void insertNowPlayingMovie(NowPlaying movie);
    @Update
    void updateNowPlayingMovie(NowPlaying movie);
    @Delete
    void deleteNowPlayingMovie(NowPlaying movie);
    @Query("DELETE FROM now_playing")
    void deleteAllNowPlayingMovies();
    @Query("SELECT * FROM now_playing")
    LiveData<List<NowPlaying>> getAllNowPlayingMovies();
    @Query("SELECT * FROM now_playing WHERE id = :id")
    LiveData<List<NowPlaying>> getNowPlayingMovieById(long id);
}
