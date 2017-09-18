package com.wasder.wasderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.Interfaces.WasderDataModel;
import com.wasder.wasderapp.util.Helpers;

public class BaseDetailsActivity<T extends WasderDataModel>
		extends AppCompatActivity {
	
	public static final String ARG_DATA_ITEM = "data_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
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
	
	protected void SetActionBarTitle() {
		
		if (getIntent().getExtras()
		               .containsKey(ARG_DATA_ITEM)) {
			Bundle extraData = getIntent().getExtras();
			T dataItem;
			if (extraData != null) {
				dataItem = (T) extraData.getSerializable(ARG_DATA_ITEM);
				ActionBar actionBar = getSupportActionBar();
				if (actionBar != null) {
					actionBar.setTitle((dataItem != null) ? dataItem.getTitle() : "");
				}
				ImageView imageView = findViewById(R.id.activity_feed_imageView);
				TextView textView = findViewById(R.id.activity_feed_textView);
				final String imageUrl;
				if (dataItem != null) {
					imageUrl = dataItem.getImageUrl();
					Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
				}
				
				if (dataItem != null) {
					textView.setText(dataItem.getSupplementaryText());
				}
			}
		}
	}
	
	protected void SetupActionBar(String title, int toolbarRes) {
		
		Toolbar toolbar = findViewById(toolbarRes);
		toolbar.setTitle(title);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
