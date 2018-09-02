package com.example.arun.moviedb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/now_playing?api_key=6cc38f1dd9439f49f5fa25a0b59f3d52&language=en-US")
    Call<MovieResponse> getNowPlayingMovies(@Query("page") int page);


    @GET("https://api.themoviedb.org/3/movie/popular?api_key=6cc38f1dd9439f49f5fa25a0b59f3d52&language=en-US&page=1")
    Call<MovieResponse> getPopularMovies();

    @GET("https://api.themoviedb.org/3/movie/{id}?api_key=6cc38f1dd9439f49f5fa25a0b59f3d52&language=en-US")
    Call<Movie> getCompleteMovieDetail(@Path("id") int id);

}
