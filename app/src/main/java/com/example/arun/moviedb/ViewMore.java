package com.example.arun.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMore extends AppCompatActivity {

    RecyclerView recyclerView;
    MoviesAdapter adapter;
    ArrayList<Movie> mMovies = new ArrayList<>();
    Boolean isScrolling = false;
    int pageNum=1;

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
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int currentItems= layoutManager.getChildCount();
                int totalItems = layoutManager.getItemCount();
                int scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling == true && (currentItems + scrollOutItems == totalItems) )
                {
                    // data fetch
                    pageNum++;
                    fetchData(pageNum);
                }

            }
        });

        fetchData(pageNum);

    }

    public void fetchData(int pageNum)
    {
        Call<MovieResponse> call = ApiClient.getMoviesService().getNowPlayingMovies(pageNum);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
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

            }
        });

    }
}
