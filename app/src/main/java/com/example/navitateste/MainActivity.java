package com.example.navitateste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.navitateste.adapter.MovieListAdapter;
import com.example.navitateste.model.BodyResponseModel;
import com.example.navitateste.viewmodel.MovieListViewModel;

public class MainActivity extends AppCompatActivity {

    private BodyResponseModel bodyResponseModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noData = findViewById(R.id.noData);

        LinearLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this, (BodyResponseModel) bodyResponseModelList);

        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        viewModel.getNowPlayingMoviesListObserver().observe(this, new Observer<BodyResponseModel>() {
            @Override
            public void onChanged(BodyResponseModel movieModels) {
                if (movieModels != null) {
                    bodyResponseModelList = movieModels;

                    adapter.setModelList(movieModels);

                    noData.setVisibility(View.GONE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}