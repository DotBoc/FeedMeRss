package com.feedmerss.lidlproduction.feedmerss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.feedmerss.lidlproduction.feedmerss.Main;


public class NavigationDrawer extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_navigation_drawer, container,false);

        // Set the Text to try this out
        TextView t = (TextView) myInflatedView.findViewById(R.id.TVEmail);
        Button BT1 = (Button)  myInflatedView.findViewById(R.id.BT1);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Main.RSS_link="http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml";

            }
        });
        String email =  FirebaseAuth.getInstance().getCurrentUser().getEmail();
        t.setText(email);
        return myInflatedView;
    }


}
