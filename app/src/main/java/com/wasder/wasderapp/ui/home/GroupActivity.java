package com.wasder.wasderapp.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;

public class GroupActivity
		extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		Toolbar toolbar = findViewById(R.id.toolbar_group_activity);
		toolbar.setTitleTextColor(Color.WHITE);
		CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
		collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
		collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			//actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
