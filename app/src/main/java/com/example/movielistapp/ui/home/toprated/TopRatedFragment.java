package com.example.movielistapp.ui.home.toprated;

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
import com.example.movielistapp.database.TopRated;
import com.example.movielistapp.databinding.FragmentTopRatedBinding;
import com.example.movielistapp.ui.adapters.OnTopRatedClickListener;
import com.example.movielistapp.ui.adapters.TopRatedRecyclerViewAdapter;
import com.example.movielistapp.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class TopRatedFragment extends Fragment {

    FragmentTopRatedBinding binding;
    MovieViewModel movieViewModel;
    TopRatedRecyclerViewAdapter adapter;

    public static final String OBJECT_TYPE_VALUE = "top_rated";

    public TopRatedFragment() {
        // Required empty public constructor
    }

    public static TopRatedFragment newInstance() {

        TopRatedFragment fragment = new TopRatedFragment();

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
        binding = FragmentTopRatedBinding.inflate(inflater, container, false);

        adapter = new TopRatedRecyclerViewAdapter(new ArrayList<>(), new OnTopRatedClickListener() {
            @Override
            public void onTopRatedClick(TopRated topRated) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.OBJECT_TYPE_NAME, OBJECT_TYPE_VALUE);
                intent.putExtra(OBJECT_TYPE_VALUE, topRated);
                startActivity(intent);
            }
        }, getActivity());

        binding.topRatedRv.setAdapter(adapter);
        binding.topRatedRv.setHasFixedSize(true);
        binding.topRatedRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFromDatabase();

    }

    public void getFromDatabase(){
        movieViewModel.getAllTopRatedMovies().observe(getActivity(), new Observer<List<TopRated>>() {
            @Override
            public void onChanged(List<TopRated> topRateds) {
                adapter.setTopRateds(topRateds);
            }
        });
    }
}