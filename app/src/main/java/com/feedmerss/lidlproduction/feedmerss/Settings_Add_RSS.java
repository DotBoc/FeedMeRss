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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings_Add_RSS extends AppCompatActivity {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;

    private TextView AddFeed;
    private Button Save;
    private EditText RssName;
    private EditText RssLink;
    private String uid;

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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


       Save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AddRSS();
           }
       });
    }

    private void AddRSS() {
        //mDatabaseReference.child(uid).setValue("Hello, World!");
        mDatabaseReference.child(uid).setValue("123213123");
        Log.e("kurwa","Etrekse");

        Log.e("kurwa","");

        //displaying a success toast
        Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
    }


}
