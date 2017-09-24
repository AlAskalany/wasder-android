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
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasder.wasderapp.AchievementsActivity;
import com.wasder.wasderapp.CalendarActivity;
import com.wasder.wasderapp.FollowersActivity;
import com.wasder.wasderapp.FriendsActivity;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.MarketItemListActivity;
import com.wasder.wasderapp.PurseActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.StudioActivity;
import com.wasder.wasderapp.Templates.NavigationFragment;
import com.wasder.wasderapp.ui.home.HomeNavigationFragment;
import com.wasder.wasderapp.ui.live.LiveNavigationFragment;
import com.wasder.wasderapp.ui.profile.OwnProfileDetailsActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
		OnFragmentInteractionListener<Object, String>, FragmentManager.OnBackStackChangedListener {
	
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
	HomeNavigationFragment homeFragment;
	LiveNavigationFragment liveFragment;
	SocialNavigationFragment socialFragment;
	private Map<Integer, NavigationFragment> fragmentMap = new HashMap<>();
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private Uri mPhotoUrl;
	private String mUid;
	private NavigationView navigationView;
	private View headerView;
	private TextView userNameTextView;
	private TextView emailTextView;
	private BottomNavigationView bottomNavigationView;
	private Toolbar mToolbar;
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
			.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction ts = manager.beginTransaction();
			Fragment currentFragment = manager.findFragmentById(R.id.framelayout_fragment_container);
			NavigationFragment newFragment;
			if (currentFragment != null) {
				manager.saveFragmentInstanceState(currentFragment);
			}
			newFragment = fragmentMap.get(item.getItemId());
			if (newFragment != currentFragment) {
				String title = newFragment.getmFragmentTitle();
				mToolbar.setTitle(title);
				ts.replace(R.id.framelayout_fragment_container, newFragment);
				ts.addToBackStack(null);
				ts.commit();
				//Helpers.Fragments.switchToNavigationFragment(container, ts, newFragment);
				return true;
			}
			return false;
		}
	};
	private BottomSheetBehavior sheetBehavior;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private BottomSheetDialog actionCenterBottomSheetDialog;
	
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
			Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();
		}
		SetupFirebaseAuth();
		CreateNavigationFragments();
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
		if (actionBar != null) {
			mToolbar.setTitle(homeFragment.getmFragmentTitle());
		}
		DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string
				.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		bottomNavigationView = findViewById(R.id.navigation);
		bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		actionCenterBottomSheetDialog = new BottomSheetDialog(this);
		Context context = this.getBaseContext();
		actionCenterBottomSheetDialog.setContentView(R.layout.bottom_sheet_action_center);
		ImageButton marketImageButton = findViewById(R.id.feed_sheet_market_imageButton);
		marketImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				startActivity(new Intent(MainActivity.this, MarketItemListActivity.class));
			}
		});
		ImageButton purseImageButton = findViewById(R.id.feed_sheet_purse_imageButton);
		purseImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				startActivity(new Intent(MainActivity.this, PurseActivity.class));
			}
		});
		ImageButton calendarImageButton = findViewById(R.id.feed_sheet_calendar_imageButton);
		calendarImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				startActivity(new Intent(MainActivity.this, CalendarActivity.class));
			}
		});
		ImageButton studioImageButton = findViewById(R.id.feed_sheet_studio_imageButton);
		studioImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				startActivity(new Intent(MainActivity.this, StudioActivity.class));
			}
		});
	}
	
	private void SetupFirebaseAuth() {
		
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
	}
	
	private void CreateNavigationFragments() {
		
		homeFragment = HomeNavigationFragment.newInstance("HomeFragment", "Home", R.id.drawer_layout, R.id.nav_view, R.id.tabLayout_main_activity);
		liveFragment = LiveNavigationFragment.newInstance("LiveFragment", "Live", R.id.drawer_layout, R.id.nav_view, R.id.tabLayout_main_activity);
		socialFragment = SocialNavigationFragment.newInstance("SocialFragment", "Social", R.id.drawer_layout, R.id.nav_view, R.id
				.tabLayout_main_activity);
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
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		
		int id = item.getItemId();
		switch (id) {
			case R.id.nav_profile:
				startActivity(new Intent(MainActivity.this, OwnProfileDetailsActivity.class));
				break;
			case R.id.nav_friends:
				startActivity(new Intent(MainActivity.this, FriendsActivity.class));
				break;
			case R.id.nav_followers:
				startActivity(new Intent(MainActivity.this, FollowersActivity.class));
				break;
			case R.id.nav_achievements:
				startActivity(new Intent(MainActivity.this, AchievementsActivity.class));
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
				actionCenterBottomSheetDialog.show();
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
		
	}
}
