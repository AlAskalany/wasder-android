package com.wasder.wasderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity
		extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		Toolbar mToolbar = findViewById(R.id.toolbar_base);
		setSupportActionBar(mToolbar);
		ActionBar mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
	}
	
	public boolean onSupportNavigateUp() {
		
		Intent upIntent = getSupportParentActivityIntent();
		if (upIntent != null) {
			// Do not reload previous activity when home back button is pressed
			upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			if (supportShouldUpRecreateTask(upIntent)) {
				TaskStackBuilder b = TaskStackBuilder.create(this);
				onCreateSupportNavigateUpTaskStack(b);
				onPrepareSupportNavigateUpTaskStack(b);
				b.startActivities();
				
				try {
					ActivityCompat.finishAffinity(this);
				} catch (IllegalStateException e) {
					// This can only happen on 4.1+, when we don't have a parent or a result set.
					// In that case we should just finish().
					finish();
				}
			} else {
				// This activity is part of the application's task, so simply
				// navigate up to the hierarchical parent activity.
				supportNavigateUpTo(upIntent);
			}
			return true;
		}
		return false;
	}
}
