package com.example.navitateste.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navitateste.model.BodyResponseModel;
import com.example.navitateste.network.APIService;
import com.example.navitateste.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListViewModel extends ViewModel {

    private final MutableLiveData<BodyResponseModel> nowPlayingMoviesList;

    public MovieListViewModel(){
        nowPlayingMoviesList = new MutableLiveData<>();
    }

    public MutableLiveData<BodyResponseModel> getNowPlayingMoviesListObserver(){
        return nowPlayingMoviesList;
    }

    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);

        Call<BodyResponseModel> call = apiService.getNowPlayingMovieList();

        call.enqueue(new Callback<BodyResponseModel>() {
            @Override
            public void onResponse(Call<BodyResponseModel> call, Response<BodyResponseModel> response) {
                nowPlayingMoviesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BodyResponseModel> call, Throwable t) {
                nowPlayingMoviesList.postValue(null);
            }
        });
    }
}