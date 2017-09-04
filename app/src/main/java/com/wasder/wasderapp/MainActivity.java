package com.wasder.wasderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wasder.wasderapp.dummy.DummyContent;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment
		.OnFragmentInteractionListener, LiveFragment.OnFragmentInteractionListener, FeedFragment.OnListFragmentInteractionListener, GroupFragment
		.OnListFragmentInteractionListener, CreatorFeedFragment.OnListFragmentInteractionListener, TwitchStreamFragment
		.OnListFragmentInteractionListener, FragmentManager.OnBackStackChangedListener, MarketFragment.OnFragmentInteractionListener {
	
	private static final String TAG = "MainActivity";
	public String mUserName = "User Name";
	public String mEmail = "User E-mail";
	HomeFragment homeFragment;
	LiveFragment liveFragment;
	MarketFragment marketFragment;
	Toolbar toolbar;
	private TextView mTextMessage;
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private Uri mPhotoUrl;
	private String mUid;
	TextView nameTextView;
	TextView detailsTextView;
	
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
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
		
		// Configure Google Sign In
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string
				.default_web_client_id)).requestEmail().build();
		
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
					TextView userNameTextView = (TextView) headerView.findViewById(R.id.nav_header_user_name);
					userNameTextView.setText(mUserName);
					TextView emailTextView = (TextView) headerView.findViewById(R.id.nav_header_user_details);
					emailTextView.setText(mEmail);
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
	public void onBackPressed() {
		
		Log.d("BackStackCount", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
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
			BottomSheetDialog sheetDialog = new BottomSheetDialog(this);
			sheetDialog.setContentView(R.layout.bottom_sheet);
			sheetDialog.show();
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
	
	@Override
	public void onFragmentInteraction(Uri uri) {
		
	}
	
	@Override
	public void onListFragmentInteraction(DummyContent.DummyItem item) {
		//ListDialog.newInstance(this).show();
	}
	
	/**
	 * Called whenever the contents of the back stack change.
	 */
	@Override
	public void onBackStackChanged() {
		
		Log.d(TAG, String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
	}
}
