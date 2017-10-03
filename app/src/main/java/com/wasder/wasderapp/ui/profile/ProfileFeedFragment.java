package com.wasder.wasderapp.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.home.tabs.FeedRecyclerAdapter;

public class ProfileFeedFragment extends Fragment implements ProfileTab {
	
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	
	private OnFragmentInteractionListener mListener;
	private int columnCount;
	
	public ProfileFeedFragment() {
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment ProfileInfoFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static ProfileFeedFragment newInstance() {
		
		ProfileFeedFragment fragment = new ProfileFeedFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, "AAA");
		args.putString(ARG_PARAM2, "BBB");
		fragment.setArguments(args);
		return fragment;
	}
	
	public String getTitle() {
		
		return "Profile";
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		Amplitude.getInstance().initialize(getActivity(), "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getActivity().getApplication());
		Amplitude.getInstance().logEvent("Started_Profile_Feed_Fragment");
		if (getArguments() != null) {
			getArguments().getString(ARG_PARAM1);
			getArguments().getString(ARG_PARAM2);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_profile_feed, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.activity_own_profile_feed_recyclerView);
		if (recyclerView != null) {
			Context context = view.getContext();
			LinearLayoutManager layoutManager;
			layoutManager = columnCount <= 1 ? new LinearLayoutManager(context) : new GridLayoutManager(context, columnCount);
			recyclerView.setLayoutManager(layoutManager);
			FeedRecyclerAdapter feedRecyclerAdapter = new FeedRecyclerAdapter(getContext(), new LinearLayoutManager(getContext()), mListener);
			recyclerView.setAdapter(feedRecyclerAdapter);
		}
		return view;
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
