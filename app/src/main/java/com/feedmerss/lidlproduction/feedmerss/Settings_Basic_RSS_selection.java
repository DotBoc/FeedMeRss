package com.feedmerss.lidlproduction.feedmerss;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings_Basic_RSS_selection extends AppCompatActivity {

    private TextView BasicRss;
    private CheckBox Health,Sports,Films,Finance,Politics,Weather,Lifestyle,Theatre,Travel,Animals;
    private Button Save;
    private DatabaseReference mDatabase;


    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__basic__rss_selection);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final String urlDB = mDatabase.getDatabase().toString();

        BasicRss = findViewById(R.id.TVBasicRss);
        Health = findViewById(R.id.CBHealth);
        Health.setChecked(getFromDB("Health"));
        Sports = findViewById(R.id.CBSports);
        Sports.setChecked(getFromDB("Sports"));
        Films = findViewById(R.id.CBFilms);
        Films.setChecked(getFromDB("Films"));
        Finance = findViewById(R.id.CBFinance);
        Finance.setChecked(getFromDB("Finance"));
        Politics = findViewById(R.id.CBPolitics);
        Politics.setChecked(getFromDB("Politics"));
        Weather = findViewById(R.id.CBWeather);
        Weather.setChecked(getFromDB("Weather"));
        Lifestyle = findViewById(R.id.CBLifestyle);
        Lifestyle.setChecked(getFromDB("Lifestyle"));
        Theatre = findViewById(R.id.CBTheatre);
        Theatre.setChecked(getFromDB("Theatre"));
        Travel = findViewById(R.id.CBTravel);
        Theatre.setChecked(getFromDB("Travel"));
        Animals = findViewById(R.id.CBAnimals);
        Animals.setChecked(getFromDB("Animals"));
        Save = findViewById(R.id.BSave);
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mDatabase.child("CustomRSS").setValue("1");
                Log.e("urlDB",urlDB);
                Log.e("uid",uid);
                Log.e("koumpi","egine");

            }
        });

    }

    private boolean getFromDB(String key){
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean(key, false);
    }

    private void saveInDB(String key,boolean value){
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }



}
