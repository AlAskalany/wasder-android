package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.EsportsItem;
import com.wasder.wasderapp.util.Helpers;

public class EsportsDetailsActivity
		extends BaseDetailsActivity {
	
	private static final String ARG_ESPORTS_ITEM = "esports_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_esports);
		Toolbar toolbar = findViewById(R.id.esports_toolbar);
		toolbar.setTitle("Esports");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_ESPORTS_ITEM)) {
			EsportsItem esportsItem = (EsportsItem) getIntent().getExtras()
					.getSerializable(ARG_ESPORTS_ITEM);
			if (esportsItem != null) {
				getSupportActionBar().setTitle(esportsItem.getTitle());
			}
			String imageUrl = null;
			if (esportsItem != null) {
				imageUrl = esportsItem.getImageUrl();
			}
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(esportsItem.getSupplementaryText());
		}
	}
}
