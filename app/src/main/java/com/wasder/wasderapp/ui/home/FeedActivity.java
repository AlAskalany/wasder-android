package com.wasder.wasderapp.ui.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

public class FeedActivity
		extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		Toolbar toolbar = findViewById(R.id.feed_toolbar);
		toolbar.setTitle("Account");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
