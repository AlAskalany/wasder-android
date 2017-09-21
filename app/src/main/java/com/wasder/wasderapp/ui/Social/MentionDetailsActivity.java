package com.wasder.wasderapp.ui.Social;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.EventItem;

public class MentionDetailsActivity
        extends BaseDetailsActivity {

    public static final String ARG_EVENT_ITEM = "event_item";
    private EventItem eventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.event_toolbar);
        toolbar.setTitle("Mention");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView imageView = findViewById(R.id.activity_feed_imageView);
        TextView textView = findViewById(R.id.activity_feed_textView);
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
