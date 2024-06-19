package com.example.movielistapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.movielistapp.database.MovieViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private MovieViewModel movieViewModel;
    SetOnDownloadListener listener;

    public MainViewModelFactory(Application application, MovieViewModel movieViewModel, SetOnDownloadListener listener) {
        this.application = application;
        this.movieViewModel = movieViewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(application, movieViewModel, listener);
    }
}
