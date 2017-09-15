package com.wasder.wasderapp.ui.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.CreatorFeedItem;
import com.wasder.wasderapp.util.Helpers;

public class CreatorFeedActivity
		extends BaseActivity {
	
	public static final String ARG_CREATOR_FEED_ITEM = "creator_feed_item";
	private CreatorFeedItem creatorFeedItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator_feed);
		Toolbar toolbar = findViewById(R.id.creator_feed_toolbar);
		toolbar.setTitle("Creator Feed");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_CREATOR_FEED_ITEM)) {
			creatorFeedItem = (CreatorFeedItem) getIntent().getExtras()
			                                               .getSerializable(ARG_CREATOR_FEED_ITEM);
			getSupportActionBar().setTitle(creatorFeedItem.getTitle());
			final String imageUrl = creatorFeedItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(creatorFeedItem.getSupplementaryText());
		}
	}
}
