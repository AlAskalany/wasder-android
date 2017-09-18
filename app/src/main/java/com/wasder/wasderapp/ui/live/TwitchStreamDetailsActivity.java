package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.TwitchStreamItem;
import com.wasder.wasderapp.util.Helpers;

public class TwitchStreamDetailsActivity
		extends BaseDetailsActivity {
	
	public static final String ARG_TWITCH_STREAM_ACTIVITY = "twitch_stream_item";
	private TwitchStreamItem twitchStreamItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitch_stream);
		Toolbar toolbar = findViewById(R.id.twitch_stream_toolbar);
		toolbar.setTitle("Twitch Stream");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_TWITCH_STREAM_ACTIVITY)) {
			twitchStreamItem = (TwitchStreamItem) getIntent().getExtras()
			                                                 .getSerializable(ARG_TWITCH_STREAM_ACTIVITY);
			getSupportActionBar().setTitle(twitchStreamItem.getTitle());
			final String imageUrl = twitchStreamItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(twitchStreamItem.getSupplementaryText());
		}
	}
}
