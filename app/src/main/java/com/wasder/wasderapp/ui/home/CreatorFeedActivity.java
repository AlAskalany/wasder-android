package com.wasder.wasderapp.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

public class CreatorFeedActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator_feed);
		String s = getIntent().getStringExtra("Name");
		Toolbar toolbar = findViewById(R.id.toolbar8);
		toolbar.setTitle(s);
	}
}
