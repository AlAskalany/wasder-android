package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.R;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
	    Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
	    Amplitude.getInstance().logEvent("Started_Friends_Activity");
        Toolbar toolbar = findViewById(R.id.fragment_friends_toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
	    if (bar != null) {
		    bar.setDisplayHomeAsUpEnabled(true);
	    }
    }

}
