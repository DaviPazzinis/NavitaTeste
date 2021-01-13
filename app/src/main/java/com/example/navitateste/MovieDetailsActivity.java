package com.example.navitateste;

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

    // Model
    MovieModel movieModel;

    // Menu IDS
    ImageView detail_menu_movieBanner;
    TextView detail_menu_moviePopularity;
    TextView detail_menu_movieVotes;
    TextView detail_menu_movieDate;

    // Details IDS
    ImageView detail_movieBannerBG;
    TextView detail_movieOverview;
    TextView detail_originalLanguage;
    TextView detail_originalTitle;
    TextView detail_realiseDate;
    TextView detail_title;
    TextView detail_popularity;
    TextView detail_averageVotes;
    TextView detail_votesCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Calling function to get data from MainActivity
        retrieveData();

        // Calling Find View Functions
        menuFindViews();
        detailsFindViews();

        // Calling Function to set data in Views
        settingDataOnMenuViews();
        settingDataOnDetailViews();

    }

    /**
     * Este método recebe na movieModel todas as infos passadas pela MainActivity para a
     * MovieDetailsAcitivity.
     */
    public void retrieveData(){
        movieModel = (MovieModel) getIntent().getSerializableExtra("movieModel");
    }

    /**
     *  Este método encontra as views na activity ACTIVITY_MOVIE_DETAILS.xml que está dentro
     *  da area do MENU, totalizando 05 (CINCO) elementos, no atual design.
     */
    public void menuFindViews() {
        detail_menu_movieBanner = findViewById(R.id.details_iv_menu_banner);
        detail_menu_moviePopularity = findViewById(R.id.details_tv_menu_popularity);
        detail_menu_movieVotes = findViewById(R.id.details_iv_menu_votes);
        detail_menu_movieDate = findViewById(R.id.details_tv_menu_realiseDate);
    }

    /**
     * Este método implementa na view de menu as informações obtidas no movieModel passados
     * pelo putExtra da MainActivity.
     */
    @SuppressLint("SetTextI18n")
    public void settingDataOnMenuViews() {
        this.setTitle("" + movieModel.getTitle());
        detail_menu_moviePopularity.setText("" + (int) movieModel.getPopularity());
        detail_menu_movieVotes.setText("" + (int) movieModel.getVote_count());
        detail_menu_movieDate.setText(movieModel.getRelease_date());
        Glide.with(this)
                .load(IMG_URL_BASE + movieModel.getPoster_path())
                .into(detail_menu_movieBanner);

    }

    /**
     *  Este método encontra as views na activity ACTIVITY_MOVIE_DETAILS.xml que está dentro
     *  da area de DETALHES, totalizando 09 (NOVE) elementos, no atual design.
     */
    public void detailsFindViews() {
        detail_movieBannerBG = findViewById(R.id.details_iv_bannerBG);
        detail_movieOverview = findViewById(R.id.details_tv_overview);
        detail_originalLanguage = findViewById(R.id.details_tv_originalLanguage);
        detail_originalTitle = findViewById(R.id.details_tv_originalTitle);
        detail_realiseDate = findViewById(R.id.details_tv_realiseDate);
        detail_title = findViewById(R.id.details_tv_title);
        detail_popularity = findViewById(R.id.details_tv_popularity);
        detail_averageVotes = findViewById(R.id.details_tv_averageVote);
        detail_votesCount = findViewById(R.id.details_tv_votesCount);
    }

    /**
     * Este método implementa na view de detalhes as informações obtidas no movieModel passados
     * pelo putExtra da MainActivity.
     */
    @SuppressLint("SetTextI18n")
    public void settingDataOnDetailViews() {
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