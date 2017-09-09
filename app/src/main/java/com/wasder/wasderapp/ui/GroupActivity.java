package com.wasder.wasderapp.ui;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

public class GroupActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_group_activity);
		toolbar.setTitleTextColor(Color.WHITE);
		CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
		collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
		collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayShowHomeEnabled(true);
			actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
			//actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
