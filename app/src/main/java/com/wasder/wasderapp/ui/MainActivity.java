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
import android.support.v4.util.SparseArrayCompat;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;
import com.wasder.wasderapp.ui.home.HomeNavigationFragment;
import com.wasder.wasderapp.ui.live.LiveNavigationFragment;
import com.wasder.wasderapp.ui.profile.OwnProfileDetailsActivity;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
		OnFragmentInteractionListener<Object, String>, FragmentManager.OnBackStackChangedListener, View.OnClickListener, ServiceConnection,
		FirebaseAuth.AuthStateListener {
	
	private static final String TAG = "MainActivity";
	private final SparseArrayCompat<NavigationFragment> mNavFragments = new SparseArrayCompat<>();
	private IInAppBillingService mService;
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private Toolbar mToolbar;
	private ActionBar mActionBar;
	private final BottomNavigationView.OnNavigationItemSelectedListener mBottomNavigationListener = new BottomNavigationView
			.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			if (getCurrentNavigationFragment() != null) {
				getSupportFragmentManager().saveFragmentInstanceState(getCurrentNavigationFragment());
			}
			NavigationFragment fragment = mNavFragments.get(item.getItemId());
			if (fragment != getCurrentNavigationFragment()) {
				mActionBar.setTitle(fragment.getmFragmentTitle());
				getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
				return true;
			}
			return false;
		}
	};
	
	private Fragment getCurrentNavigationFragment() {
		
		return getSupportFragmentManager().findFragmentById(R.id.container);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindInAppBillingService();
		showNetworkStatus();
		setupFirebaseAuthAndUser();
		CreateNavigationFragments();
		mToolbar = findViewById(R.id.toolbar_main_activity);
		setSupportActionBar(mToolbar);
		mActionBar = getSupportActionBar();
		if (mActionBar != null) {
			mActionBar.setTitle(mNavFragments.get(R.id.navigation_home).getmFragmentTitle());
		}
		setActionBarToggleWithDrawerLayout(((DrawerLayout) findViewById(R.id.drawer_layout)));
		((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
		((BottomNavigationView) findViewById(R.id.navigation)).setOnNavigationItemSelectedListener(mBottomNavigationListener);
		findViewById(R.id.market_button).setOnClickListener(this);
		findViewById(R.id.purse_button).setOnClickListener(this);
		findViewById(R.id.calendar_button).setOnClickListener(this);
		findViewById(R.id.studio_button).setOnClickListener(this);
		findViewById(R.id.floatingActionButton).setOnClickListener(this);
		
		
	}
	
	private void setupFirebaseAuthAndUser() {
		
		mAuth = FirebaseAuth.getInstance();
		mAuthListener = this;
	}
	
	private void bindInAppBillingService() {
		
		Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		serviceIntent.setPackage("com.android.vending");
		bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
	}
	
	private void showNetworkStatus() {
		
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		if (activeInfo != null && activeInfo.isConnected()) {
			Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void setActionBarToggleWithDrawerLayout(DrawerLayout drawerLayout) {
		
		if (drawerLayout != null) {
			ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string
					.navigation_drawer_close);
			toggle.syncState();
		}
	}
	
	private void CreateNavigationFragments() {
		
		mNavFragments.put(R.id.navigation_home, HomeNavigationFragment.newInstance());
		mNavFragments.put(R.id.navigation_live, LiveNavigationFragment.newInstance());
		mNavFragments.put(R.id.navigation_social, SocialNavigationFragment.newInstance());
		getSupportFragmentManager().beginTransaction().add(R.id.container, mNavFragments.get(R.id.navigation_home), "Home").commit();
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
			unbindService(this);
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
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		
		Intent intent = null;
		Context context = MainActivity.this;
		switch (item.getItemId()) {
			case R.id.nav_profile:
				intent = new Intent(context, OwnProfileDetailsActivity.class);
				break;
			case R.id.nav_friends:
				intent = new Intent(context, FriendsActivity.class);
				break;
			case R.id.nav_followers:
				intent = new Intent(context, FollowersActivity.class);
				break;
			case R.id.nav_achievements:
				intent = new Intent(context, AchievementsActivity.class);
				break;
			case R.id.nav_settings:
				intent = new Intent(context, SettingsActivity.class);
				break;
			default:
				break;
		}
		if (intent != null) {
			startActivity(intent);
		}
		this.<DrawerLayout>findViewById(R.id.drawer_layout).closeDrawer(GravityCompat.START);
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
		switch (id) {
			case R.id.action_open_action_center:
				BottomSheetDialog sheet = new BottomSheetDialog(this);
				sheet.setContentView(R.layout.bottom_sheet_action_center);
				sheet.show();
				break;
			case R.id.action_settings:
				startActivity(new Intent(MainActivity.this, MarketItemListActivity.class));
				return true;
			case R.id.action_sign_outout:
				mAuth.signOut();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onFragmentInteractionListener(String tag, Object data, String extra) {
		// TODO hide and show bottom navigation bar upon scrolling
		//noinspection StatementWithEmptyBody,StringEquality
		if (tag == "ScrollDown") {
			//bottomNavigation.setVisibility(BottomNavigationView.GONE);
		} else //noinspection StatementWithEmptyBody,StringEquality
			if (tag == "ScrollUp") {
				//bottomNavigation.setVisibility(BottomNavigationView.VISIBLE);
			}
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			case R.id.purse_button:
				startActivity(new Intent(MainActivity.this, PurseActivity.class));
				break;
			case R.id.calendar_button:
				startActivity(new Intent(MainActivity.this, CalendarActivity.class));
				break;
			case R.id.studio_button:
				startActivity(new Intent(MainActivity.this, StudioActivity.class));
				break;
			case R.id.market_button:
				startActivity(new Intent(MainActivity.this, MarketItemListActivity.class));
				break;
			case R.id.floatingActionButton:
				startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
				break;
			default:
				break;
		}
	}
	
	@Override
	public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
		
		mService = IInAppBillingService.Stub.asInterface(iBinder);
	}
	
	@Override
	public void onServiceDisconnected(ComponentName componentName) {
		
		mService = null;
	}
	
	// Firebase authentication state listener
	@Override
	public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
		
		FirebaseUser user = mAuth.getCurrentUser();
		NavigationView navigationView = findViewById(R.id.nav_view);
		if (user != null && navigationView != null) {
			Log.d(TAG, "Signed in");
			user.getUid();
			//setNavigationDrawerImage(navigationView, user.getPhotoUrl());
			//setNavigationDrawerName(navigationView, user.getDisplayName());
			//setNavigationDrawerDetails(navigationView, user.getEmail());
		} else {
			Log.d(TAG, "Signed out");
			startActivity(new Intent(MainActivity.this, LoginActivity.class));
		}
	}
	
	@SuppressWarnings("EmptyMethod")
	private void setNavigationDrawerImage(NavigationView navigationView, Uri photoUrl) {
		
		//ImageView imageView = navigationView.getHeaderView(0).findViewById(R.id.nav_header_imageView);
		//Helpers.Firebase.DownloadUrlImage(photoUrl.toString(), imageView, true, R.drawable.wasder_logo);
	}
	
	private void setNavigationDrawerName(NavigationView navigationView, String userName) {
		
		((TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_user_name)).setText(userName);
	}
	
	private void setNavigationDrawerDetails(NavigationView navigationView, String details) {
		
		((TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_user_details)).setText(details);
	}
}
