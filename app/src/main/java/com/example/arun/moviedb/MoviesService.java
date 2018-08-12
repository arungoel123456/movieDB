package com.example.arun.moviedb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesService {

    @GET("?api_key=6cc38f1dd9439f49f5fa25a0b59f3d52&language=en-US&page=1")
    Call<ArrayList<Movie>> getNowPlayingMovies();
}
