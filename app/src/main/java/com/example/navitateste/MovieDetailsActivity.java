package com.example.navitateste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.navitateste.model.MovieModel;

import static com.example.navitateste.adapter.MovieListAdapter.IMG_URL_BASE;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieModel movieModel = (MovieModel) getIntent().getSerializableExtra("title");
        setContentView(R.layout.activity_movie_details);
        this.setTitle(""+movieModel.getTitle());

        ImageView detail_movieBanner = findViewById(R.id.text_detail_movieBanner);
        TextView detail_movieOverview = findViewById(R.id.text_detail_movieOverview);

        detail_movieOverview.setText(movieModel.getOverview());

        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_movieBanner);


    }


}