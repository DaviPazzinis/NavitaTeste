package com.example.navitateste.services;

import com.example.navitateste.model.BodyResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    String API_KEY = "?api_key=aee621a049370abd068e7637a8d11225";
    String LANGUAGE = "&language=pt-BR";

    @GET("movie/now_playing"+API_KEY+LANGUAGE)
    Call<BodyResponseModel> getNowPlayingMovieList();

    @GET("genre/tv/list"+API_KEY+LANGUAGE)
    Call<BodyResponseModel> getGenreList();

}