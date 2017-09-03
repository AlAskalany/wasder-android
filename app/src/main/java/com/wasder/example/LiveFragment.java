package com.wasder.example;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class LiveFragment extends Fragment {
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	private OnFragmentInteractionListener mListener;
	View view;
	Toolbar toolbar;
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
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_live, container, false);
		//region Toolbar
		toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		
		((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
		
		//region Tabbed_Navigation
		mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
		mSectionsPagerAdapter.addFragment(new FirstFragment(), "Fourth");
		mSectionsPagerAdapter.addFragment(new SecondFragment(), "Fifth");
		mSectionsPagerAdapter.addFragment(new ThirdFragment(), "Sixth");
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) view.findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		tabLayout = (TabLayout) view.findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
		
		return view;
	}
	
	@Override
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
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
	
	/**
	 * First Fragment
	 */
	public static class FirstFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View view = inflater.inflate(R.layout.fragment_tabbed_first, container, false);
			return view;
		}
	}
	
	/**
	 * Second Fragment
	 */
	public static class SecondFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View view = inflater.inflate(R.layout.fragment_tabbed_second, container, false);
			return view;
		}
	}
	
	/**
	 * Third Fragment
	 */
	public static class ThirdFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View view = inflater.inflate(R.layout.fragment_tabbed_third, container, false);
			return view;
		}
	}
}
