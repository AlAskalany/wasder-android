package com.wasder.wasderapp.ui.home;

import android.os.Bundle;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;

public class FeedDetailsActivity<FeedItem>
		extends BaseDetailsActivity {
	
	private int layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		this.SetupActionBar("Feed", R.id.feed_toolbar);
		this.SetActionBarTitle();
	}
}
