package com.wasder.wasderapp.ui.home;

import android.os.Bundle;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;

public class FeedDetailsActivity<FeedItem>
		extends BaseDetailsActivity {
	
	private int layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		this.SetupActionBar();
		//this.SetActionBarTitle();
	}
}
