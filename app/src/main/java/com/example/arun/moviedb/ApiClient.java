package com.example.arun.moviedb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    private static MoviesService service;

    static Retrofit getInstance(){
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/now_playing")
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

    static MoviesService getMoviesService(){
        if(service == null){
            service = getInstance().create(MoviesService.class);
        }
        return service;
    }
}
