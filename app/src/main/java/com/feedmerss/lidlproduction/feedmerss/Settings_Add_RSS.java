package com.feedmerss.lidlproduction.feedmerss;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings_Add_RSS extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private String uid;


    private TextView AddFeed;
    private Button Save;
    private EditText RssName;
    private EditText RssLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__add__rss);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        AddFeed = findViewById(R.id.TVRssFeed);
        RssName = findViewById(R.id.ETRssName);
        RssLink = findViewById(R.id.ETRssLink);
        Save = findViewById(R.id.BSave);
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AddRSS(RssName.getText().toString(),RssLink.getText().toString());
                Toast.makeText(Settings_Add_RSS.this , getString(R.string.added_rss_link)  + RssName.getText().toString() , Toast.LENGTH_LONG).show();
            }
        });




    }

    private void AddRSS(String id , String link) {
        mDatabaseReference.child(uid).child("CustomRSSFeed").child(id).setValue(link);
    }

}
