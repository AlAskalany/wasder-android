package com.wasder.wasderapp.ui.social;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.EventItem;
import com.wasder.wasderapp.util.Helpers;

public class EventActivity
		extends BaseActivity {
	
	public static final String ARG_EVENT_ITEM = "event_item";
	private EventItem eventItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		Toolbar toolbar = findViewById(R.id.event_toolbar);
		toolbar.setTitle("Event");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_EVENT_ITEM)) {
			eventItem = (EventItem) getIntent().getExtras()
			                                   .getSerializable(ARG_EVENT_ITEM);
			getSupportActionBar().setTitle(eventItem.getTitle());
			final String imageUrl = eventItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(eventItem.getSupplementaryText());
		}
	}
}
