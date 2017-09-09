package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.DummyContent.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FeedFragment extends Fragment {
	
	// TODO: Customize parameter argument names
	private static final String ARG_COLUMN_COUNT = "column-count";
	private FeedFirebaseRecyclerAdapter mFeedFirebaseRecyclerAdapter;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	// TODO: Customize parameters
	private int mColumnCount = 1;
	private OnListFragmentInteractionListener mListener;
	private OnFeedItemShareListener mShareListener;
	private OnAvatarListener mAvatarListener;
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public FeedFragment() {
		
	}
	
	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static FeedFragment newInstance(int columnCount) {
		
		FeedFragment fragment = new FeedFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnListFragmentInteractionListener) {
			mListener = (OnListFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
		}
		if (context instanceof OnFeedItemShareListener) {
			mShareListener = (OnFeedItemShareListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnFeedItemShareListener");
		}
		if (context instanceof OnAvatarListener) {
			mAvatarListener = (OnAvatarListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnAvatarListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_feed_list, container, false);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.feedRecyclerView);
		mLinearLayoutManager = new LinearLayoutManager(getContext());
		mFeedFirebaseRecyclerAdapter = new FeedFirebaseRecyclerAdapter(getContext(), mRecyclerView, mLinearLayoutManager);
		mRecyclerView.setAdapter(mFeedFirebaseRecyclerAdapter);
		
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
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnListFragmentInteractionListener {
		
		// TODO: Update argument type and name
		void onListFragmentInteraction(DummyItem item);
	}
	
	public interface OnFeedItemShareListener {
		
		void onFeedItemShareListener();
	}
	
	public interface OnAvatarListener {
		
		void onAvatarListener();
	}
}
