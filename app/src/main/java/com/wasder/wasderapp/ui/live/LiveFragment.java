package com.wasder.wasderapp.ui.live;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LiveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
	
	private static final String TAG = "LiveFragment";
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	View view;
	Toolbar toolbar;
	AppCompatActivity activity;
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private OnFragmentInteractionListener mListener;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private TabLayout tabLayout;
	
	public LiveFragment() {
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment HomeFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static LiveFragment newInstance(String param1, String param2) {
		
		Log.d(TAG, "newInstance()");
		LiveFragment fragment = new LiveFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	
	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}
	
	@Override
	public void onAttach(Context context) {
		
		Log.d(TAG, "onAttach()");
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Log.d(TAG, "onCreate()");
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		Log.d(TAG, "onCreateView()");
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_live, container, false);
		//region Toolbar
		toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		//toolbar.setBackgroundColor(Color.RED);
		activity = ((AppCompatActivity) getActivity());
		activity.setSupportActionBar(toolbar);
		ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setTitle("Live");
		
		DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string
				.navigation_drawer_close);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		
		//region Tabbed_Navigation
		mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		mSectionsPagerAdapter.addFragment(new TwitchStreamFragment(), "Twitch Streams");
		mSectionsPagerAdapter.addFragment(new TwitchLiveFragment(), "Twitch Live");
		mSectionsPagerAdapter.addFragment(new EsportsFragment(), "Esports");
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) view.findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		tabLayout = (TabLayout) view.findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
		//tabLayout.setBackgroundColor(Color.RED);
		//tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
		
		//activity.getWindow().setStatusBarColor(getResources().getColor(R.color.md_red_900, activity.getTheme()));
		//toolbar.setBackgroundColor(getResources().getColor(R.color.md_red_800, activity.getTheme()));
		//tabLayout.setBackgroundColor(getResources().getColor(R.color.md_red_800, activity.getTheme()));
		//tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_yellow_300));
		
		return view;
	}
	
	/**
	 * Called when an item in the navigation menu is selected.
	 *
	 * @param item The selected item
	 * @return true to display the item as the selected item
	 */
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		
		return false;
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
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
