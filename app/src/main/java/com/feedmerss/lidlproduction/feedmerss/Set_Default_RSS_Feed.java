package com.feedmerss.lidlproduction.feedmerss;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

    private  String RSS_link = "https://www.huffingtonpost.com/section/health/feed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_default_rss_feed);

        Toolbar toolbar = (Toolbar)findViewById(R.id.custom_actionbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Default_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Save = findViewById(R.id.BSave);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                switch (pos){
                    case 0:
                        RSS_link="https://www.huffingtonpost.com/section/health/feed";
                        break;

                    case 1:
                        RSS_link="https://www.huffingtonpost.com/section/sports/feed";
                        break;

                    case 2:
                        RSS_link="https://www.huffingtonpost.com/topic/film/feed";
                        break;

                    case 3:
                        RSS_link="https://www.huffingtonpost.com/section/money/feed";
                        break;

                    case 4:
                        RSS_link="https://www.huffingtonpost.com/section/politics/feed";
                        break;

                    case 5:
                        RSS_link="https://www.huffingtonpost.com/topic/weather/feed";
                        break;

                    case 6:
                        RSS_link="https://www.huffingtonpost.com/section/celebrity/feed";
                        break;

                    case 7:
                        RSS_link="https://www.huffingtonpost.com/topic/theatre/feed";
                        break;

                    case 8:
                        RSS_link="https://www.huffingtonpost.com/section/travel/feed";
                        break;

                    case 9:
                        RSS_link="https://www.huffingtonpost.com/topic/animals/feed";
                        break;
                }

                AddRSS(RSS_link);

                Toast.makeText(parent.getContext(), "The selected RSS Feed is " +
                        parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }});


        Toast.makeText(Set_Default_RSS_Feed.this , "The selected RSS Feed is " + spinner.getItemAtPosition(SetDefaultAsSelected(spinner)).toString() , Toast.LENGTH_LONG).show();

    }

    private void AddRSS(String link) {
        mDatabaseReference.child(uid).child("CustomRSSFeed").child("DefaultRSSLink").setValue(link);
    }

    private int SetDefaultAsSelected(Spinner spinner){
        int pos = 0;

        mDatabaseReference.child(uid).child("CustomRSSFeed").child("DefaultRSSLink").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            RSS_link = dataSnapshot.getValue(String.class);
                            Log.e("db",RSS_link);
                        } else
                        {
                            RSS_link = "https://www.huffingtonpost.com/section/health/feed";
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Set_Default_RSS_Feed.this , getString(R.string.error_db_set_feed)  , Toast.LENGTH_LONG).show();
                        RSS_link = "https://www.huffingtonpost.com/section/health/feed";
                    }
                });


                switch (RSS_link){
                    case "https://www.huffingtonpost.com/section/health/feed":
                        pos = 0;
                        spinner.setSelection(0);
                        break;

                    case "https://www.huffingtonpost.com/section/sports/feed":
                        pos = 1;
                        spinner.setSelection(1);
                        break;

                    case "https://www.huffingtonpost.com/topic/film/feed":
                        pos = 2;
                        spinner.setSelection(2);
                        break;

                    case "https://www.huffingtonpost.com/section/money/feed":
                        pos = 3;
                        spinner.setSelection(3);
                        break;

                    case "https://www.huffingtonpost.com/section/politics/feed":
                        pos = 4;
                        spinner.setSelection(4);
                        break;

                    case "https://www.huffingtonpost.com/topic/weather/feed":
                        pos = 5;
                        spinner.setSelection(5);
                        break;

                    case "https://www.huffingtonpost.com/section/celebrity/feed":
                        pos = 6;
                        spinner.setSelection(6);
                        break;

                    case "https://www.huffingtonpost.com/topic/theatre/feed":
                        pos = 7;
                        spinner.setSelection(7);
                        break;

                    case "https://www.huffingtonpost.com/section/travel/feed":
                        pos = 8;
                        spinner.setSelection(8);
                        break;

                    case "https://www.huffingtonpost.com/topic/animals/feed":
                        pos = 9;
                        spinner.setSelection(9);
                        break;
                }
                Log.e("case",String.valueOf(pos));
                return pos;

    }
}

