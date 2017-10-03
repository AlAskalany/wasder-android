package com.wasder.wasderapp.ui.home;

import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;
import com.wasder.wasderapp.Templates.TabFragment;
import com.wasder.wasderapp.ui.home.tabs.FeedTabFragment;
import com.wasder.wasderapp.ui.home.tabs.GroupsTabFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class HomeNavigationFragment extends NavigationFragment implements NavigationView.OnNavigationItemSelectedListener {
	
	private String mTAG;
	private String mFragmentTitle;
	private int mResToolbar;
	private int mResDrawerLayout;
	private int mResNavigationView;
	private List<TabFragment> mTabFragments = new ArrayList<>();
	private AppCompatActivity activity;
	private OnFragmentInteractionListener mListener;
	
	public HomeNavigationFragment() {
		
	}
	
	public static HomeNavigationFragment newInstance() {
		
		return new HomeNavigationFragment();
	}
	
	public List<TabFragment> getmTabFragments() {
		
		return mTabFragments;
	}
	
	public void setmTabFragments(List<TabFragment> mTabFragments) {
		
		this.mTabFragments = mTabFragments;
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
		Amplitude.getInstance().initialize(getActivity(), "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getActivity().getApplication());
		Amplitude.getInstance().logEvent("Started_Home_Navigation_Fragment");
		FeedTabFragment feedTabFragment = FeedTabFragment.newInstance();
		GroupsTabFragment groupsTabFragment = GroupsTabFragment.newInstance();
		mTabFragments.add(feedTabFragment);
		mTabFragments.add(groupsTabFragment);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.main_home_fragment, container, false);
		SectionsPagerAdapter sectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		for (TabFragment tab : mTabFragments) {
			sectionPagerAdapter.addFragment(tab, tab.getTitle());
		}
		ViewPager viewPager = view.findViewById(R.id.home_viewpager);
		viewPager.setAdapter(sectionPagerAdapter);
		TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout_main_activity);
		tabLayout.setupWithViewPager(viewPager);
		return view;
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
		
		switch (id) {
			case R.id.nav_profile:
				break;
			case R.id.nav_settings:
				
				break;
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
	
	public String getmTAG() {
		
		return mTAG;
	}
	
	public void setmTAG(String mTAG) {
		
		this.mTAG = mTAG;
	}
	
	public String getmFragmentTitle() {
		
		return mFragmentTitle;
	}
	
	public void setmFragmentTitle(String mFragmentTitle) {
		
		this.mFragmentTitle = mFragmentTitle;
	}
	
	public void setmResLayout(int mResLayout) {
		
		@SuppressWarnings("UnusedAssignment") int mResLayout1 = mResLayout;
	}
	
	public int getmResToolbar() {
		
		return mResToolbar;
	}
	
	public void setmResToolbar(int mResToolbar) {
		
		this.mResToolbar = mResToolbar;
	}
	
	public void setmResDrawerLayout(int mResDrawerLayout) {
		
		this.mResDrawerLayout = mResDrawerLayout;
	}
	
	public int getmResNavigationView() {
		
		return mResNavigationView;
	}
	
	public void setmResNavigationView(int mResNavigationView) {
		
		this.mResNavigationView = mResNavigationView;
	}
	
	public void setmResViewPager(int mResViewPager) {
		
		@SuppressWarnings("UnusedAssignment") int mResViewPager1 = mResViewPager;
	}
	
	public void setmResTabLayout(int mResTabLayout) {
		
		@SuppressWarnings("UnusedAssignment") int mResTabLayout1 = mResTabLayout;
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	static class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		
		private final Map<Integer, Pair<String, Fragment>> fragmentMap = new HashMap<>();
		
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
			fragmentMap.put(positon, new Pair<>(title, fragment));
		}
	}
}
