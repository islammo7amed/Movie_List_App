package com.example.movielistapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopRatedDao {
    @Insert
    void insertTopRatedMovie(TopRated movie);
    @Update
    void updateTopRatedMovie(TopRated movie);
    @Delete
    void deleteTopRatedMovie(TopRated movie);
    @Query("DELETE FROM top_rated")
    void deleteAllTopRatedMovies();
    @Query("SELECT * FROM top_rated")
    LiveData<List<TopRated>> getAllTopRatedMovies();
    @Query("SELECT * FROM top_rated WHERE id = :id")
    LiveData<List<TopRated>> getTopRatedMovieById(long id);
}
