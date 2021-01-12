package com.example.navitateste.network;

import com.example.navitateste.model.BodyResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    String API_KEY = "?api_key=aee621a049370abd068e7637a8d11225";

    @GET("now_playing"+API_KEY)
    Call<BodyResponseModel> getNowPlayingMovieList();

}