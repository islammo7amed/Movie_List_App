package com.example.movielistapp.ui.workers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.movielistapp.MainActivity;
import com.example.movielistapp.database.MovieDatabaseRepository;

public class MovieWorker extends Worker {
    public MovieWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        MovieDatabaseRepository repository = new MovieDatabaseRepository((Application) getApplicationContext());
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int num = sharedPreferences.getInt(MainActivity.CACHE_KEY, 0);
        if (num == 0){
            num++;
            editor.putInt(MainActivity.CACHE_KEY, num);
            editor.apply();
        }else {
            repository.deleteAllNowPlayingMovies();
            repository.deleteAllPopularMovies();
            repository.deleteAllTopRatedMovies();
            repository.deleteAllUpcomingMovies();
        }

        return Result.success();
    }
}
