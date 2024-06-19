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
import com.example.movielistapp.database.TopRated;
import com.example.movielistapp.databinding.MovieItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TopRatedRecyclerViewAdapter extends RecyclerView.Adapter<TopRatedRecyclerViewAdapter.TopRatedViewHolder> {

    List<TopRated> topRateds;
    OnTopRatedClickListener listener;
    Activity activity;

    public TopRatedRecyclerViewAdapter(List<TopRated> topRateds, OnTopRatedClickListener listener, Activity activity) {
        this.topRateds = topRateds;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopRatedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedViewHolder holder, int position) {
        TopRated topRated = topRateds.get(position);
        holder.bind(topRated);
    }

    @Override
    public int getItemCount() {
        return topRateds.size();
    }

    public void setTopRateds(List<TopRated> topRateds) {
        this.topRateds = topRateds;
        notifyDataSetChanged();
    }

    class TopRatedViewHolder extends RecyclerView.ViewHolder {

        MovieItemBinding binding;
        TopRated topRated;

        public TopRatedViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTopRatedClick(topRated);
                }
            });
        }

        public void bind(TopRated topRated) {
            this.topRated = topRated;
            binding.itemProgress.setVisibility(View.VISIBLE);
            binding.itemError.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(MovieRepository.getImageBaseUrl() + topRated.getPoster_path()).into(binding.movieItemIv, new Callback() {
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
