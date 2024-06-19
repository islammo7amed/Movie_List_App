package com.example.movielistapp.ui.home.popular;

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
import com.example.movielistapp.database.Popular;
import com.example.movielistapp.databinding.FragmentPopularBinding;
import com.example.movielistapp.ui.adapters.OnPopularClickListener;
import com.example.movielistapp.ui.adapters.PopularRecyclerViewAdapter;
import com.example.movielistapp.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {

    FragmentPopularBinding binding;
    MovieViewModel movieViewModel;
    PopularRecyclerViewAdapter adapter;

    public static final String OBJECT_TYPE_VALUE = "popular";

    public PopularFragment() {
        // Required empty public constructor
    }

    public static PopularFragment newInstance() {

        PopularFragment fragment = new PopularFragment();

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
        binding = FragmentPopularBinding.inflate(inflater, container, false);

        adapter = new PopularRecyclerViewAdapter(new ArrayList<>(), new OnPopularClickListener() {
            @Override
            public void onPopularClick(Popular popular) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.OBJECT_TYPE_NAME, OBJECT_TYPE_VALUE);
                intent.putExtra(OBJECT_TYPE_VALUE, popular);
                startActivity(intent);
            }
        }, getActivity());

        binding.popularRv.setAdapter(adapter);
        binding.popularRv.setHasFixedSize(true);
        binding.popularRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getFromDatabase();

    }

    public void getFromDatabase(){
        movieViewModel.getAllPopularMovies().observe(getActivity(), new Observer<List<Popular>>() {
            @Override
            public void onChanged(List<Popular> populars) {
                adapter.setMovieModels(populars);
            }
        });
    }
}