package com.wasder.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	
	private TextView mTextMessage;
	
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			
			switch (item.getItemId()) {
				case R.id.navigation_home:
					mTextMessage.setText(R.string.title_home);
					return true;
				case R.id.navigation_dashboard:
					mTextMessage.setText(R.string.title_dashboard);
					return true;
				case R.id.navigation_notifications:
					mTextMessage.setText(R.string.title_notifications);
					return true;
			}
			return false;
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		
		mTextMessage = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		
		SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		// Set up the ViewPager with the sections adapter.
		ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
		
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});
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
	
	/**
	 * The type Placeholder fragment.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		/**
		 * Instantiates a new Placeholder fragment.
		 */
		public PlaceholderFragment() {
			
		}
		
		/**
		 * New instance placeholder fragment.
		 *
		 * @param sectionNumber the section number
		 * @return the placeholder fragment
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
			TextView textView = rootView.findViewById(R.id.section_label);
			textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	private class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		/**
		 * Instantiates a new Sections pager adapter.
		 *
		 * @param fm the fm
		 */
		SectionsPagerAdapter(FragmentManager fm) {
			
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			return PlaceholderFragment.newInstance(position + 1);
		}
		
		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			switch (position) {
				case 0:
					return "SECTION 1";
				case 1:
					return "SECTION 2";
				case 2:
					return "SECTION 3";
			}
			return null;
		}
	}
}
