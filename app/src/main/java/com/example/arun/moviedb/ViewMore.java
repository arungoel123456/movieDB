package com.example.arun.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ViewMore extends AppCompatActivity {

    RecyclerView recyclerView;
    MoviesAdapter adapter;
    ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);

        recyclerView = findViewById(R.id.recyclerView);
        adapter=new MoviesAdapter(this, mMovies, new MoviesClickListener() {
            @Override
            public void onMovieClicked(View view, int position) {

            }
        });

        recyclerView.setAdapter(adapter);

        fetchData();

    }

    public void fetchData()
    {

    }
}
