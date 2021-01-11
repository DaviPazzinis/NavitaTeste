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
import com.example.navitateste.model.BodyResponseModel;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private Context context;
    private BodyResponseModel movieList;
    String IMG_URL_BASE = "https://image.tmdb.org/t/p/w500/";
    private OnNoteListener mOnNoteListener;

    public MovieListAdapter(Context context, BodyResponseModel movieList, OnNoteListener mOnNoteListener) {
        this.context = context;
        this.movieList = movieList;
        this.mOnNoteListener = mOnNoteListener;
    }

    public void setModelList(BodyResponseModel modelList) {
        this.movieList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.getResults().get(position).getTitle());

        Glide.with(context)
                .load(IMG_URL_BASE + this.movieList.getResults().get(position).getBackdrop_path())
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        if (this.movieList != null) {
            return this.movieList.getResults().size();
        }

        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        ImageView movieImage;
        OnNoteListener onNoteListener;

        public MyViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.textMovieNameView);
            movieImage = itemView.findViewById(R.id.movieImage);
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
