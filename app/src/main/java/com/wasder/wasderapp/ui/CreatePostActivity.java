package com.wasder.wasderapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.amplitude.api.Amplitude;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.WasderPreferences;
import com.wasder.wasderapp.models.FeedItem;

public class CreatePostActivity extends AppCompatActivity {
	
	private final String MESSAGES_CHILD = "feed";
	private static final int DEFAULT_MSG_LENGTH_LIMIT = 40000;
	@SuppressWarnings("unused")
	public final String ANONYMOUS = "anonymous";
	private final int REQUEST_IMAGE = 1;
	@SuppressWarnings("unused")
	private final String MESSAGE_SENT_EVENT = "message_sent";
	@SuppressWarnings("unused")
	private final String MESSAGE_URL = "http://friendlychat.firebase.google.com/message/";
	private static final String TAG = "CreatePostActivity";
	private EditText mEditText;
	private FirebaseUser mUser;
	private String mPhotoUrl;
	private Uri mImage;
	private boolean mTextExists;
	private String imageUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);
		Amplitude.getInstance().initialize(this, "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getApplication());
		Amplitude.getInstance().logEvent("Started_Create_Post_Activity");
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		// Initialize Firebase Auth
		FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
		mUser = mFirebaseAuth.getCurrentUser();
		
		if (mUser == null) {
			// Not signed in, launch the Sign In activity
			startActivity(new Intent(this, LoginActivity.class));
			finish();
			return;
		} else {
			String mUsername = mUser.getDisplayName();
			if (mUser.getPhotoUrl() != null) {
				mPhotoUrl = mUser.getPhotoUrl().toString();
			}
		}
		Toolbar toolbar = findViewById(R.id.create_post_toolbar);
		toolbar.setTitle("Posts");
		
		setSupportActionBar(toolbar);
		ActionBar bar = getSupportActionBar();
		if (bar != null) {
			bar.setDisplayHomeAsUpEnabled(true);
			bar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
		}
		mEditText = findViewById(R.id.post_editText);
		mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mSharedPreferences.getInt(WasderPreferences.FRIENDLY_MSG_LENGTH,
				DEFAULT_MSG_LENGTH_LIMIT))});
		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (charSequence.toString().trim().length() > 0) {
					mTextExists = true;
					invalidateOptionsMenu();
				} else {
					mTextExists = false;
					invalidateOptionsMenu();
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
				
			}
		});
		
		
		ImageButton matchImageButton = findViewById(R.id.add_image_imageButton);
		matchImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent;
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
					intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent.setType("image/*");
					startActivityForResult(intent, REQUEST_IMAGE);
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_post, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		if (!mTextExists) {
			menu.findItem(R.id.action_post).setEnabled(false);
			//menu.findItem(R.id.action_post).getIcon().setAlpha();
			Drawable drawable = menu.findItem(R.id.action_post).getIcon();
			drawable.mutate();
			drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
		} else {
			menu.findItem(R.id.action_post).setEnabled(true);
			Drawable drawable = menu.findItem(R.id.action_post).getIcon();
			drawable.mutate();
			drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		switch (id) {
			// If post menu item was selected
			case R.id.action_post:
				String uId = mUser.getUid();
				// check of an image was selected
				if (mImage != null) {
					// if an image was selected, then upload it to the storage
					StorageReference storageReference = FirebaseStorage.getInstance().getReference();
					StorageReference userStorageReference = storageReference.child(uId);
					StorageReference postStorageReference = userStorageReference.child("post");
					StorageReference ref = postStorageReference.child(mImage.getLastPathSegment());
					
					UploadTask uploadTask = ref.putFile(mImage);
					uploadTask.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							
							Log.d(TAG, "Image upload was unsuccessful");
						}
					}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
							
							Uri downloadUrl = taskSnapshot.getDownloadUrl();
						}
					}).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
							
							if (task.isSuccessful()) {
								imageUrl = task.getResult().getDownloadUrl().toString();
								PutPostInDatabase(imageUrl);
							}
						}
					});
				} else {
					PutPostInDatabase(null);
				}
				
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == RESULT_OK) {
				mImage = data != null ? data.getData() : null;
			}
		}
	}
	
	private void PutPostInDatabase(@Nullable String imageUrl) {
		// Create feed item, with image storage download url or null
		String uId = mUser.getUid();
		String text = mEditText.getText().toString();
		FeedItem feedItem = new FeedItem(uId, text, text, text, mPhotoUrl, imageUrl, text);
		// Add the feed item to database
		DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
		databaseReference.child(MESSAGES_CHILD).push().setValue(feedItem, new DatabaseReference.CompletionListener() {
			@Override
			public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
				
				if (databaseError == null) {
					onBackPressed();
				}
				// if adding the item to the database was unsuccessful, warn user
				else {
					Log.d(TAG, "Post creation was unsuccessful");
				}
			}
		});
	}
}
