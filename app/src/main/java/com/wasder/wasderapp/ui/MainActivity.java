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
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.wasder.wasderapp.AccountActivity;
import com.wasder.wasderapp.Builders.WasderUiBuilder;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseNavigationFragment;
import com.wasder.wasderapp.ui.home.CreatorFeedActivity;
import com.wasder.wasderapp.ui.home.GroupActivity;
import com.wasder.wasderapp.ui.live.EsportsActivity;
import com.wasder.wasderapp.ui.live.TwitchLiveActivity;
import com.wasder.wasderapp.ui.live.TwitchStreamActivity;
import com.wasder.wasderapp.ui.social.EventActivity;
import com.wasder.wasderapp.ui.social.FriendEventActivity;
import com.wasder.wasderapp.ui.social.RecommendedEventActivity;
import com.wasder.wasderapp.util.Helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Main activity.
 */
public class MainActivity
		extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener,
		           OnFragmentInteractionListener<Object>,
		           FragmentManager.OnBackStackChangedListener {
	
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
	
	BaseNavigationFragment homeFragment;
	BaseNavigationFragment liveFragment;
	BaseNavigationFragment socialFragment;
	private Map<Integer, BaseNavigationFragment> fragmentMap = new HashMap<>();
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private Uri mPhotoUrl;
	private String mUid;
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
			.OnNavigationItemSelectedListener() {
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction ts = manager.beginTransaction();
			Fragment currentFragment = manager.findFragmentById(R.id.framelayout_fragment_container);
			BaseNavigationFragment newFragment = null;
			int container = R.id.framelayout_fragment_container;
			if (currentFragment != null) {
				manager.saveFragmentInstanceState(currentFragment);
			}
			newFragment = fragmentMap.get(item.getItemId());
			if (newFragment != currentFragment) {
				ts.replace(R.id.framelayout_fragment_container, newFragment);
				ts.addToBackStack(null);
				ts.commit();
				//Helpers.Fragments.switchToNavigationFragment(container, ts, newFragment);
				return true;
			}
			return false;
		}
	};
	private NavigationView navigationView;
	private View headerView;
	private TextView userNameTextView;
	private TextView emailTextView;
	private BottomNavigationView bottomNavigationView;
	private Toolbar mToolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		serviceIntent.setPackage("com.android.vending");
		bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		//Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_main_linearlayout),
		// "Connection", 2000);
		//snackbar.show();
		if (activeInfo != null && activeInfo.isConnected()) {
			Toast.makeText(this, "Connected", Toast.LENGTH_SHORT)
			     .show();
		} else {
			Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT)
			     .show();
		}
		// Configure Google Sign In
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string
				                                                                                                                        .default_web_client_id))
		
		                                                                                              .requestEmail()
		                                                                                              .build();
		
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
					
					navigationView = findViewById(R.id.nav_view);
					headerView = navigationView.getHeaderView(0);
					userNameTextView = headerView.findViewById(R.id.nav_header_user_name);
					userNameTextView.setText(mUserName);
					emailTextView = headerView.findViewById(R.id.nav_header_user_details);
					emailTextView.setText(mEmail);
				} else {
					Log.d(TAG, "Signed out");
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
				}
			}
		};
		
		homeFragment = new WasderUiBuilder.NavigationFragmentBuilder().Create()
		                                                              .setmFragmentTitle("Home")
		                                                              .setmResNavigationView(R.id.nav_view)
		                                                              .setmResDrawerLayout(R.id.drawer_layout)
		                                                              .setmResLayout(R.layout.main_home_fragment)
		                                                              .setmResTabLayout(R.id.home_tablayout)
		                                                              .setmResViewPager(R.id.home_viewpager)
		                                                              .setmTAG("HomeFragment")
		                                                              .addTab(WasderUiBuilder.TabType.Feed)
		                                                              .addTab(WasderUiBuilder.TabType.Creators)
		                                                              .addTab(WasderUiBuilder.TabType.Groups)
		                                                              .build();
		
		liveFragment = new WasderUiBuilder.NavigationFragmentBuilder().Create()
		                                                              .setmFragmentTitle("Live")
		                                                              .setmResNavigationView(R.id.nav_view)
		                                                              .setmResDrawerLayout(R.id.drawer_layout)
		                                                              .setmResLayout(R.layout.main_live_fragment)
		                                                              .setmResTabLayout(R.id.live_tablayout)
		                                                              .setmResViewPager(R.id.live_viewpager)
		                                                              .setmTAG("LiveFragment")
		                                                              .addTab(WasderUiBuilder.TabType.TwitchStream)
		                                                              .addTab(WasderUiBuilder.TabType.TwitchLive)
		                                                              .addTab(WasderUiBuilder.TabType.Esports)
		                                                              .build();
		
		socialFragment = new WasderUiBuilder.NavigationFragmentBuilder().Create()
		                                                                .setmFragmentTitle("Social")
		                                                                .setmResNavigationView(R.id.nav_view)
		                                                                .setmResDrawerLayout(R.id.drawer_layout)
		                                                                .setmResLayout(R.layout.main_social_fragment)
		                                                                .setmResTabLayout(R.id.social_tablayout)
		                                                                .setmResViewPager(R.id.social_viewpager)
		                                                                .setmTAG("SocialFragment")
		                                                                .addTab(WasderUiBuilder.TabType.AllEvents)
		                                                                .addTab(WasderUiBuilder.TabType.RecommendedEvents)
		                                                                .addTab(WasderUiBuilder.TabType.FriendsEvents)
		                                                                .build();
		
		fragmentMap.put(R.id.navigation_home, homeFragment);
		fragmentMap.put(R.id.navigation_live, liveFragment);
		fragmentMap.put(R.id.navigation_social, socialFragment);
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.framelayout_fragment_container, homeFragment, "Home");
		transaction.commit();
		
		mToolbar = findViewById(R.id.toolbar_main_activity);
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Wasder");
		DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
		                                                         drawerLayout,
		                                                         mToolbar,
		                                                         R.string.navigation_drawer_open,
		                                                         R.string.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		
		//region Bottom_Navigation
		bottomNavigationView = findViewById(R.id.navigation);
		bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
		
		Log.d("BackStackCount", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
		
		switch (id) {
			case R.id.nav_account:
				startActivity(new Intent(MainActivity.this, AccountActivity.class));
				break;
			case R.id.nav_settings:
				startActivity(new Intent(MainActivity.this, SettingsActivity.class));
				break;
		}
		
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		
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
	public void onFragmentInteractionListener(String tag, Object data) {
		
		switch (tag) {
			case Helpers.TAG.FeedFragment:
				startActivity(new Intent(MainActivity.this, ProfileActivity.class));
				break;
			case Helpers.TAG.CreatorsFragment:
				startActivity(new Intent(MainActivity.this, CreatorFeedActivity.class));
				break;
			case Helpers.TAG.GroupsFragment:
				startActivity(new Intent(MainActivity.this, GroupActivity.class));
				break;
			case Helpers.TAG.TwitchStreamFragment:
				startActivity(new Intent(MainActivity.this, TwitchStreamActivity.class));
				break;
			case Helpers.TAG.TwitchLiveFragment:
				startActivity(new Intent(MainActivity.this, TwitchLiveActivity.class));
				break;
			case Helpers.TAG.EsportsFragment:
				startActivity(new Intent(MainActivity.this, EsportsActivity.class));
				break;
			case Helpers.TAG.EventsFragment:
				startActivity(new Intent(MainActivity.this, EventActivity.class));
				break;
			case Helpers.TAG.RecommendedEventsFragment:
				startActivity(new Intent(MainActivity.this, RecommendedEventActivity.class));
				break;
			case Helpers.TAG.FriendsEventsFragment:
				startActivity(new Intent(MainActivity.this, FriendEventActivity.class));
				break;
			
		}
	}
}
