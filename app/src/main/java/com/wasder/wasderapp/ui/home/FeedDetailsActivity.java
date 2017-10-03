package com.wasder.wasderapp.ui.home;

import android.os.Bundle;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;

public class FeedDetailsActivity<FeedItem>
		extends BaseDetailsActivity {
	
	@SuppressWarnings("unused")
	private int layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
		Amplitude.getInstance().logEvent("Started_Feed_Details_Activity");
		this.SetupActionBar();
		//this.SetActionBarTitle();
	}
}
