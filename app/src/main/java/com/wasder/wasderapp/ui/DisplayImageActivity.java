package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.util.Helpers;

public class DisplayImageActivity extends AppCompatActivity {
	
	private static final String ARG_DATA_ITEM = "data_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_image);
		ImageView displayImage = findViewById(R.id.display_image);
		
		if (getIntent().getExtras().containsKey(ARG_DATA_ITEM)) {
			String imageUrl = getIntent().getExtras().getString(ARG_DATA_ITEM);
			if (imageUrl != null) {
				Helpers.Firebase.DownloadUrlImage(imageUrl, displayImage, true, R.drawable.event_pic);
			}
		}
	}
}
