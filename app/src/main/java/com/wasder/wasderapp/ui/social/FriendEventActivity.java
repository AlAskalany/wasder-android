package com.wasder.wasderapp.ui.social;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;

public class FriendEventActivity
		extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_event);
		Toolbar toolbar = findViewById(R.id.friend_event_toolbar);
		toolbar.setTitle("Friend Event");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
