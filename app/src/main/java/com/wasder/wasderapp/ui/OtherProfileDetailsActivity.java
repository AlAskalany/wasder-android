package com.wasder.wasderapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;
import com.wasder.wasderapp.util.Helpers;

import java.io.InputStream;

public class OtherProfileDetailsActivity extends BaseDetailsActivity {
	
	private final String ARG_DATA_ITEM = "data_item";
	private final String ARG_PHOTO_ITEM = "photo_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_profile);
		Toolbar toolbar = findViewById(R.id.toolbar_other_profile);
		toolbar.setTitleTextColor(Color.WHITE);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_other_profile);
		collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
		}
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		/*FirebaseAuth mAuth = FirebaseAuth.getInstance();
		FirebaseUser user = mAuth.getCurrentUser();
		findViewById(R.id.app_bar_image);
		Uri imageUri = null;
		if (user != null) {
			imageUri = user.getPhotoUrl();
		}
		if (imageUri != null) {
			new DownloadImageTask((ImageView) findViewById(R.id.app_bar_image)).execute(imageUri.toString());
		}*/
		
		if (getIntent().getExtras().containsKey(ARG_DATA_ITEM)) {
			String name = getIntent().getExtras().getString(ARG_DATA_ITEM);
			actionBar.setTitle(name);
			String photoUrl = getIntent().getExtras().getString(ARG_PHOTO_ITEM);
			ImageView profileImageView = findViewById(R.id.profileImage);
			Helpers.Firebase.DownloadUrlImage(photoUrl, profileImageView, true, R.drawable.avatar);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		switch (id) {
			case R.id.homeAsUp:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case android.R.id.home:
				onBackPressed();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		
		final ImageView bmImage;
		
		public DownloadImageTask(ImageView bmImage) {
			
			this.bmImage = bmImage;
		}
		
		@Override
		protected Bitmap doInBackground(String... urls) {
			
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}
		
		protected void onPostExecute(Bitmap result) {
			
			bmImage.setImageBitmap(result);
		}
	}
}
