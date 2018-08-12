package com.example.arun.moviedb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>  {

    ArrayList<Movie> movies;
    Context context;

    public MoviesAdapter(Context context , ArrayList<Movie> movies ) {
        this.context = context;
        this.movies= movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_post,parent,false);
        return new MovieViewHolder(rowLayout);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie post = movies.get(position);
        MovieViewHolder.titleTextView.setText(post.title);
//        MovieViewHolder.imageImageView.setImageDrawable(post.poster_path);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
