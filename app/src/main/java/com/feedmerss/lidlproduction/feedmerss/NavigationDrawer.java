package com.feedmerss.lidlproduction.feedmerss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.feedmerss.lidlproduction.feedmerss.Main;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NavigationDrawer extends Fragment implements View.OnClickListener {

    private DatabaseReference mDatabaseReference;
    private String uid;

    private Button BTHealth,BTSports,BTFilms,BTFinance,BTPolitics,BTWeather,BTLifestyle,BTTheatre,BTTravel,BTAnimals;
    private TextView TVEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_navigation_drawer, container,false);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("/users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        TVEmail = myInflatedView.findViewById(R.id.TVEmail);

        BTHealth = myInflatedView.findViewById(R.id.BTHealth);
        BTHealth.setOnClickListener(this);
        setVisibility(BTHealth,"Health");


        BTSports = myInflatedView.findViewById(R.id.BTSports);
        BTSports.setOnClickListener(this);
        setVisibility(BTSports,"Sports");


        BTFilms = myInflatedView.findViewById(R.id.BTFilms);
        BTFilms.setOnClickListener(this);
        setVisibility(BTFilms,"Films");


        BTFinance = myInflatedView.findViewById(R.id.BTFinance);
        BTFinance.setOnClickListener(this);
        setVisibility(BTFinance,"Finance");


        BTPolitics = myInflatedView.findViewById(R.id.BTPolitics);
        BTPolitics.setOnClickListener(this);
        setVisibility(BTPolitics,"Politics");


        BTWeather = myInflatedView.findViewById(R.id.BTWeather);
        BTWeather.setOnClickListener(this);
        setVisibility(BTWeather,"Weather");


        BTLifestyle = myInflatedView.findViewById(R.id.BTLifestyle);
        BTLifestyle.setOnClickListener(this);
        setVisibility(BTLifestyle,"Lifestyle");


        BTTheatre = myInflatedView.findViewById(R.id.BTTheatre);
        BTTheatre.setOnClickListener(this);
        setVisibility(BTTheatre,"Theater");


        BTTravel = myInflatedView.findViewById(R.id.BTTravel);
        BTTravel.setOnClickListener(this);
        setVisibility(BTTravel,"Travel");


        BTAnimals = myInflatedView.findViewById(R.id.BTAnimals);
        BTAnimals.setOnClickListener(this);
        setVisibility(BTAnimals,"Animals");


        String email =  user.getEmail();
        TVEmail.setText(email);
        return myInflatedView;
    }

   @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.BTHealth) {

            Main.RSS_link="https://www.huffingtonpost.com/section/health/feed";

        } else if (i == R.id.BTSports) {

            Main.RSS_link="https://www.huffingtonpost.com/section/sports/feed";

        } else if (i == R.id.BTFilms) {

            Main.RSS_link="https://www.huffingtonpost.com/topic/film/feed";

        } else if (i == R.id.BTFinance) {

            Main.RSS_link="https://www.huffingtonpost.com/section/money/feed";

        } else if (i == R.id.BTPolitics) {

            Main.RSS_link="https://www.huffingtonpost.com/section/politics/feed";

        } else if (i == R.id.BTWeather) {

            Main.RSS_link="https://www.huffingtonpost.com/topic/weather/feed";

        } else if (i == R.id.BTLifestyle) {

            Main.RSS_link="https://www.huffingtonpost.com/section/celebrity/feed";

        }  else if (i == R.id.BTTheatre) {

            Main.RSS_link="https://www.huffingtonpost.com/topic/theatre/feed";

        } else if (i == R.id.BTTravel) {

            Main.RSS_link="https://www.huffingtonpost.com/section/travel/feed";

        } else if (i == R.id.BTAnimals) {

            Main.RSS_link="https://www.huffingtonpost.com/topic/animals/feed";

        }
    }

    private void setVisibility(final Button button , String id){

        mDatabaseReference.child(uid).child("BasicRSSFeeds").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    button.setVisibility(View.VISIBLE);
                }
                else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                button.setVisibility(View.GONE);
            }
        });
    }
}
