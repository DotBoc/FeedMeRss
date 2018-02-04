package com.feedmerss.lidlproduction.feedmerss;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class Settings_Add_RSS extends AppCompatActivity {

    private TextView AddFeed;
    private Button Save;
    private EditText RssName;
    private EditText RssLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__add__rss);

         AddFeed = findViewById(R.id.TVRssFeed);
         Save = findViewById(R.id.BSave);
         RssName = findViewById(R.id.ETRssName);
         RssLink = findViewById(R.id.ETRssLink);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



    }
}
