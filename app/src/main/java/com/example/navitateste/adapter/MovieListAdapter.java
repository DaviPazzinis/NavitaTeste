package com.example.navitateste.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navitateste.R;
import com.example.navitateste.model.MovieResponseDTO;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private final Context context;
    private MovieResponseDTO movieList;
    public static final String IMG_URL_BASE = "https://image.tmdb.org/t/p/w500/";
    private final OnNoteListener mOnNoteListener;

    public MovieListAdapter(Context context, MovieResponseDTO movieList, OnNoteListener mOnNoteListener) {
        this.context = context;
        this.movieList = movieList;
        this.mOnNoteListener = mOnNoteListener;
    }

    public void setModelList(MovieResponseDTO modelList) {
        this.movieList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.getResults().get(position).getTitle());

        Glide.with(context)
                .load(IMG_URL_BASE + this.movieList.getResults().get(position).getPoster_path())
                .into(holder.ivMovieImage);

        holder.tvMovieDate.setText(this.movieList.getResults().get(position).getRelease_date());
        holder.tvMovieRate.setText("" + this.movieList.getResults().get(position).getVote_average());


    }

    @Override
    public int getItemCount() {
        if (this.movieList != null) {
            return this.movieList.getResults().size();
        }

        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView tvTitle;
        ImageView ivMovieImage;
        TextView tvMovieDate;
        TextView tvMovieRate;

        OnNoteListener onNoteListener;

        public MyViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.item_tv_title);
            ivMovieImage = itemView.findViewById(R.id.item_iv_banner);
            tvMovieDate = itemView.findViewById(R.id.item_tv_realiseDate);
            tvMovieRate = itemView.findViewById(R.id.item_tv_rate);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClick(getBindingAdapterPosition());

        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }

}