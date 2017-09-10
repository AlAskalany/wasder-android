package com.wasder.wasderapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.home.CreatorFeedFragment;
import com.wasder.wasderapp.ui.home.FeedFragment;
import com.wasder.wasderapp.ui.home.GroupFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public abstract class NavigationFragment extends Fragment implements NavigationView
		.OnNavigationItemSelectedListener {
	
	private static String TAG;
	private static String ARG_PARAM1 = "param1";
	private static String ARG_PARAM2 = "param2";
	private String param1;
	private String param2;
	private View view;
	private int layout;
	private int resToolbar;
	private AppCompatActivity activity;
	private Toolbar toolbar;
	private String fragmentTitle;
	private int resDrawerLayout;
	private int resNavigationView;
	private SectionsPagerAdapter sectionPagerAdapter;
	private int resViewPager;
	private ViewPager viewPager;
	private int resTabLayout;
	private TabLayout tabLayout;
	private OnFragmentInteractionListener mListener;
	private Fragment firstFragment;
	private Fragment secondFragment;
	private Fragment thirdFragment;
	
	protected NavigationFragment(String TAG, String fragmentTitle, int layout, int resToolbar, int
			resDrawerLayout, int resNavigationView, int resViewPager, int resTabLayout, Fragment
			firstFragment, Fragment secondFragment, Fragment thirdFragment) {
		
		this.TAG = TAG;
		this.fragmentTitle = fragmentTitle;
		this.layout = layout;
		this.resToolbar = resToolbar;
		this.resDrawerLayout = resDrawerLayout;
		this.resNavigationView = resNavigationView;
		this.resViewPager = resViewPager;
		this.resTabLayout = resTabLayout;
		this.firstFragment = firstFragment;
		this.secondFragment = secondFragment;
		this.thirdFragment = thirdFragment;
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement " +
					"OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			param1 = getArguments().getString(ARG_PARAM1);
			param2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
			savedInstanceState) {
		
		view = inflater.inflate(this.layout, container, false);
		toolbar = view.findViewById(resToolbar);
		activity = (AppCompatActivity) getActivity();
		activity.setSupportActionBar(toolbar);
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setTitle(fragmentTitle);
		DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(resDrawerLayout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) activity.findViewById(resNavigationView);
		navigationView.setNavigationItemSelectedListener(this);
		sectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		sectionPagerAdapter.addFragment(firstFragment, "Feed");
		sectionPagerAdapter.addFragment(secondFragment, "Creators");
		sectionPagerAdapter.addFragment(thirdFragment, "Groups");
		viewPager = (ViewPager) view.findViewById(resViewPager);
		viewPager.setAdapter(sectionPagerAdapter);
		tabLayout = (TabLayout) view.findViewById(resTabLayout);
		tabLayout.setupWithViewPager(viewPager);
		return view;
	}
	
	@Override
	public void onStart() {
		
		super.onStart();
	}
	
	/**
	 * Called when an item in the navigation menu is selected.
	 *
	 * @param item The selected item
	 * @return true to display the item as the selected item
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		
		int id = item.getItemId();
		
		if (id == R.id.nav_camera) {
			startActivity(new Intent(activity, ProfileActivity.class));
		} else if (id == R.id.nav_gallery) {
			
		} else if (id == R.id.nav_slideshow) {
			
		} else if (id == R.id.nav_manage) {
			
		} else if (id == R.id.nav_share) {
			
		} else if (id == R.id.nav_send) {
			
		}
		
		DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(resDrawerLayout);
		
		drawer.closeDrawer(GravityCompat.START);
		return false;
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	static class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		
		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();
		
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
			
			return mFragmentList.get(position);
		}
		
		@Override
		public int getCount() {
			
			return mFragmentList.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return mFragmentTitleList.get(position);
		}
		
		public void addFragment(Fragment fragment, String title) {
			
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}
	}
}
