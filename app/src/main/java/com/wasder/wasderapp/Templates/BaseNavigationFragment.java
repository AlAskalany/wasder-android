package com.wasder.wasderapp.Templates;

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

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class BaseNavigationFragment
		extends Fragment
		
		implements NavigationView.OnNavigationItemSelectedListener {
	
	//	private static final String ARG_TAG = "param_tag";
	//	private static final String ARG_TITLE = "param_title";
	//	private static final String ARG_LAYOUT = "param_layout";
	//	private static final String ARG_TOOLBAR = "param_toolbar";
	//	private static final String ARG_DRAWER_LAYOUT = "param_drawer_layout";
	//	private static final String ARG_NAVIGATION_VIEW = "param_navigation_view";
	//	private static final String ARG_VIEWPAGER = "param_viewpager";
	//	private static final String ARG_TAB_LAYOUT = "param_tab_layout";
	private String mTAG;
	private String mFragmentTitle;
	private int mResLayout;
	private int mResToolbar;
	private int mResDrawerLayout;
	private int mResNavigationView;
	private int mResViewPager;
	private int mResTabLayout;
	private List<BaseTabFragment> mBaseTabFragments = new ArrayList<>();
	private AppCompatActivity activity;
	private OnFragmentInteractionListener mListener;
	private ViewPager viewPager;
	private TabLayout tabLayout;
	private SectionsPagerAdapter sectionPagerAdapter;
	
	public BaseNavigationFragment() {
		
	}
	
	public static BaseNavigationFragment newInstance(String tag, String fragmentTitle, int layout, int resToolbar, int resDrawerLayout,
			int resNavigationView, int resViewPager, int resTabLayout) {
		
		BaseNavigationFragment fragment = new BaseNavigationFragment();
		//		Bundle args = new Bundle();
		//		args.putString(ARG_TAG, tag);
		//		args.putString(ARG_TITLE, fragmentTitle);
		//		args.putInt(ARG_LAYOUT, layout);
		//		args.putInt(ARG_TOOLBAR, resToolbar);
		//		args.putInt(ARG_DRAWER_LAYOUT, resDrawerLayout);
		//		args.putInt(ARG_NAVIGATION_VIEW, resNavigationView);
		//		args.putInt(ARG_VIEWPAGER, resViewPager);
		//		args.putInt(ARG_TAB_LAYOUT, resTabLayout);
		//		fragment.setArguments(args);
		return fragment;
	}
	
	public List<BaseTabFragment> getmBaseTabFragments() {
		
		return mBaseTabFragments;
	}
	
	public void setmBaseTabFragments(List<BaseTabFragment> mBaseTabFragments) {
		
		this.mBaseTabFragments = mBaseTabFragments;
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
			//			mTAG = getArguments().getString(ARG_TAG);
			//			mFragmentTitle = getArguments().getString(ARG_TITLE);
			//			mResLayout = getArguments().getInt(ARG_LAYOUT);
			//			mResToolbar = getArguments().getInt(ARG_TOOLBAR);
			//			mResDrawerLayout = getArguments().getInt(ARG_DRAWER_LAYOUT);
			//			mResNavigationView = getArguments().getInt(ARG_NAVIGATION_VIEW);
			//			mResViewPager = getArguments().getInt(ARG_VIEWPAGER);
			//			mResTabLayout = getArguments().getInt(ARG_TAB_LAYOUT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(this.mResLayout, container, false);
		//Toolbar toolbar = view.findViewById(mResToolbar);
		//activity = (AppCompatActivity) getActivity();
		//activity.setSupportActionBar(toolbar);
		//ActionBar actionBar = activity.getSupportActionBar();
		//if (actionBar != null) {
		//	actionBar.setTitle(mFragmentTitle);
		//}
		sectionPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		for (BaseTabFragment tab : mBaseTabFragments) {
			sectionPagerAdapter.addFragment(tab, tab.getTitle());
		}
		viewPager = view.findViewById(mResViewPager);
		viewPager.setAdapter(sectionPagerAdapter);
		tabLayout = view.findViewById(mResTabLayout);
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
	
	//@Override
	//public void onSaveInstanceState(Bundle outState) {
	//super.onSaveInstanceState(outState);
	//outState.putInt("someVarA", someVarA);
	//outState.putString("someVarB", someVarB);
	//}
	
	//@Override
	//public void onActivityCreated(@Nullable Bundle savedInstanceState) {
	//super.onActivityCreated(savedInstanceState);
	//someVarA = savedInstanceState.getInt("someVarA");
	//someVarB = savedInstanceState.getString("someVarB");
	//}
	
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
			case R.id.nav_account:
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
		
		this.mResLayout = mResLayout;
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
		
		this.mResViewPager = mResViewPager;
	}
	
	public void setmResTabLayout(int mResTabLayout) {
		
		this.mResTabLayout = mResTabLayout;
	}
	
	/**
	 * The type Sections pager adapter.
	 */
	static class SectionsPagerAdapter
			extends FragmentStatePagerAdapter {
		
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
