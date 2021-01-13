package com.example.navitateste.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navitateste.R;
import com.example.navitateste.adapter.MovieListAdapter;
import com.example.navitateste.model.MovieResponseDTO;
import com.example.navitateste.model.MovieModel;
import com.example.navitateste.viewmodel.MovieListViewModel;
import com.example.navitateste.viewmodel.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnNoteListener {

    private MovieResponseDTO movieResponseDTOList;
    private MovieListAdapter adapter;

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);
        TextView tvNoResult = findViewById(R.id.main_tv_noResult);

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

    protected void registerNetworkBroadcastReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }

    protected void unregisterNetwork() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetwork();
    }
}