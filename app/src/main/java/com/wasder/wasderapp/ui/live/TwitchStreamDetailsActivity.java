package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;
import com.wasder.wasderapp.models.TwitchStreamItem;
import com.wasder.wasderapp.util.Helpers;

public class TwitchStreamDetailsActivity
		extends BaseDetailsActivity {
	
	private static final String ARG_TWITCH_STREAM_ACTIVITY = "twitch_stream_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitch_stream);
		Toolbar toolbar = findViewById(R.id.twitch_stream_toolbar);
		toolbar.setTitle("Twitch Stream");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_TWITCH_STREAM_ACTIVITY)) {
			TwitchStreamItem twitchStreamItem = (TwitchStreamItem) getIntent().getExtras()
					.getSerializable(ARG_TWITCH_STREAM_ACTIVITY);
			if (twitchStreamItem != null) {
				getSupportActionBar().setTitle(twitchStreamItem.getTitle());
			}
			String imageUrl = null;
			if (twitchStreamItem != null) {
				imageUrl = twitchStreamItem.getImageUrl();
			}
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(twitchStreamItem.getSupplementaryText());
		}
	}
}
