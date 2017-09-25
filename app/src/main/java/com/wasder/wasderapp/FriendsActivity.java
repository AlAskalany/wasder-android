package com.wasder.wasderapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = findViewById(R.id.fragment_friends_toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
	    if (bar != null) {
		    bar.setDisplayHomeAsUpEnabled(true);
	    }
    }

}
