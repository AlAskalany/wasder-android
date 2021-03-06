package com.wasder.wasderapp.ui.Social;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;
import com.wasder.wasderapp.models.RecommendedEventItem;
import com.wasder.wasderapp.util.Helpers;

public class PMDetailsActivity
        extends BaseDetailsActivity {
	
	private static final String ARG_RECOMMENDED_EVENT_ITEM = "recommended_event_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommended_event);
		Toolbar toolbar = findViewById(R.id.recommended_event_toolbar);
		toolbar.setTitle("Recommended Event");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_RECOMMENDED_EVENT_ITEM)) {
			RecommendedEventItem recommendedEventItem = (RecommendedEventItem) getIntent().getExtras()
					.getSerializable(ARG_RECOMMENDED_EVENT_ITEM);
			if (recommendedEventItem != null) {
				getSupportActionBar().setTitle(recommendedEventItem.getTitle());
			}
			String imageUrl = null;
			if (recommendedEventItem != null) {
				imageUrl = recommendedEventItem.getImageUrl();
			}
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(recommendedEventItem.getSupplementaryText());
		}
	}
}
