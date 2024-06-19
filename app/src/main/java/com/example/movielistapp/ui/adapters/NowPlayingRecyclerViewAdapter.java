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
import com.example.movielistapp.database.NowPlaying;
import com.example.movielistapp.databinding.MovieItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NowPlayingRecyclerViewAdapter extends RecyclerView.Adapter<NowPlayingRecyclerViewAdapter.NowPlayingViewHolder> {

    List<NowPlaying> nowPlayings;
    OnNowPlayingClickListener listener;
    Activity activity;

    public NowPlayingRecyclerViewAdapter(List<NowPlaying> nowPlayings, OnNowPlayingClickListener listener, Activity activity) {
        this.nowPlayings = nowPlayings;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NowPlayingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingViewHolder holder, int position) {
        NowPlaying nowPlaying = nowPlayings.get(position);
        holder.bind(nowPlaying);
    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public void setMovieModels(List<NowPlaying> nowPlayings) {
        this.nowPlayings = nowPlayings;
        notifyDataSetChanged();
    }

    class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        MovieItemBinding binding;
        NowPlaying nowPlaying;

        public NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNowPlayingClick(nowPlaying);
                }
            });
        }

        public void bind(NowPlaying nowPlaying) {
            this.nowPlaying = nowPlaying;
            binding.itemProgress.setVisibility(View.VISIBLE);
            binding.itemError.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(MovieRepository.getImageBaseUrl() + nowPlaying.getPoster_path()).into(binding.movieItemIv, new Callback() {
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
