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

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> playingMovies;
    RecyclerView recyclerView;
    MoviesAdapter adapter;
    ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MoviesAdapter(this, mMovies);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        fetchData();


    }


    public void fetchData()
    {
        Call<ArrayList<Movie>> call = ApiClient.getMoviesService().getNowPlayingMovies();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                Log.d("fetchdata","onResponseCall");
                ArrayList<Movie> nowPlayingMovies = response.body();
                playingMovies.clear();
                playingMovies.addAll(nowPlayingMovies);
                mMovies.addAll(nowPlayingMovies);
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                Log.d("fetchdata","onFailure");

            }
        });
    }

}
