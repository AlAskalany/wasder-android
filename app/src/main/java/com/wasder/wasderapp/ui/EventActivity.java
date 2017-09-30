package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.FeedItem;
import com.wasder.wasderapp.util.Helpers;

public class EventActivity extends AppCompatActivity {
	
	private static final String ARG_EVENT = "event";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		if (getIntent().getExtras().containsKey(ARG_EVENT)) {
			FeedItem feedItem = (FeedItem) getIntent().getExtras().getSerializable(ARG_EVENT);
			ImageView imageView = findViewById(R.id.app_bar_image);
			String imageUrl = null;
			if (feedItem != null) {
				imageUrl = feedItem.getImageUrl();
			}
			if (imageUrl != null && imageView != null) {
				Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, true, R.drawable.event_pic);
			}
			if (actionBar != null) {
				if (feedItem != null) {
					actionBar.setTitle(feedItem.getTitle());
				}
			}
			TextView textView1 = findViewById(R.id.textView16);
			if (feedItem != null) {
				textView1.setText(feedItem.getSubhead());
			}
			TextView textView2 = findViewById(R.id.textView15);
			textView2.setText(feedItem.getSupplementaryText());
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}
}
