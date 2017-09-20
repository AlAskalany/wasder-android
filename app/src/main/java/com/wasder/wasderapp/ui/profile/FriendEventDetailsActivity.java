package com.wasder.wasderapp.ui.profile;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.FriendEventItem;
import com.wasder.wasderapp.util.Helpers;

public class FriendEventDetailsActivity
		extends BaseDetailsActivity {
	
	public static final String ARG_FRIEND_EVENT_ITEM = "friend_event_item";
	private FriendEventItem friendEventItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_event);
		Toolbar toolbar = findViewById(R.id.friend_event_toolbar);
		toolbar.setTitle("Friend Event");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_FRIEND_EVENT_ITEM)) {
			friendEventItem = (FriendEventItem) getIntent().getExtras()
			                                               .getSerializable(ARG_FRIEND_EVENT_ITEM);
			getSupportActionBar().setTitle(friendEventItem.getTitle());
			final String imageUrl = friendEventItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(friendEventItem.getSupplementaryText());
		}
	}
}
