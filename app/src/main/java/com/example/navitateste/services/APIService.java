package com.example.navitateste.services;

import com.example.navitateste.model.MovieResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    String API_KEY = "?api_key=aee621a049370abd068e7637a8d11225";

    @GET("movie/now_playing"+API_KEY)
    Call<MovieResponseDTO> getNowPlayingMovieList(
            @Query("language") String language
    );

}



