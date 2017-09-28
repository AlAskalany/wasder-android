package com.wasder.wasderapp.ui.profile;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnProfileDetailsActivity extends BaseDetailsActivity implements OnFragmentInteractionListener {
	
	private final List<ProfileTab> mTabFragments = new ArrayList<>();
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_own_profile);
		ProfileFeedFragment profileFeedFragment = ProfileFeedFragment.newInstance();
		ProfileInfoFragment profileInfoFragment = ProfileInfoFragment.newInstance();
		mTabFragments.add(profileFeedFragment);
		mTabFragments.add(profileInfoFragment);
		
		NestedScrollView nestedScrollView = findViewById(R.id.activity_own_profile_nestedScrollView);
		nestedScrollView.setFillViewport(true);
		
		Toolbar toolbar = findViewById(R.id.activity_own_profile_toolbar);
		toolbar.setTitle("Profile");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		for (ProfileTab tabFragment : mTabFragments) {
			mSectionsPagerAdapter.addFragment(tabFragment, tabFragment.getTitle());
		}
		// Set up the ViewPager with the sections adapter.
		/*
	  The {@link ViewPager} that will host the section contents.
	 */
		ViewPager mViewPager = findViewById(R.id.activity_own_profile_viewPager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		TabLayout tabLayout = findViewById(R.id.activity_own_profile_tabLayout);
		tabLayout.setupWithViewPager(mViewPager);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_account, menu);
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
	
	@Override
	public void onFragmentInteractionListener(String tag, Object data, Object extra) {
		
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	static class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		
		private final Map<Integer, Pair<String, ProfileTab>> fragmentMap = new HashMap<>();
		
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
			
			return (Fragment) fragmentMap.get(position).second;
		}
		
		@Override
		public int getCount() {
			
			return fragmentMap.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return fragmentMap.get(position).first;
		}
		
		public void addFragment(ProfileTab fragment, String title) {
			
			int positon = fragmentMap.size();
			fragmentMap.put(positon, new Pair<String, ProfileTab>(title, fragment));
		}
	}
}
