package com.wasder.wasderapp.ui.Social.tabs;

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

public class GroupsMentionsTabFragment extends TabFragment {
	
	private int columnCount;
	private String title = "Groups Mentions";
	private int resLayout;
	private OnFragmentInteractionListener mListener;
	private View view;
	private String mTAG;
	
	public GroupsMentionsTabFragment() {
		
		super();
	}
	
	public static GroupsMentionsTabFragment newInstance() {
		
		GroupsMentionsTabFragment fragment = new GroupsMentionsTabFragment();
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		title = "Feed";
		view = inflater.inflate(R.layout.group_mentions_recycler_view, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.groups_mentions_RecyclerView);
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
