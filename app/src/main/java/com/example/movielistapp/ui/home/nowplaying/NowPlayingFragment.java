package com.example.movielistapp.ui.home.nowplaying;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movielistapp.database.MovieViewModel;
import com.example.movielistapp.database.NowPlaying;
import com.example.movielistapp.databinding.FragmentNowPlayingBinding;
import com.example.movielistapp.ui.adapters.NowPlayingRecyclerViewAdapter;
import com.example.movielistapp.ui.adapters.OnNowPlayingClickListener;
import com.example.movielistapp.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingFragment extends Fragment {

    FragmentNowPlayingBinding binding;
    MovieViewModel movieViewModel;
    NowPlayingRecyclerViewAdapter adapter;

    public static final String OBJECT_TYPE_VALUE = "now_playing";

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    public static NowPlayingFragment newInstance() {

        NowPlayingFragment fragment = new NowPlayingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false);

        adapter = new NowPlayingRecyclerViewAdapter(new ArrayList<>(), new OnNowPlayingClickListener() {
            @Override
            public void onNowPlayingClick(NowPlaying nowPlaying) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.OBJECT_TYPE_NAME, OBJECT_TYPE_VALUE);
                intent.putExtra(OBJECT_TYPE_VALUE, nowPlaying);
                startActivity(intent);
            }
        }, getActivity());

        binding.nowPlayingRv.setAdapter(adapter);
        binding.nowPlayingRv.setHasFixedSize(true);
        binding.nowPlayingRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFromDatabase();

    }

    public void getFromDatabase(){
        movieViewModel.getAllNowPlayingMovies().observe(getActivity(), new Observer<List<NowPlaying>>() {
            @Override
            public void onChanged(List<NowPlaying> nowPlayings) {
                adapter.setMovieModels(nowPlayings);
            }
        });
    }
}