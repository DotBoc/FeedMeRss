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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Set_Default_RSS_Feed extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private String uid;


    private TextView AddFeed;
    private Button Save;
    private EditText RssName;
    private EditText RssLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_default_rss_feed);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RssLink = findViewById(R.id.ETRssLink);
        Save = findViewById(R.id.BSave);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        mDatabaseReference.child(uid).child("CustomRSSFeed").child("DefaultRSSLink").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       if(dataSnapshot.exists())
                       {
                           RssLink.setText(dataSnapshot.getValue(String.class));
                       } else
                       {
                           RssLink.setText("");
                       }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        RssLink.setText("");
                        Toast.makeText(Set_Default_RSS_Feed.this , getString(R.string.error)  , Toast.LENGTH_LONG).show();
                    }
                });





        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(RssLink.length() == 0){

                    Toast.makeText(Set_Default_RSS_Feed.this , getString(R.string.insert_rss_link)   , Toast.LENGTH_LONG).show();

                } else {

                    AddRSS(RssLink.getText().toString());
                    Toast.makeText(Set_Default_RSS_Feed.this , getString(R.string.added_rss_link) + RssLink.getText().toString()  , Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    private void AddRSS(String link) {
        mDatabaseReference.child(uid).child("CustomRSSFeed").child("DefaultRSSLink").setValue(link);
    }

}
