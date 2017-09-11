package com.wasder.wasderapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasder.wasderapp.R;

import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Toolbar toolbar = findViewById(R.id.toolbar_profile);
		toolbar.setTitleTextColor(Color.WHITE);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_profile);
		collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		
		FirebaseAuth mAuth = FirebaseAuth.getInstance();
		FirebaseUser user = mAuth.getCurrentUser();
		ImageView imageView = findViewById(R.id.app_bar_image);
		Uri imageUri = user.getPhotoUrl();
		new DownloadImageTask((ImageView) findViewById(R.id.app_bar_image)).execute(imageUri.toString());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		switch (id) {
			case R.id.homeAsUp:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		
		ImageView bmImage;
		
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
