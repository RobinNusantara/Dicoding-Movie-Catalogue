package com.informatika.umm.myapplication.ui.fragment.movies;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.informatika.umm.myapplication.R;
import com.informatika.umm.myapplication.adapter.MovieListAdapter;
import com.informatika.umm.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private ShimmerFrameLayout shimmerFrameLayout;
    private MovieListAdapter listAdapter;
    private MovieViewModel viewModel;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView rvDiscover;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupViewModel();
        showDiscoverMovie();
        viewModel.loadDiscoverMovie();
        shimmerFrameLayout.startShimmer();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                listAdapter.setMovie(movieList);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        });
    }

    private void showDiscoverMovie() {
        LinearLayoutManager layoutNowPlayingMovies = new LinearLayoutManager(getContext());
        listAdapter = new MovieListAdapter(getContext(), movieList);
        rvDiscover.setHasFixedSize(true);
        rvDiscover.setLayoutManager(layoutNowPlayingMovies);
        rvDiscover.setAdapter(listAdapter);
    }

    private void bindView(View view) {
        shimmerFrameLayout = view.findViewById(R.id.shimmer_container);
        rvDiscover = view.findViewById(R.id.rv_movies_now_playing);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        if (getContext() != null){
            SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
            if (searchManager != null){
                SearchView searchView = (SearchView) (menu.findItem(R.id.btn_search)).getActionView();
                searchView.setQueryHint(getResources().getString(R.string.str_search));
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                });
            }
        }

        super.onCreateOptionsMenu(menu, inflater);
    }
}