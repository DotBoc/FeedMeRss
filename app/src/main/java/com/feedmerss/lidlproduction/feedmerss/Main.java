package com.feedmerss.lidlproduction.feedmerss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.feedmerss.lidlproduction.feedmerss.Adapter.FeedAdapter;
import com.feedmerss.lidlproduction.feedmerss.Model.RSSObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Main extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private String uid;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    RecyclerView recyclerView;
    private static String FIREBASE_URL = "https://feedmerss-4580c.firebaseio.com/";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";
    static String RSS_link = "https://www.huffingtonpost.com/section/health/feed";
    RSSObject rssObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        mDatabaseReference.child(uid).child("CustomRSSFeed").child("DefaultRSSLink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    RSS_link = dataSnapshot.getValue(String.class);
                } else {
                    Toast.makeText(Main.this , getString(R.string.error_didnt_find)  , Toast.LENGTH_LONG).show();
                    RSS_link = "https://www.huffingtonpost.com/section/health/feed";
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main.this , getString(R.string.error_db_set_feed)  , Toast.LENGTH_LONG).show();
                RSS_link = "https://www.huffingtonpost.com/section/health/feed";

            }
        });

        Toolbar toolbar = findViewById(R.id.custom_actionbar_with_components);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerlayout= findViewById(R.id.drawer_layout);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerlayout, toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.e("Msgopened",RSS_link);
            }



            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                loadRSS();
                Log.e("Msgclosed",RSS_link);
            }


        };



        mDrawerlayout.setDrawerListener(mActionBarDrawerToggle);


        mDrawerlayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);



        loadRSS();

    }




    void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(Main.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
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

            case R.id.Set_Default_RSS_Feed:
                gotoActivity(Set_Default_RSS_Feed.class);
                break;

            case R.id.upload:
                gotoActivity(UploadActivity.class);
                break;

            case R.id.help:
                gotoActivity(Help.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoActivity(Class nextClass){
        Intent intent = new Intent(Main.this ,nextClass);
        startActivity(intent);

    }
}
