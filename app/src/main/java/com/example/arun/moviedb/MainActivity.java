package com.example.arun.moviedb;

import android.content.Intent;
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

    RecyclerView recyclerView;
    MoviesAdapter adapter;
    ArrayList<Movie> mMovies = new ArrayList<>();

    RecyclerView recyclerView2;
    MoviesAdapter adapter2;
    ArrayList<Movie> mMovies2 = new ArrayList<>();


    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);

        adapter = new MoviesAdapter(this, mMovies, new MoviesClickListener() {
            @Override
            public void onMovieClicked(View view , int position) {
                Movie currentMovie= mMovies.get(position);
                Intent intent = new Intent(getApplicationContext(), CompleteMovieDescription.class);
                intent.putExtra("id", currentMovie.id);
                startActivityForResult(intent,1);

            }
        });

        adapter2=new MoviesAdapter(this, mMovies2, new MoviesClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        retrofit = ApiClient.getInstance();

        fetchData();


    }


    public void fetchData()
    {
        Call<MovieResponse> call = ApiClient.getMoviesService().getNowPlayingMovies(1);
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


        Call<MovieResponse> call2 = ApiClient.getMoviesService().getPopularMovies();
        call2.enqueue(new Callback<MovieResponse>(){

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                MovieResponse popularMovie = response.body();
                ArrayList<Movie> temp;
                temp= popularMovie.results;
                mMovies2.addAll(temp);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }



    public void viewAll(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewMore.class);
        startActivityForResult(intent,1);
    }
}
