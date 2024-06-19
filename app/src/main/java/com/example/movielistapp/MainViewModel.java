package com.example.movielistapp;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.movielistapp.api.MovieRepository;
import com.example.movielistapp.api.OnReceivedMovieListener;
import com.example.movielistapp.database.MovieViewModel;
import com.example.movielistapp.database.NowPlaying;
import com.example.movielistapp.database.Popular;
import com.example.movielistapp.database.TopRated;
import com.example.movielistapp.database.Upcoming;
import com.example.movielistapp.pojo.MovieModel;
import com.example.movielistapp.pojo.MovieResultModel;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MovieViewModel movieViewModel;
    MainHandler handler;
    SetOnDownloadListener listener;

    private static int var = 0;
    public MainViewModel(@NonNull Application application, MovieViewModel movieViewModel, SetOnDownloadListener listener) {
        super(application);
        this.movieViewModel = movieViewModel;
        this.listener = listener;
        handler = new MainHandler();
    }

    private synchronized int setVar(){
        var++;
        return var;
    }

    public void getNowPlayingFromApi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MovieRepository.getNowPlaying(new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        List<MovieResultModel> movieResultModels = movieModel.getResults();
                        for (MovieResultModel model : movieResultModels){
                            NowPlaying nowPlaying = new NowPlaying(model.getId(),model.getOverview(),model.getPoster_path(),model.getTitle(),model.getVote_average(),model.getVote_count());
                            movieViewModel.insertNowPlayingMovie(nowPlaying);
                        }
                        handler.sendEmptyMessage(setVar());
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {

                    }
                });
            }
        }).start();
    }

    public void getPopularFromApi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MovieRepository.getPopular(new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        List<MovieResultModel> movieResultModels = movieModel.getResults();
                        for (MovieResultModel model : movieResultModels){
                            Popular popular = new Popular(model.getId(),model.getOverview(),model.getPoster_path(),model.getTitle(),model.getVote_average(),model.getVote_count());
                            movieViewModel.insertPopularMovie(popular);
                        }
                        handler.sendEmptyMessage(setVar());
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {

                    }
                });
            }
        }).start();
    }

    public void getTopRatedFromApi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MovieRepository.getTopRated(new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        List<MovieResultModel> movieResultModels = movieModel.getResults();
                        for (MovieResultModel model : movieResultModels){
                            TopRated topRated = new TopRated(model.getId(),model.getOverview(),model.getPoster_path(),model.getTitle(),model.getVote_average(),model.getVote_count());
                            movieViewModel.insertTopRatedMovie(topRated);
                        }
                        handler.sendEmptyMessage(setVar());
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {

                    }
                });
            }
        }).start();
    }

    public void getUpcomingFromApi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MovieRepository.getUpcoming(new OnReceivedMovieListener() {
                    @Override
                    public void onReceivedMovie(MovieModel movieModel) {
                        List<MovieResultModel> movieResultModels = movieModel.getResults();
                        for (MovieResultModel model : movieResultModels){
                            Upcoming upcoming = new Upcoming(model.getId(),model.getOverview(),model.getPoster_path(),model.getTitle(),model.getVote_average(),model.getVote_count());
                            movieViewModel.insertUpcomingMovie(upcoming);
                        }
                        handler.sendEmptyMessage(setVar());
                    }

                    @Override
                    public void onReceivedMovieFailure(String message) {

                    }
                });
            }
        }).start();
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4)
                listener.onDownload();
        }
    }
}
