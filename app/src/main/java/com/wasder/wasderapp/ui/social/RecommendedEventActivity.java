package com.wasder.wasderapp.ui.social;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;

public class RecommendedEventActivity
		extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommended_event);
		Toolbar toolbar = findViewById(R.id.recommended_events_toolbar);
		toolbar.setTitle("Recommended Event");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
