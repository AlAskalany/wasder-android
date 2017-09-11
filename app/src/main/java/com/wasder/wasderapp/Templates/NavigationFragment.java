package com.wasder.wasderapp.Templates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
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

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.ProfileActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class NavigationFragment
		extends Fragment
		implements NavigationView.OnNavigationItemSelectedListener {
	
	private static final String ARG_TAG = "param_tag";
	private static final String ARG_TITLE = "param_title";
	private static final String ARG_LAYOUT = "param_layout";
	private static final String ARG_TOOLBAR = "param_toolbar";
	private static final String ARG_DRAWER_LAYOUT = "param_drawer_layout";
	private static final String ARG_NAVIGATION_VIEW = "param_navigation_view";
	private static final String ARG_VIEWPAGER = "param_viewpager";
	private static final String ARG_TAB_LAYOUT = "param_tab_layout";
	private static final String ARG_TAB_FRAGMENTS = "param_tab_fragments";
	String mTAG;
	String mFragmentTitle;
	int mResLayout;
	int mResToolbar;
	int mResDrawerLayout;
	int mResNavigationView;
	int mResViewPager;
	int mResTabLayout;
	List<TabFragment> mTabFragments;
	private AppCompatActivity activity;
	private OnFragmentInteractionListener mListener;
	
	public NavigationFragment() {
		
	}
	
	public static NavigationFragment newInstance(String tag, String fragmentTitle, int layout, int resToolbar, int resDrawerLayout,
			int resNavigationView, int resViewPager, int resTabLayout, List<TabFragment> tabFragments) {
		
		NavigationFragment fragment = new NavigationFragment();
		Bundle args = new Bundle();
		args.putString(ARG_TAG, tag);
		args.putString(ARG_TITLE, fragmentTitle);
		args.putInt(ARG_LAYOUT, layout);
		args.putInt(ARG_TOOLBAR, resToolbar);
		args.putInt(ARG_DRAWER_LAYOUT, resDrawerLayout);
		args.putInt(ARG_NAVIGATION_VIEW, resNavigationView);
		args.putInt(ARG_VIEWPAGER, resViewPager);
		args.putInt(ARG_TAB_LAYOUT, resTabLayout);
		args.putSerializable(ARG_TAB_FRAGMENTS, (Serializable) tabFragments);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement " + "OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mTAG = getArguments().getString(ARG_TAG);
			mFragmentTitle = getArguments().getString(ARG_TITLE);
			mResLayout = getArguments().getInt(ARG_LAYOUT);
			mResToolbar = getArguments().getInt(ARG_TOOLBAR);
			mResDrawerLayout = getArguments().getInt(ARG_DRAWER_LAYOUT);
			mResNavigationView = getArguments().getInt(ARG_NAVIGATION_VIEW);
			mResViewPager = getArguments().getInt(ARG_VIEWPAGER);
			mResTabLayout = getArguments().getInt(ARG_TAB_LAYOUT);
			mTabFragments = (List<TabFragment>) getArguments().getSerializable(ARG_TAB_FRAGMENTS);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(this.mResLayout, container, false);
		Toolbar toolbar = view.findViewById(mResToolbar);
		activity = (AppCompatActivity) getActivity();
		activity.setSupportActionBar(toolbar);
		ActionBar actionBar = activity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setTitle(mFragmentTitle);
		}
		DrawerLayout drawerLayout = activity.findViewById(mResDrawerLayout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = activity.findViewById(mResNavigationView);
		navigationView.setNavigationItemSelectedListener(this);
		SectionsPagerAdapter sectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		for (TabFragment tab : mTabFragments) {
			sectionPagerAdapter.addFragment(tab, tab.getTitle());
		}
		ViewPager viewPager = view.findViewById(mResViewPager);
		viewPager.setAdapter(sectionPagerAdapter);
		TabLayout tabLayout = view.findViewById(mResTabLayout);
		tabLayout.setupWithViewPager(viewPager);
		return view;
	}
	
	@Override
	public void onStart() {
		
		super.onStart();
	}
	
	@Override
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
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
		} else {
			switch (id) {
				case R.id.nav_gallery:
					
					break;
				case R.id.nav_slideshow:
					
					break;
				case R.id.nav_manage:
					
					break;
				case R.id.nav_share:
					
					break;
				case R.id.nav_send:
					
					break;
			}
		}
		
		DrawerLayout drawer = getActivity().findViewById(mResDrawerLayout);
		
		drawer.closeDrawer(GravityCompat.START);
		return false;
	}
	
	public OnFragmentInteractionListener getmListener() {
		
		return mListener;
	}
	
	public void setmListener(OnFragmentInteractionListener mListener) {
		
		this.mListener = mListener;
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	static class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		
		private Map<Integer, Pair<String, Fragment>> fragmentMap = new HashMap<>();
		
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
			
			return fragmentMap.get(position).second;
		}
		
		@Override
		public int getCount() {
			
			return fragmentMap.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			
			return fragmentMap.get(position).first;
		}
		
		public void addFragment(Fragment fragment, String title) {
			
			int positon = fragmentMap.size();
			fragmentMap.put(positon, new Pair<String, Fragment>(title, fragment));
		}
	}
}
