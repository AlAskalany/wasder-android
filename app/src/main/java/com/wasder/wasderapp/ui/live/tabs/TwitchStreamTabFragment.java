package com.wasder.wasderapp.ui.live.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.TabFragment;
import com.wasder.wasderapp.ui.home.tabs.FeedRecyclerAdapter;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class TwitchStreamTabFragment extends TabFragment {
	
	private int columnCount;
	private String title = "Twitch Stream";
	private int resLayout;
	private OnFragmentInteractionListener mListener;
	private View view;
	private String mTAG;
	
	public TwitchStreamTabFragment() {
		
		super();
	}
	
	public static TwitchStreamTabFragment newInstance() {
		
		return new TwitchStreamTabFragment();
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.twitch_stream_recycler_view, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.twitch_stream_recyclerView);
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
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setColumnCount(int columnCount) {
		
		this.columnCount = columnCount;
	}
	
	public void setResLayout(int resLayout) {
		
		this.resLayout = resLayout;
	}
	
	public void setTag(String tag) {
		
		this.mTAG = tag;
	}
}
