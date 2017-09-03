package com.wasder.example;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wasder.example.dummy.DummyContent;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment
		.OnFragmentInteractionListener, LiveFragment.OnFragmentInteractionListener, FeedFragment.OnListFragmentInteractionListener, GroupFragment
		.OnListFragmentInteractionListener, CreatorFeedFragment.OnListFragmentInteractionListener, TwitchStreamFragment.OnListFragmentInteractionListener {
	
	HomeFragment homeFragment;
	LiveFragment liveFragment;
	private TextView mTextMessage;
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
			.OnNavigationItemSelectedListener() {
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ts = fm.beginTransaction();
			switch (item.getItemId()) {
				case R.id.navigation_home:
					getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
					ts.replace(R.id.framelayout_fragment_container, homeFragment);
					ts.addToBackStack(null);
					ts.commit();
					return true;
				case R.id.navigation_dashboard:
					getWindow().setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));
					ts.replace(R.id.framelayout_fragment_container, liveFragment);
					ts.addToBackStack(null);
					ts.commit();
					return true;
				case R.id.navigation_notifications:
					return true;
			}
			return false;
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		homeFragment = new HomeFragment();
		liveFragment = new LiveFragment();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.framelayout_fragment_container, homeFragment, "Home");
		//transaction.add(R.id.framelayout_fragment_container, liveFragment, "Live");
		//transaction.hide(liveFragment);
		transaction.commit();
		
		//region Navigation_Drawer
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, homeFragment.toolbar, R.string.navigation_drawer_open, R.string
				.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		//endregion
		
		//region Bottom_Navigation
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		//endregion
	}
	
	@Override
	public void onBackPressed() {
		
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
			return true;
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
		
	}
}
