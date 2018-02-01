package com.feedmerss.lidlproduction.feedmerss;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private static String FIREBASE_URL = "https://feedmerss-4580c.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar_with_components);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerlayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerlayout, toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerlayout.setDrawerListener(mActionBarDrawerToggle);

        mDrawerlayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });


        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.basic_settings:
                gotoActivity(Settings_Basic_RSS_selection.class);
                break;

            case R.id.add_custom_rss:
                gotoActivity(Settings_Add_RSS.class);
                break;

            case R.id.manage_custom_rss:
                gotoActivity(Settings_Manage_Custom_RSS.class);
                break;

            case R.id.help:
                gotoActivity(Help.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoActivity(Class nextClass){
        Intent intent = new Intent(Main.this ,nextClass);
       // intent.putExtra("FIREBASE_URL", FIREBASE_URL);
        startActivity(intent);

    }
}
