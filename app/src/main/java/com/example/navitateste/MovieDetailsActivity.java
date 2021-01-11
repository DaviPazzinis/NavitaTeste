package com.example.navitateste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.navitateste.model.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MovieModel movieModel = (MovieModel) getIntent().getSerializableExtra("title");

        TextView details_movieName = findViewById(R.id.text_details_movieName);

        details_movieName.setText(movieModel.getTitle());

    }


}