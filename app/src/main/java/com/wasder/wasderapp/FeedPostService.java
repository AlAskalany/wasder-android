package com.wasder.wasderapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.annotation.GlideModule;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wasder.wasderapp.models.FeedItem;


/**
 * Created by Ahmed AlAskalany on 10/3/2017.
 * Project: wasder-android
 * Package: ${PACKAGE_NAME}
 */
public class FeedPostService extends JobService {
	
	private static final String TAG = FeedPostService.class.getSimpleName();
	private static final String ARG_IMAGE_URI = "image_uri";
	private static final String ARG_TEXT = "text";
	private String text;
	Uri downloadUrl;
	
	@Override
	public boolean onStartJob(final JobParameters job) {
		// Do some work here
		/*Uri mImageUri = Uri.parse(job.getExtras().getString(ARG_IMAGE_URI));
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		String uId = user.getUid();
		StorageReference storageReference = FirebaseStorage.getInstance().getReference();
		StorageReference ref = storageReference.child(uId).child("post").child(mImageUri.getLastPathSegment());
		UploadTask uploadTask = ref.putFile(mImageUri);
		uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
				
				if (task.isSuccessful()) {
					downloadUrl = task.getResult().getDownloadUrl();
					//imageUrl = downloadUrl != null ? downloadUrl.toString() : null;
				} else {
					Log.d(TAG, "Image upload was unsuccessful");
				}
			}
		});
		
		// Create feed item, with image storage download url or null
		text = job.getExtras().getString(ARG_TEXT);
		String photoUrl = null;
		Uri photo = user.getPhotoUrl();
		photoUrl = photo.toString();
		String postImageDownloadUrl = downloadUrl != null ? downloadUrl.toString() : null;
		FeedItem feedItem = new FeedItem(uId, text, text, text, photoUrl, postImageDownloadUrl, text);
		// Add the feed item to database
		DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child("feed").push().setValue(feedItem, new DatabaseReference.CompletionListener() {
			@Override
			public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
				
				if (databaseError == null) {
					//onBackPressed();
				}
				// if adding the item to the database was unsuccessful, warn user
				else {
					Log.d(TAG, "Post creation was unsuccessful");
				}
			}
		});*/
		
		return false; // Answers the question: "Is there still work going on?"
	}
	
	@Override
	public boolean onStopJob(JobParameters job) {
		
		return false; // Answers the question: "Should this job be retried?"
	}
}
