package com.wasder.wasderapp.ui.home;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;
import com.wasder.wasderapp.models.CreatorFeedItem;
import com.wasder.wasderapp.util.Helpers;

public class CreatorFeedDetailsActivity
		extends BaseDetailsActivity {
	
	private static final String ARG_CREATOR_FEED_ITEM = "creator_feed_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creator_feed);
		Toolbar toolbar = findViewById(R.id.creator_feed_toolbar);
		toolbar.setTitle("Creator Feed");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_CREATOR_FEED_ITEM)) {
			CreatorFeedItem creatorFeedItem = (CreatorFeedItem) getIntent().getExtras()
					.getSerializable(ARG_CREATOR_FEED_ITEM);
			if (creatorFeedItem != null) {
				getSupportActionBar().setTitle(creatorFeedItem.getTitle());
			}
			String imageUrl = null;
			if (creatorFeedItem != null) {
				imageUrl = creatorFeedItem.getImageUrl();
			}
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(creatorFeedItem.getSupplementaryText());
		}
	}
}
