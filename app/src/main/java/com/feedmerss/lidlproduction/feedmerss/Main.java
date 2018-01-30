package com.feedmerss.lidlproduction.feedmerss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar_with_components);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final String url = intent.getStringExtra("FIREBASE_URL");
    }
}
