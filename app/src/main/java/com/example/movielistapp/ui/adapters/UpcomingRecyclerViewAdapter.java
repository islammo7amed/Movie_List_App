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
import com.example.movielistapp.database.MovieViewModel;
import com.example.movielistapp.database.Upcoming;
import com.example.movielistapp.databinding.MovieItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UpcomingRecyclerViewAdapter extends RecyclerView.Adapter<UpcomingRecyclerViewAdapter.UpcomingViewHolder> {


    List<Upcoming> upcomings;
    OnUpcomingClickListener listener;
    Activity activity;

    public UpcomingRecyclerViewAdapter(List<Upcoming> upcomings, OnUpcomingClickListener listener, MovieViewModel movieViewModel, Activity activity) {
        this.upcomings = upcomings;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpcomingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        Upcoming upcoming = upcomings.get(position);
        holder.bind(upcoming);
    }

    @Override
    public int getItemCount() {
        return upcomings.size();
    }

    public void setUpcomings(List<Upcoming> upcomings) {
        this.upcomings = upcomings;
        notifyDataSetChanged();
    }

    class UpcomingViewHolder extends RecyclerView.ViewHolder {

        MovieItemBinding binding;
        Upcoming upcoming;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUpcomingClick(upcoming);
                }
            });
        }

        public void bind(Upcoming upcoming) {
            this.upcoming = upcoming;
            binding.itemProgress.setVisibility(View.VISIBLE);
            binding.itemError.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(MovieRepository.getImageBaseUrl() + upcoming.getPoster_path()).into(binding.movieItemIv, new Callback() {
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
