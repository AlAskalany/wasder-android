package com.wasder.wasderapp.ui.Messages;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;
import com.wasder.wasderapp.models.EventItem;

public class MentionDetailsActivity
        extends BaseDetailsActivity {

    public static final String ARG_EVENT_ITEM = "event_item";
    private EventItem eventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_mention_details);
	    Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
	    Amplitude.getInstance().logEvent("Started_Mention_Details_Activity");
	    Toolbar toolbar = findViewById(R.id.event_toolbar);
        toolbar.setTitle("Mention");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
	    if (actionBar != null) {
		    actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	
	    findViewById(R.id.activity_feed_imageView);
	    findViewById(R.id.activity_feed_textView);
        /*if (getIntent().getExtras()
                       .containsKey(ARG_EVENT_ITEM)) {
			eventItem = (EventItem) getIntent().getExtras()
			                                   .getSerializable(ARG_EVENT_ITEM);
			getSupportActionBar().setTitle(eventItem.getTitle());
			final String imageUrl = eventItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(eventItem.getSupplementaryText());
		}*/
    }
}
