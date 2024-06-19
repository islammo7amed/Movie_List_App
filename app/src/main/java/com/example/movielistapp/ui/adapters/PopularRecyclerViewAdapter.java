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
import com.example.movielistapp.database.Popular;
import com.example.movielistapp.databinding.MovieItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularRecyclerViewAdapter extends RecyclerView.Adapter<PopularRecyclerViewAdapter.PopularViewHolder> {

    List<Popular> populars;
    OnPopularClickListener listener;
    Activity activity;

    public PopularRecyclerViewAdapter(List<Popular> populars, OnPopularClickListener listener, Activity activity) {
        this.populars = populars;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Popular popular = populars.get(position);
        holder.bind(popular);
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }

    public void setMovieModels(List<Popular> populars) {
        this.populars = populars;
        notifyDataSetChanged();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder{

        MovieItemBinding binding;
        Popular popular;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPopularClick(popular);
                }
            });
        }

        public void bind(Popular popular) {
            this.popular = popular;
            binding.itemProgress.setVisibility(View.VISIBLE);
            binding.itemError.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(MovieRepository.getImageBaseUrl() + popular.getPoster_path()).into(binding.movieItemIv, new Callback() {
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
