package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.R;

public class StudioActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studio);
		Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
		Amplitude.getInstance().logEvent("Started_Studio_Activity");
	}
}
