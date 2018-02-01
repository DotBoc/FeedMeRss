package com.feedmerss.lidlproduction.feedmerss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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
        t.setText("Text to Display");
        return myInflatedView;
    }

}
