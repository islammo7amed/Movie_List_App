package com.example.movielistapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UpcomingDao {
    @Insert
    void insertUpcomingMovie(Upcoming movie);
    @Update
    void updateUpcomingMovie(Upcoming movie);
    @Delete
    void deleteUpcomingMovie(Upcoming movie);
    @Query("DELETE FROM upcoming")
    void deleteAllUpcomingMovies();
    @Query("SELECT * FROM upcoming")
    LiveData<List<Upcoming>> getAllUpcomingMovies();
    @Query("SELECT * FROM upcoming WHERE id = :id")
    LiveData<List<Upcoming>> getUpcomingMovieById(long id);
}
