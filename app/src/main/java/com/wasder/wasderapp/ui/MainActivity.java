package com.wasder.wasderapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.DummyContent;
import com.wasder.wasderapp.models.FeedModel;
import com.wasder.wasderapp.ui.home.CreatorFeedActivity;
import com.wasder.wasderapp.ui.home.CreatorFeedFragment;
import com.wasder.wasderapp.ui.home.FeedActivity;
import com.wasder.wasderapp.ui.home.FeedFragment;
import com.wasder.wasderapp.ui.home.GroupFragment;
import com.wasder.wasderapp.ui.home.HomeFragment;
import com.wasder.wasderapp.ui.home.models.CreatorFeedContent.CreatorFeedItem;
import com.wasder.wasderapp.ui.live.EsportsActivity;
import com.wasder.wasderapp.ui.live.EsportsFragment;
import com.wasder.wasderapp.ui.live.LiveFragment;
import com.wasder.wasderapp.ui.live.TwitchLiveActivity;
import com.wasder.wasderapp.ui.live.TwitchLiveFragment;
import com.wasder.wasderapp.ui.live.TwitchStreamActivity;
import com.wasder.wasderapp.ui.live.TwitchStreamFragment;
import com.wasder.wasderapp.ui.market.EventActivity;
import com.wasder.wasderapp.ui.market.EventFragment;
import com.wasder.wasderapp.ui.market.FriendEventActivity;
import com.wasder.wasderapp.ui.market.FriendEventFragment;
import com.wasder.wasderapp.ui.market.MarketFragment;
import com.wasder.wasderapp.ui.market.RecommendedEventActivity;
import com.wasder.wasderapp.ui.market.RecommendedEventFragment;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView
		.OnNavigationItemSelectedListener, OnFragmentInteractionListener, FeedFragment
		.OnFeedFragmentInteractionListener, GroupFragment.OnGroupFragmentInteractionListener,
		CreatorFeedFragment.OnCreatorFeedFragmentInteractionListener, TwitchStreamFragment
				.OnTiwtchStreamFragmentInteractionListener, FragmentManager
				.OnBackStackChangedListener, FeedFragment.OnFeedItemShareListener, GroupFragment
				.OnGroupDetailsListener, FeedFragment.OnAvatarListener, CreatorFeedFragment
				.OnAvatarListener, CreatorFeedFragment.OnFeedItemShareListener, TwitchLiveFragment
				.OnTwitchLiveFragmentInteractionListener, EsportsFragment
				.OnEsportsFragmentInteractionListener, EventFragment
				.OnEventFragmentInteractionListener, FriendEventFragment
				.OnFriendEventFragmentInteractionListener, RecommendedEventFragment
				.OnRecommendedEventFragmentInteractionListener {
	
	private static final String TAG = "MainActivity";
	public String mUserName = "User Name";
	public String mEmail = "User E-mail";
	IInAppBillingService mService;
	ServiceConnection mServiceConn = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			mService = IInAppBillingService.Stub.asInterface(service);
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
			mService = null;
		}
	};
	HomeFragment homeFragment;
	LiveFragment liveFragment;
	MarketFragment marketFragment;
	Toolbar toolbar;
	TextView nameTextView;
	TextView detailsTextView;
	private TextView mTextMessage;
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private Uri mPhotoUrl;
	private String mUid;
	private BottomNavigationView.OnNavigationItemSelectedListener
			mOnNavigationItemSelectedListener = new BottomNavigationView
			.OnNavigationItemSelectedListener() {
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ts = fm.beginTransaction();
			Fragment fragment = fm.findFragmentById(R.id.framelayout_fragment_container);
			if (fragment != null) {
				fm.saveFragmentInstanceState(fragment);
			}
			switch (item.getItemId()) {
				case R.id.navigation_home:
					
					if (fragment != homeFragment) {
						ts.replace(R.id.framelayout_fragment_container, homeFragment);
						ts.addToBackStack(null);
						ts.commit();
					}
					return true;
				case R.id.navigation_live:
					if (fragment != liveFragment) {
						ts.replace(R.id.framelayout_fragment_container, liveFragment);
						ts.addToBackStack(null);
						ts.commit();
					}
					return true;
				case R.id.navigation_market:
					if (fragment != marketFragment) {
						ts.replace(R.id.framelayout_fragment_container, marketFragment);
						ts.addToBackStack(null);
						ts.commit();
					}
					return true;
			}
			return false;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		serviceIntent.setPackage("com.android.vending");
		bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context
				.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		//Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_main_linearlayout),
		// "Connection", 2000);
		//snackbar.show();
		if (activeInfo != null && activeInfo.isConnected()) {
			Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();
		}
		// Configure Google Sign In
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail().build();
		
		mAuth = FirebaseAuth.getInstance();
		mAuthListener = new FirebaseAuth.AuthStateListener() {
			
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				
				FirebaseUser user = mAuth.getCurrentUser();
				if (user != null) {
					Log.d(TAG, "Signed in");
					mUserName = user.getDisplayName();
					mEmail = user.getEmail();
					mPhotoUrl = user.getPhotoUrl();
					mUid = user.getUid();
					
					NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
					View headerView = navigationView.getHeaderView(0);
					TextView userNameTextView = (TextView) headerView.findViewById(R.id
							.nav_header_user_name);
					userNameTextView.setText(mUserName);
					TextView emailTextView = (TextView) headerView.findViewById(R.id
							.nav_header_user_details);
					emailTextView.setText(mEmail);
					
					/*UserProfileChangeRequest profilUpdates = new UserProfileChangeRequest
					.Builder().setDisplayName("Ahmed AlAskalany").setPhotoUri
							(Uri.parse("http://www.lovemarks
							.com/wp-content/uploads/profile-avatars/default-avatar-knives-ninja
							.png")).build();
					user.updateProfile(profilUpdates).addOnCompleteListener(new
					OnCompleteListener<Void>() {
						
						@Override
						public void onComplete(@NonNull Task<Void> task) {
							
							if (task.isSuccessful()) {
								Log.d(TAG, "User Profile Updated");
							}
						}
					});*/
					
				} else {
					Log.d(TAG, "Signed out");
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
				}
			}
		};
		homeFragment = new HomeFragment();
		liveFragment = new LiveFragment();
		marketFragment = new MarketFragment();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.framelayout_fragment_container, homeFragment, "Home");
		transaction.commit();
		
		//region Bottom_Navigation
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		//endregion
	}
	
	@Override
	public void onStart() {
		
		super.onStart();
		mAuth.addAuthStateListener(mAuthListener);
	}
	
	@Override
	public void onStop() {
		
		super.onStop();
		if (mAuthListener != null) {
			mAuth.removeAuthStateListener(mAuthListener);
		}
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		if (mService != null) {
			unbindService(mServiceConn);
		}
	}
	
	@Override
	public void onBackPressed() {
		
		Log.d("BackStackCount", String.valueOf(getSupportFragmentManager().getBackStackEntryCount
				()));
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			startActivity(new Intent(MainActivity.this, SettingsActivity.class));
			return true;
		} else if (id == R.id.action_sign_outout) {
			mAuth.signOut();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.nav_camera) {
			
		} else if (id == R.id.nav_gallery) {
			
		} else if (id == R.id.nav_slideshow) {
			
		} else if (id == R.id.nav_manage) {
			
		} else if (id == R.id.nav_share) {
			
		} else if (id == R.id.nav_send) {
			
		}
		
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
	
	/**
	 * Called whenever the contents of the back stack change.
	 */
	@Override
	public void onBackStackChanged() {
		
		Log.d(TAG, String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
	}
	
	@Override
	public void onFeedItemShareListener() {
		
		BottomSheetDialog sheetDialog = new BottomSheetDialog(this);
		sheetDialog.setContentView(R.layout.bottom_sheet);
		sheetDialog.show();
	}
	
	@Override
	public void onGroupDetailsListener() {
		//ListDialog.newInstance(this).show();
		startActivity(new Intent(MainActivity.this, GroupActivity.class));
	}
	
	@Override
	public void onAvatarListener() {
		
		startActivity(new Intent(MainActivity.this, ProfileActivity.class));
	}
	
	@Override
	public void onEsportsFragmentInteraction(com.wasder.wasderapp.ui.live.dummy.DummyContent
			                                             .DummyItem item) {
		
		startActivity(new Intent(MainActivity.this, EsportsActivity.class));
	}
	
	@Override
	public void onTwitchLiveFragmentInteractionListener(com.wasder.wasderapp.ui.live.dummy
			                                                        .DummyContent.DummyItem item) {
		
		startActivity(new Intent(MainActivity.this, TwitchLiveActivity.class));
	}
	
	@Override
	public void onTiwtchStreamFragmentInteractionListener(DummyContent.DummyItem item) {
		
		startActivity(new Intent(MainActivity.this, TwitchStreamActivity.class));
	}
	
	@Override
	public void onEventFragmentInteractionListener(com.wasder.wasderapp.ui.market.dummy
			                                                   .DummyContent.DummyItem item) {
		
		startActivity(new Intent(MainActivity.this, EventActivity.class));
	}
	
	@Override
	public void onFriendEventFragmentInteractionListener(com.wasder.wasderapp.ui.market.dummy
			                                                         .DummyContent.DummyItem
			                                                         item) {
		
		startActivity(new Intent(MainActivity.this, FriendEventActivity.class));
	}
	
	@Override
	public void onRecommendedEventFragmentInteractionListener(com.wasder.wasderapp.ui.market.dummy
			                                                              .DummyContent.DummyItem
			                                                              item) {
		
		startActivity(new Intent(MainActivity.this, RecommendedEventActivity.class));
	}
	
	@Override
	public void onCreatorFeedFragmentInteractionListener(CreatorFeedItem item) {
		
		Intent intent = new Intent(getBaseContext(), CreatorFeedActivity.class);
		intent.putExtra("Name", item.content);
		startActivity(intent);
	}
	
	@Override
	public void onFeedFragmentInteractionListener(FeedModel feedModel) {
		
		startActivity(new Intent(MainActivity.this, FeedActivity.class));
	}
	
	@Override
	public void onGroupFragmentInteractionListener(DummyContent.DummyItem item) {
		
		startActivity(new Intent(MainActivity.this, GroupActivity.class));
	}
	
	@Override
	public void onFragmentInteractionListener(Uri uri) {
		
	}
}
