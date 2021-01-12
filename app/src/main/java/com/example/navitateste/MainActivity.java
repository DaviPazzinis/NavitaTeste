package com.example.navitateste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.navitateste.adapter.MovieListAdapter;
import com.example.navitateste.model.BodyResponseModel;
import com.example.navitateste.model.MovieModel;
import com.example.navitateste.viewmodel.MovieListViewModel;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnNoteListener {

    private BodyResponseModel bodyResponseModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noData = findViewById(R.id.noData);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this, (BodyResponseModel) bodyResponseModelList, this);

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

    @Override
    public void onNoteClick(int position) {
        bodyResponseModelList.getResults().get(position);
        Intent it = new Intent(this, MovieDetailsActivity.class);

        MovieModel movieModel = bodyResponseModelList.getResults().get(position);
        it.putExtra("title", movieModel);
        startActivity(it);
    }
}