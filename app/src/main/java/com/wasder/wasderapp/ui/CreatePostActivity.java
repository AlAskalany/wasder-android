package com.wasder.wasderapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amplitude.api.Amplitude;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.wasder.wasderapp.FeedPostService;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.WasderPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
	private ImageView mPostImage;
	private FrameLayout postImageContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);
		
		postImageContainer = findViewById(R.id.post_image_container);
		postImageContainer.setVisibility(View.INVISIBLE);
		
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
		
		mPostImage = findViewById(R.id.post_image);
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
		
		getMenuInflater().inflate(R.menu.create_post, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		if (!mTextExists) {
			menu.findItem(R.id.action_post).setEnabled(false);
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
			case R.id.action_post:
				// Create a new dispatcher using the Google Play driver.
				//createPost(mImage);
				finish();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createPost(Uri image) {
		
		FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
		Bundle extras = new Bundle();
		String imageUri = (image != null) ? image.toString() : null;
		extras.putString("image_uri", imageUri);
		extras.putString("text", mEditText.getText().toString());
		Job myJob = dispatcher.newJobBuilder().setService(FeedPostService.class) // the JobService that will be called
				.setExtras(extras)              // put extras
				.setTag("my-unique-tag")        // uniquely identifies the job
				.setLifetime(Lifetime.UNTIL_NEXT_BOOT) //
				.setTrigger(Trigger.executionWindow(0, 60)) //
				.setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL) //
				.setReplaceCurrent(false) //
				.setRecurring(false) // one time job
				.build();
		
		dispatcher.mustSchedule(myJob);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == RESULT_OK) {
				mImage = data != null ? data.getData() : null;
				try {
					Bitmap bitmap = getThumbnail(mImage);
					if (bitmap != null) {
						postImageContainer.setVisibility(View.VISIBLE);
						mPostImage.setImageBitmap(bitmap);
						ImageButton removeImageButton = (ImageButton) findViewById(R.id.remove_image_button);
						removeImageButton.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								
								mPostImage.setImageBitmap(null);
								postImageContainer.setVisibility(View.INVISIBLE);
							}
						});
					}
					//Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Bitmap getThumbnail(Uri uri) throws IOException {
		
		Bitmap bitmap;
		BitmapFactory.Options onlyBoundsOptions;
		InputStream input = this.getContentResolver().openInputStream(uri);
		try {
			onlyBoundsOptions = new BitmapFactory.Options();
			onlyBoundsOptions.inJustDecodeBounds = true;
			onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
			BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		} finally {
			if (input != null) {
				input.close();
			}
		}
		if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
			return null;
		}
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
		input = this.getContentResolver().openInputStream(uri);
		try {
			bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return bitmap;
	}
}
