package com.wasder.wasderapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
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
	
	public static final String MESSAGES_CHILD = "feed";
	public static final int DEFAULT_MSG_LENGTH_LIMIT = 10;
	public static final String ANONYMOUS = "anonymous";
	private static final int REQUEST_INVITE = 1;
	private static final int REQUEST_IMAGE = 2;
	private static final String MESSAGE_SENT_EVENT = "message_sent";
	private static final String MESSAGE_URL = "http://friendlychat.firebase.google.com/message/";
	private static final String LOADING_IMAGE_URL = "https://www.google.com/images/spin-32.gif";
	private static final String TAG = "CreatePostActivity";
	private DatabaseReference mFirebaseDatabaseReference;
	private ImageButton mattachImageButton;
	private EditText mMessageEditText;
	private ImageButton mSendButton;
	private String mUsername = "Askalany";
	private String mPhotoUrl = "https://ubistatic19-a.akamaihd.net/resource/en-us/game/rainbow6/siege/r6-game-info-units_210380.jpg";
	private FirebaseAuth mFirebaseAuth;
	private FirebaseUser mFirebaseUser;
	private SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		// Initialize Firebase Auth
		mFirebaseAuth = FirebaseAuth.getInstance();
		mFirebaseUser = mFirebaseAuth.getCurrentUser();
		
		if (mFirebaseUser == null) {
			// Not signed in, launch the Sign In activity
			startActivity(new Intent(this, LoginActivity.class));
			finish();
			return;
		} else {
			mUsername = mFirebaseUser.getDisplayName();
			if (mFirebaseUser.getPhotoUrl() != null) {
				mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
			}
		}
		mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
		Toolbar toolbar = findViewById(R.id.create_post_toolbar);
		toolbar.setTitle("Post");
		setSupportActionBar(toolbar);
		ActionBar bar = getSupportActionBar();
		if (bar != null) {
			bar.setDisplayHomeAsUpEnabled(true);
		}
		mMessageEditText = findViewById(R.id.post_editText);
		mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mSharedPreferences.getInt(WasderPreferences.FRIENDLY_MSG_LENGTH,
				DEFAULT_MSG_LENGTH_LIMIT))});
		mMessageEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
			}
			
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				
				if (charSequence.toString().trim().length() > 0) {
					mSendButton.setEnabled(true);
				} else {
					mSendButton.setEnabled(false);
				}
			}
			
			@Override
			public void afterTextChanged(Editable editable) {
				
			}
		});
		
		
		mattachImageButton = findViewById(R.id.add_image_imageButton);
		mattachImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = null;
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
					intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent.setType("image/*");
					startActivityForResult(intent, REQUEST_IMAGE);
				}
			}
		});
		
		mSendButton = findViewById(R.id.create_post_send_imageButton);
		mSendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				FeedItem feedItem = new FeedItem(mFirebaseUser.getUid(), mMessageEditText.getText().toString(), mMessageEditText.getText().toString
						(), mMessageEditText.getText().toString(), mPhotoUrl, null, mMessageEditText.getText().toString());
				mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(feedItem);
				mMessageEditText.setText("");
				//mFirebaseAnalytics.logEvent(MESSAGE_SENT_EVENT, null);
			}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);
		
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == RESULT_OK) {
				if (data != null) {
					final Uri uri = data.getData();
					Log.d(TAG, "Uri: " + uri.toString());
					FeedItem tempMessage = new FeedItem(mFirebaseUser.getUid(), mMessageEditText.getText().toString(), mMessageEditText.getText()
							.toString(), mMessageEditText.getText().toString(), mPhotoUrl, LOADING_IMAGE_URL, mMessageEditText.getText().toString());
					mFirebaseDatabaseReference.child(MESSAGES_CHILD).push().setValue(tempMessage, new DatabaseReference.CompletionListener() {
						@Override
						public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
							
							if (databaseError == null) {
								String key = databaseReference.getKey();
								StorageReference storageReference = FirebaseStorage.getInstance().getReference(mFirebaseUser.getUid()).child(key)
										.child(uri.getLastPathSegment());
								
								putImageInStorage(storageReference, uri, key);
							} else {
								Log.w(TAG, "Unable to write message to database.", databaseError.toException());
							}
						}
					});
				}
			}
		} else if (requestCode == REQUEST_INVITE) {
			if (resultCode == RESULT_OK) {
				// Use Firebase Measurement to log that invitation was sent.
				Bundle payload = new Bundle();
				payload.putString(FirebaseAnalytics.Param.VALUE, "inv_sent");
				
				// Check how many invitations were sent and log.
				//String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
				//Log.d(TAG, "Invitations sent: " + ids.length);
			} else {
				// Use Firebase Measurement to log that invitation was not sent
				Bundle payload = new Bundle();
				payload.putString(FirebaseAnalytics.Param.VALUE, "inv_not_sent");
				//mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, payload);
				
				// Sending failed or it was canceled, show failure message to the user
				Log.d(TAG, "Failed to send invitation.");
			}
		}
	}
	
	public boolean onSupportNavigateUp() {
		
		Intent upIntent = getSupportParentActivityIntent();
		if (upIntent != null) {
			// Do not reload previous activity when home back button is pressed
			upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			if (supportShouldUpRecreateTask(upIntent)) {
				TaskStackBuilder b = TaskStackBuilder.create(this);
				onCreateSupportNavigateUpTaskStack(b);
				onPrepareSupportNavigateUpTaskStack(b);
				b.startActivities();
				
				try {
					ActivityCompat.finishAffinity(this);
				} catch (IllegalStateException e) {
					// This can only happen on 4.1+, when we don't have a parent or a result set.
					// In that case we should just finish().
					finish();
				}
			} else {
				// This activity is part of the application's task, so simply
				// navigate up to the hierarchical parent activity.
				supportNavigateUpTo(upIntent);
			}
			return true;
		}
		return false;
	}
	
	private void putImageInStorage(StorageReference storageReference, Uri uri, final String key) {
		
		storageReference.putFile(uri).addOnCompleteListener(CreatePostActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
				
				if (task.isSuccessful()) {
					FeedItem feedItem = new FeedItem(mFirebaseUser.getUid(), mMessageEditText.getText().toString(), mMessageEditText.getText()
							.toString(), mMessageEditText.getText().toString(), mPhotoUrl, task.getResult().getDownloadUrl().toString(),
							mMessageEditText.getText().toString());
					mFirebaseDatabaseReference.child(MESSAGES_CHILD).child(key).setValue(feedItem);
				} else {
					Log.w(TAG, "Image upload task was not successful.", task.getException());
				}
			}
		});
	}
}
