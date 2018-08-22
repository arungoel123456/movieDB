package com.example.arun.moviedb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>  {

    ArrayList<Movie> movies;
    Context context;
    MoviesClickListener listener;

    public MoviesAdapter(Context context , ArrayList<Movie> movies , MoviesClickListener listener ) {
        this.context = context;
        this.movies= movies;
        this.listener= listener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_post,parent,false);
        return new MovieViewHolder(rowLayout);
    }
    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        Movie post = movies.get(position);
        holder.titleTextView.setText(post.title);
//        MovieViewHolder.imageImageView.setImageDrawable(post.poster_path);

        String posterPath= post.poster_path;
//
        String imageUri = "http://image.tmdb.org/t/p/w185"+ posterPath;
//        ImageView ivBasicImage = (ImageView) findViewById0(R.id.ivBasicImage);
        Picasso.get().load(imageUri).fit().into(holder.imageImageView);
        //Picasso.with(context).load(android_versions.get(i).getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onMovieClicked(view, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
