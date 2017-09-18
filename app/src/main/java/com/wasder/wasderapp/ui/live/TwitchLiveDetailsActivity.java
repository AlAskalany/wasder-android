package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.TwitchLiveItem;
import com.wasder.wasderapp.util.Helpers;

public class TwitchLiveDetailsActivity
		extends BaseDetailsActivity {
	
	public static final String ARG_TWITCH_LIVE_ITEM = "twitch_live_item";
	private TwitchLiveItem twitchLiveItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitch_live);
		Toolbar toolbar = findViewById(R.id.twitch_live_toolbar);
		toolbar.setTitle("Twitch Live");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_TWITCH_LIVE_ITEM)) {
			twitchLiveItem = (TwitchLiveItem) getIntent().getExtras()
			                                             .getSerializable(ARG_TWITCH_LIVE_ITEM);
			getSupportActionBar().setTitle(twitchLiveItem.getTitle());
			final String imageUrl = twitchLiveItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(twitchLiveItem.getSupplementaryText());
		}
	}
}
