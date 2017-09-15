package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

public class TwitchStreamActivity
		extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitch_stream);
		Toolbar toolbar = findViewById(R.id.twitch_stream_toolbar);
		toolbar.setTitle("Twitch Stream");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
