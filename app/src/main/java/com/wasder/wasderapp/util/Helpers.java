package com.wasder.wasderapp.util;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class Helpers {
	
	public static class Firebase {
		
		private static final String TAG = "FirebaseHelper";
		
		public static void DownloadUrlImage(final String imageUrl, final ImageView imageView, boolean replace, int resImage) {
			
			if (imageUrl != null) {
				if (imageUrl.startsWith("gs://")) {
					StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
					storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
						
						@Override
						public void onComplete(@NonNull Task<Uri> task) {
							
							if (task.isSuccessful()) {
								String downloadUrl = task.getResult().toString();
								Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
							} else {
								Log.d(TAG, "Getting Download URL was not successful", task.getException());
							}
						}
					});
				} else {
					Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
				}
			} else {
				if (replace) {
					Drawable defaultImage = (Drawable) imageView.getContext().getDrawable(resImage);
					if (defaultImage != null) {
						imageView.setImageDrawable(defaultImage);
					} else {
						imageView.setVisibility(ImageView.GONE);
					}
				} else {
					imageView.setVisibility(ImageView.GONE);
				}
			}
		}
	}
	
}