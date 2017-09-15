package com.wasder.wasderapp.ui.live;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.EsportsItem;
import com.wasder.wasderapp.util.Helpers;

public class EsportsActivity
		extends BaseActivity {
	
	public static final String ARG_ESPORTS_ITEM = "esports_item";
	private EsportsItem esportsItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_esports);
		Toolbar toolbar = findViewById(R.id.esports_toolbar);
		toolbar.setTitle("Esports");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageView imageView = findViewById(R.id.activity_feed_imageView);
		TextView textView = findViewById(R.id.activity_feed_textView);
		if (getIntent().getExtras()
		               .containsKey(ARG_ESPORTS_ITEM)) {
			esportsItem = (EsportsItem) getIntent().getExtras()
			                                       .getSerializable(ARG_ESPORTS_ITEM);
			getSupportActionBar().setTitle(esportsItem.getTitle());
			final String imageUrl = esportsItem.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
			textView.setText(esportsItem.getSupplementaryText());
		}
	}
}
