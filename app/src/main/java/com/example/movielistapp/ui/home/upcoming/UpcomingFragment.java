package com.example.movielistapp.ui.home.upcoming;

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
import com.example.movielistapp.database.Upcoming;
import com.example.movielistapp.databinding.FragmentUpcomingBinding;
import com.example.movielistapp.ui.adapters.OnUpcomingClickListener;
import com.example.movielistapp.ui.adapters.UpcomingRecyclerViewAdapter;
import com.example.movielistapp.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {

    FragmentUpcomingBinding binding;
    MovieViewModel movieViewModel;
    UpcomingRecyclerViewAdapter adapter;

    public static final String OBJECT_TYPE_VALUE = "upcoming";

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public static UpcomingFragment newInstance() {

        UpcomingFragment fragment = new UpcomingFragment();

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
        binding = FragmentUpcomingBinding.inflate(inflater, container, false);

        adapter = new UpcomingRecyclerViewAdapter(new ArrayList<>(), new OnUpcomingClickListener() {
            @Override
            public void onUpcomingClick(Upcoming upcoming) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.OBJECT_TYPE_NAME, OBJECT_TYPE_VALUE);
                intent.putExtra(OBJECT_TYPE_VALUE, upcoming);
                startActivity(intent);
            }
        }, movieViewModel, getActivity());

        binding.upcomingRv.setAdapter(adapter);
        binding.upcomingRv.setHasFixedSize(true);
        binding.upcomingRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFromDatabase();

    }

    public void getFromDatabase(){
        movieViewModel.getAllUpcomingMovies().observe(getActivity(), new Observer<List<Upcoming>>() {
            @Override
            public void onChanged(List<Upcoming> upcomings) {
                adapter.setUpcomings(upcomings);
            }
        });
    }
}