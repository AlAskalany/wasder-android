package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

public class FollowersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Toolbar toolbar = findViewById(R.id.fragment_followers_toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
	    if (bar != null) {
		    bar.setDisplayHomeAsUpEnabled(true);
	    }
    }

}
