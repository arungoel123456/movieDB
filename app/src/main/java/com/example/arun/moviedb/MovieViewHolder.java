package com.example.arun.moviedb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends  RecyclerView.ViewHolder {

    static ImageView imageImageView;
    static TextView titleTextView;
    static View itemView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        titleTextView = itemView.findViewById(R.id.titleTextview);
        imageImageView = itemView.findViewById(R.id.imageImageview);

    }

}
