package com.wasder.wasderapp.ui.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.FeedItem;
import com.wasder.wasderapp.util.Helpers;

public class FeedActivity
		extends BaseActivity {
	
	public static final String ARG_FEED_ITEM = "feed_item";
	private FeedItem feedItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		Toolbar toolbar = findViewById(R.id.feed_toolbar);
		toolbar.setTitle("Feed");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_FEED_ITEM)) {
			feedItem = (FeedItem) getIntent().getExtras()
			                                 .getSerializable(ARG_FEED_ITEM);
			getSupportActionBar().setTitle(feedItem.getTitle());
			final String imageUrl = feedItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(feedItem.getSupplementaryText());
		}
	}
}
