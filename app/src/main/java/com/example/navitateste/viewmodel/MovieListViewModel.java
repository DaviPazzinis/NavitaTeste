package com.example.navitateste.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitateste.model.MovieResponseDTO;
import com.example.navitateste.services.APIService;
import com.example.navitateste.services.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListViewModel extends ViewModel {

    private final MutableLiveData<MovieResponseDTO> nowPlayingMovieList;

    public MovieListViewModel(){
        nowPlayingMovieList = new MutableLiveData<>();
    }

    public MutableLiveData<MovieResponseDTO> getNowPlayingMoviesListObserver(){
        return nowPlayingMovieList;
    }

    public void makeNowPlayingMovieList(){
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);

        Call<MovieResponseDTO> call = apiService.getNowPlayingMovieList();

        call.enqueue(new Callback<MovieResponseDTO>() {
            @Override
            public void onResponse(Call<MovieResponseDTO> call, Response<MovieResponseDTO> response) {
                nowPlayingMovieList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieResponseDTO> call, Throwable t) {
                nowPlayingMovieList.postValue(null);
            }
        });
    }
}