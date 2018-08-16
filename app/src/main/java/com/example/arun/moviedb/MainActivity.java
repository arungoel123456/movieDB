package com.example.arun.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    MovieResponse playingMovies;
    RecyclerView recyclerView;
    MoviesAdapter adapter;
    ArrayList<Movie> mMovies = new ArrayList<>();

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MoviesAdapter(this, mMovies);
        recyclerView.setAdapter(adapter);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        retrofit = ApiClient.getInstance();

        fetchData();


    }


    public void fetchData()
    {
        Call<MovieResponse> call = ApiClient.getMoviesService().getNowPlayingMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("fetchdata","onResponseCall");
                MovieResponse nowPlayingMovies = response.body();
//                playingMovies.clear();
//                playingMovies.addAll(nowPlayingMovies);
                ArrayList<Movie> temp ;
                temp=nowPlayingMovies.results;

                mMovies.addAll(temp);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("fetchdataerror",t.getMessage());

            }
        });
    }

}
