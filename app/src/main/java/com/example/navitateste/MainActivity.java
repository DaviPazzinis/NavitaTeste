package com.example.navitateste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.navitateste.adapter.MovieListAdapter;
import com.example.navitateste.model.MovieResponseDTO;
import com.example.navitateste.model.MovieModel;
import com.example.navitateste.util.LanguageSelector;
import com.example.navitateste.viewmodel.MovieListViewModel;
import com.example.navitateste.util.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.OnNoteListener {

    private MovieResponseDTO movieResponseDTOList;
    private MovieListAdapter mAdapter;

    MovieListViewModel viewModel;
    TextView tvNoResult;
    SwipeRefreshLayout main_refreshLayout;
    RecyclerView recyclerView;
    BroadcastReceiver broadcastReceiver;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LanguageSelector languageSelector = new LanguageSelector();
        languageSelector.getSystemLocale();


        // Getting all IDS
        findViewsById();

        // Calling network methods
        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        // Applying layout
        layoutManager = new LinearLayoutManager(this);

        //  Setting Layout into RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        // mAdapter receiving content
        mAdapter = new MovieListAdapter(this, movieResponseDTOList, this);

        // Setting mAdapter into the recyclerView
        recyclerView.setAdapter(mAdapter);

        // Setting viewModel
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //Getting content and applying it
        fetchMovies();

        /**
         * Função que ouve se ha alguma requisição de refresh
         */
        main_refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                forceRefresh();
            }
        });


    }

    public void findViewsById() {
        recyclerView = findViewById(R.id.main_recyclerView);
        tvNoResult = findViewById(R.id.main_tv_noResult);
        main_refreshLayout = findViewById(R.id.main_refreshLayout);
    }

    public void forceRefresh() {
        fetchMovies();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                main_refreshLayout.setRefreshing(false);
            }
        }, 4 * 1000);
    }

    /**
     * Função que contém o viewModel observando se existe alguma mudança para avisar o adapter.
     * Caso nulo ele seta o tvNoResults para Visivel mostrando que algum erro ocorreu.
     */
    private void fetchMovies() {
        viewModel.getNowPlayingMoviesListObserver().observe(this, new Observer<MovieResponseDTO>() {
            @Override
            public void onChanged(MovieResponseDTO movieModels) {
                if (movieModels != null) {
                    movieResponseDTOList = movieModels;
                    mAdapter.setModelList(movieModels);
                    tvNoResult.setVisibility(View.GONE);
                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeNowPlayingMovieList();
    }

    /**
     * Função que cria a intent MovieDetailsActivity, mandando para ela
     * o Model movieModel com todas as infos.
     *
     * @param position Int que guarda a posição do item clicado
     */
    @Override
    public void onNoteClick(int position) {
        movieResponseDTOList.getResults().get(position);
        Intent it = new Intent(this, MovieDetailsActivity.class);
        MovieModel movieModel = movieResponseDTOList.getResults().get(position);
        it.putExtra("movieModel", movieModel);
        startActivity(it);
    }

    /**
     * Função para verificar versão do SDK
     */
    protected void registerNetworkBroadcastReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }

    /**
     * Função para registrar a não conexão a internet
     */
    protected void unregisterNetwork() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * ONLY LIFE CYCLE DOWN BELLOW
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        forceRefresh();
        Log.d("lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterNetwork();
        Log.d("lifecycle", "onStop invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        forceRefresh();
        unregisterNetwork();
        Log.d("lifecycle", "onRestart invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetwork();
        Log.d("lifecycle", "onDestroy invoked");
    }
}
