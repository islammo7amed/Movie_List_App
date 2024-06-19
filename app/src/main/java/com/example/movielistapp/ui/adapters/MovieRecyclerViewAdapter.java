package com.example.movielistapp.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielistapp.R;
import com.example.movielistapp.api.MovieRepository;
import com.example.movielistapp.databinding.MovieItemBinding;
import com.example.movielistapp.pojo.MovieResultModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    List<MovieResultModel> movieResultModels;
    OnMovieClickListener listener;
    Activity activity;

    public MovieRecyclerViewAdapter(List<MovieResultModel> movieResultModels, OnMovieClickListener listener, Activity activity){
        this.movieResultModels = movieResultModels;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResultModel movieResultModel = movieResultModels.get(position);
        holder.bind(movieResultModel);

    }

    @Override
    public int getItemCount() {
        return movieResultModels.size();
    }

    public void setMovieResultModels(List<MovieResultModel> movieResultModels) {
        this.movieResultModels = movieResultModels;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        MovieItemBinding binding;
        MovieResultModel movieResultModel;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMovieClick(movieResultModel);
                }
            });
        }

        public void bind(MovieResultModel movieResultModel) {
            this.movieResultModel = movieResultModel;
            binding.itemProgress.setVisibility(View.VISIBLE);
            binding.itemError.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(MovieRepository.getImageBaseUrl() + movieResultModel.getPoster_path()).into(binding.movieItemIv, new Callback() {
                                @Override
                                public void onSuccess() {
                                    binding.itemProgress.setVisibility(View.GONE);
                                    binding.itemError.setVisibility(View.GONE);
                                    binding.movieItemIv.setScaleType(ImageView.ScaleType.FIT_XY);
                                }

                                @Override
                                public void onError(Exception e) {
                                    binding.movieItemIv.setImageResource(R.drawable.baseline_error);
                                    binding.movieItemIv.setScaleType(ImageView.ScaleType.CENTER);
                                    binding.itemError.setVisibility(View.VISIBLE);
                                    binding.itemProgress.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
            }).start();
        }

    }
}
