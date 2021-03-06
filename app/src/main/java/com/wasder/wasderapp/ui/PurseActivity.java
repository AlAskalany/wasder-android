package com.wasder.wasderapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ServletPostAsyncTask;

public class PurseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purse);
		Toolbar toolbar = findViewById(R.id.activity_purse_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setTitle("Purse");
		}
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		new ServletPostAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
	}
}
