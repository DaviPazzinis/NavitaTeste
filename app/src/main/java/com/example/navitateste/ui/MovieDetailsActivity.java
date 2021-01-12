package com.example.navitateste.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.navitateste.R;
import com.example.navitateste.model.MovieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.navitateste.adapter.MovieListAdapter.IMG_URL_BASE;

public class MovieDetailsActivity extends AppCompatActivity {

    MovieModel movieModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieModel = (MovieModel) getIntent().getSerializableExtra("title");
        this.setTitle(""+movieModel.getTitle());

        // FIND VIEWS BY ID AREA
        ImageView detail_movieBanner = findViewById(R.id.text_detail_movieBanner);
        ImageView detail_movieBannerBG = findViewById(R.id.text_detail_movieBannerBG);
        TextView detail_movieOverview = findViewById(R.id.text_detail_movieOverview);
        TextView detail_moviePopularity = findViewById(R.id.text_detail_moviePopularity);
        TextView detail_movieVotes = findViewById(R.id.text_detail_movieVotes);
        TextView detail_movieDate = findViewById(R.id.text_detail_movieDate);

        // SETTING DATA INTO ACTIVITY
        detail_movieOverview.setText(movieModel.getOverview());
        detail_moviePopularity.setText(""+(int) movieModel.getPopularity());
        detail_movieVotes.setText(""+(int) movieModel.getVote_count());
        detail_movieDate.setText(movieModel.getRelease_date());



        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_movieBanner);

        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_movieBannerBG);


    }

}