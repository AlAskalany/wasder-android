package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wasder.wasderapp.R;

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
    }
}
