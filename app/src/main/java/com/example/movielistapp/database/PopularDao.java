package com.example.movielistapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PopularDao {
    @Insert
    void insertPopularMovie(Popular movie);
    @Update
    void updatePopularMovie(Popular movie);
    @Delete
    void deletePopularMovie(Popular movie);
    @Query("DELETE FROM popular")
    void deleteAllPopularMovies();
    @Query("SELECT * FROM popular")
    LiveData<List<Popular>> getAllPopularMovies();
    @Query("SELECT * FROM popular WHERE id = :id")
    LiveData<List<Popular>> getPopularMovieById(long id);
}
