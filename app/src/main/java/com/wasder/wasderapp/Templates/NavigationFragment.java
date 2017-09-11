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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public abstract class NavigationFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
	
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
	private List<TabFragment> tabFragments;
	
	protected NavigationFragment(String TAG, String fragmentTitle, int layout, int resToolbar, int resDrawerLayout, int resNavigationView, int
			resViewPager, int resTabLayout, List<TabFragment> tabFragments) {
		
		NavigationFragment.TAG = TAG;
		this.fragmentTitle = fragmentTitle;
		this.layout = layout;
		this.resToolbar = resToolbar;
		this.resDrawerLayout = resDrawerLayout;
		this.resNavigationView = resNavigationView;
		this.resViewPager = resViewPager;
		this.resTabLayout = resTabLayout;
		this.tabFragments = tabFragments;
	}
	
	public NavigationFragment() {
		
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
			param1 = getArguments().getString(ARG_PARAM1);
			param2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(this.layout, container, false);
		toolbar = view.findViewById(resToolbar);
		activity = (AppCompatActivity) getActivity();
		activity.setSupportActionBar(toolbar);
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setTitle(fragmentTitle);
		DrawerLayout drawerLayout = activity.findViewById(resDrawerLayout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string
				.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = activity.findViewById(resNavigationView);
		navigationView.setNavigationItemSelectedListener(this);
		sectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		for (TabFragment tab : tabFragments) {
			sectionPagerAdapter.addFragment(tab, tab.getTitle());
		}
		viewPager = view.findViewById(resViewPager);
		viewPager.setAdapter(sectionPagerAdapter);
		tabLayout = view.findViewById(resTabLayout);
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
		} else if (id == R.id.nav_gallery) {
			
		} else if (id == R.id.nav_slideshow) {
			
		} else if (id == R.id.nav_manage) {
			
		} else if (id == R.id.nav_share) {
			
		} else if (id == R.id.nav_send) {
			
		}
		
		DrawerLayout drawer = getActivity().findViewById(resDrawerLayout);
		
		drawer.closeDrawer(GravityCompat.START);
		return false;
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
