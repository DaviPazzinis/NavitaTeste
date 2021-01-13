package com.example.navitateste.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navitateste.R;
import com.example.navitateste.adapter.MovieListAdapter;
import com.example.navitateste.model.MovieResponseDTO;
import com.example.navitateste.model.MovieModel;
import com.example.navitateste.viewmodel.MovieListViewModel;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnNoteListener {

    private MovieResponseDTO movieResponseDTOList;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);
        TextView tvNoResult = findViewById(R.id.main_tv_noResult );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this, (MovieResponseDTO) movieResponseDTOList, this);

        recyclerView.setAdapter(adapter);

        MovieListViewModel viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        viewModel.getNowPlayingMoviesListObserver().observe(this, new Observer<MovieResponseDTO>() {
            @Override
            public void onChanged(MovieResponseDTO movieModels) {
                if (movieModels != null) {
                    movieResponseDTOList = movieModels;
                    adapter.setModelList(movieModels);

                    tvNoResult.setVisibility(View.GONE);
                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.makeNowPlayingMovieList();

    }

    @Override
    public void onNoteClick(int position) {
        movieResponseDTOList.getResults().get(position);
        Intent it = new Intent(this, MovieDetailsActivity.class);

        MovieModel movieModel = movieResponseDTOList.getResults().get(position);
        it.putExtra("title", movieModel);
        startActivity(it);
    }
}