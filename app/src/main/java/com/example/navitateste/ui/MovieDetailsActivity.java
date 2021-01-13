package com.example.navitateste.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.navitateste.R;
import com.example.navitateste.model.MovieModel;

import static com.example.navitateste.adapter.MovieListAdapter.IMG_URL_BASE;

public class MovieDetailsActivity extends AppCompatActivity {

    MovieModel movieModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Retrieving content from MainActivity
        movieModel = (MovieModel) getIntent().getSerializableExtra("title");

        // FIND VIEW BY ID IN MENU AREA
        ImageView detail_menu_movieBanner = findViewById(R.id.details_iv_menu_banner);
        TextView detail_menu_moviePopularity = findViewById(R.id.details_tv_menu_popularity);
        TextView detail_menu_movieVotes = findViewById(R.id.details_iv_menu_votes);
        TextView detail_menu_movieDate = findViewById(R.id.details_tv_menu_realiseDate);

        // SETTING DATA INTO MENU AREA
        this.setTitle("" + movieModel.getTitle());
        detail_menu_moviePopularity.setText("" + (int) movieModel.getPopularity());
        detail_menu_movieVotes.setText("" + (int) movieModel.getVote_count());
        detail_menu_movieDate.setText(movieModel.getRelease_date());
        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_menu_movieBanner);

        // FIND VIEW BY ID IN DETAIL AREA
        ImageView detail_movieBannerBG = findViewById(R.id.details_iv_bannerBG);
        TextView detail_movieOverview = findViewById(R.id.details_tv_overview);
        TextView detail_originalLanguage = findViewById(R.id.details_tv_originalLanguage);
        TextView detail_originalTitle = findViewById(R.id.details_tv_originalTitle);
        TextView detail_realiseDate = findViewById(R.id.details_tv_realiseDate);
        TextView detail_title = findViewById(R.id.details_tv_title);
        TextView detail_popularity = findViewById(R.id.details_tv_popularity);
        TextView detail_averageVotes = findViewById(R.id.details_tv_averageVote);
        TextView detail_votesCount = findViewById(R.id.details_tv_votesCount);

        //SETTING DATA INTO DETAILS AREA
        detail_movieOverview.setText(movieModel.getOverview());
        detail_originalLanguage.setText(movieModel.getOriginal_language());
        detail_originalTitle.setText(movieModel.getOriginal_title());
        detail_realiseDate.setText(movieModel.getRelease_date());
        detail_title.setText(movieModel.getTitle());
        detail_popularity.setText("" + (int) movieModel.getPopularity());
        detail_averageVotes.setText("" + (int) movieModel.getVote_average());
        detail_votesCount.setText("" + (int) movieModel.getVote_count());
        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_movieBannerBG);
    }

}