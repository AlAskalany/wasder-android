package com.wasder.wasderapp.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;

public class ProfileInfoFragment extends Fragment implements ProfileTab {
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	private OnFragmentInteractionListener mListener;
	
	public ProfileInfoFragment() {
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment ProfileInfoFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ProfileInfoFragment newInstance() {
		
		ProfileInfoFragment fragment = new ProfileInfoFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, "AAA");
		args.putString(ARG_PARAM2, "BBB");
		fragment.setArguments(args);
		return fragment;
	}
	
	public String getTitle() {
		
		String mTitle = "Info";
		return mTitle;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			getArguments().getString(ARG_PARAM1);
			getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_profile_info, container, false);
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
	}
}
