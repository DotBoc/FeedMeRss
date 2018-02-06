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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings_Basic_RSS_selection extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DataSnapshot mDataSnapshot;
    private FirebaseAuth mAuth;
    private String uid;
    Boolean check;

    private TextView BasicRss;
    private CheckBox Health,Sports,Films,Finance,Politics,Weather,Lifestyle,Theatre,Travel,Animals;
    private Button Save;




    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__basic__rss_selection);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        BasicRss = findViewById(R.id.TVBasicRss);


        Health = findViewById(R.id.CBHealth);
        Health.setChecked(CheckState("Health"));
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
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onCheckboxClicked(v);


            }
        });

    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.CBHealth:
                if (checked){
                    AddRSS("Health","https://www.huffingtonpost.com/section/health/feed");
                }
                else{
                    RemoveRSS("Health");
                }

                break;

            case R.id.CBSports:
                if (checked){
                    AddRSS("Sports","https://www.huffingtonpost.com/section/sports/feed");
                }
                else{
                    RemoveRSS("Sports");
                }
                break;

            case R.id.CBFilms:
                if (checked){
                    AddRSS("Films","https://www.huffingtonpost.com/topic/film/feed");
                }
                else{
                    RemoveRSS("Films");
                }
                break;


            case R.id.CBFinance:
                if (checked){
                    AddRSS("Finance","https://www.huffingtonpost.com/section/money/feed");
                }
                else{
                    RemoveRSS("Finance");
                }
                break;


            case R.id.CBPolitics:
                if (checked){
                    AddRSS("Politics","https://www.huffingtonpost.com/section/politics/feed");
                }
                else{
                    RemoveRSS("Politics");
                }
                break;


            case R.id.CBWeather:
                if (checked){
                    AddRSS("Weather","https://www.huffingtonpost.com/section/health/feed");
                }
                else{
                    RemoveRSS("Weather");
                }
                break;


            case R.id.CBLifestyle:
                if (checked){
                    AddRSS("Lifestyle","https://www.huffingtonpost.com/section/celebrity/feed");
                }
                else{
                    RemoveRSS("Lifestyle");
                }
                break;


            case R.id.CBTheatre:
                if (checked){
                    AddRSS("Theater","https://www.huffingtonpost.com/topic/theatre/feed");
                }
                else{
                    RemoveRSS("Theater");
                }
                break;


            case R.id.CBTravel:
                if (checked){
                    AddRSS("Travel"," https://www.huffingtonpost.com/section/travel/feed");
                }
                else{
                    RemoveRSS("Travel");
                }
                break;


            case R.id.CBAnimals:
                if (checked){
                    AddRSS("Animals","https://www.huffingtonpost.com/topic/animals/feed");
                }
                else{
                    RemoveRSS("Animals");
                }
                break;
        }
    }

    private void AddRSS(String id , String link) {
        mDatabaseReference.child(uid).child("BasicRSSFeeds").child(id).setValue(link);
        Log.e(id,link);
    }

    private void RemoveRSS(String id){
        mDatabaseReference.child(uid).child("BasicRSSFeeds").child(id).removeValue();

    }

    private boolean CheckState(String id){
        mDatabaseReference.child(uid).child("BasicRSSFeeds").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    check = true;
                    Log.e("snap","true");
                }
                else {
                    check = false;
                    Log.e("snap","false");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                check = false;
                Toast.makeText(Settings_Basic_RSS_selection.this , R.string.error , Toast.LENGTH_LONG).show();
            }
            return ValueEventListener;
        });
        return check;
    }
}
