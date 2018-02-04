package com.feedmerss.lidlproduction.feedmerss;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;

public class Settings_Basic_RSS_selection extends AppCompatActivity {

    private TextView BasicRss;
    private CheckBox Health,Sports,Films,Finance,Politics,Weather,Lifestyle,Theatre,Travel,Animals;
    private Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__basic__rss_selection);

        BasicRss = findViewById(R.id.TVBasicRss);
        Health = findViewById(R.id.CBHealth);
        Sports = findViewById(R.id.CBSports);
        Films = findViewById(R.id.CBFilms);
        Finance = findViewById(R.id.CBFinance);
        Politics = findViewById(R.id.CBPolitics);
        Weather = findViewById(R.id.CBWeather);
        Lifestyle = findViewById(R.id.CBLifestyle);
        Theatre = findViewById(R.id.CBTheatre);
        Travel = findViewById(R.id.CBTravel);
        Animals = findViewById(R.id.CBAnimals);
        Save = findViewById(R.id.BSave);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
