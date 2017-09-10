package com.wasder.wasderapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.ui.home.CreatorFeedFragment;
import com.wasder.wasderapp.ui.home.FeedFragment;
import com.wasder.wasderapp.ui.home.GroupFragment;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public abstract class TabFragment extends Fragment {
	
	private static String ARG_COLUMN_COUNT = "column-count";
	private static int FEED_FRAGMENT = 1;
	private static int CREATOR_FEED_FRAGMENT = 2;
	private static int GROUP_FRAGMENT = 3;
	private String title;
	private int columnCount;
	private OnFragmentInteractionListener mListener;
	private View view;
	private int resLayout;
	private int fragmentType;
	private FeedFragment.MyFeedRecyclerAdapter myFeedRecyclerAdapter;
	private CreatorFeedFragment.MyCreatorFeedRecyclerViewAdapter myCreatorFeedRecyclerViewAdapter;
	
	public TabFragment(String title, int columnCount, int resLayout, int fragmentType) {
		
		this.title = title;
		this.columnCount = columnCount;
		this.resLayout = resLayout;
		this.fragmentType = fragmentType;
	}
	
	public TabFragment() {
		
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
			columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(resLayout, container, false);
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			if (columnCount <= 1) {
				LinearLayoutManager layoutManager = new LinearLayoutManager(context);
				recyclerView.setLayoutManager(layoutManager);
				if (fragmentType == FEED_FRAGMENT) {
					recyclerView.setAdapter(new FeedFragment.MyFeedRecyclerAdapter(context, recyclerView, layoutManager, mListener));
				} else if (fragmentType == CREATOR_FEED_FRAGMENT) {
					recyclerView.setAdapter(new CreatorFeedFragment.MyCreatorFeedRecyclerViewAdapter(context, recyclerView, layoutManager,
							mListener));
				} else if (fragmentType == GROUP_FRAGMENT) {
					recyclerView.setAdapter(new GroupFragment.MyGroupRecyclerAdapter(context, recyclerView, layoutManager, mListener));
				}
			} else {
				GridLayoutManager layoutManager = new GridLayoutManager(context, columnCount);
				recyclerView.setLayoutManager(layoutManager);
				if (fragmentType == FEED_FRAGMENT) {
					recyclerView.setAdapter(new FeedFragment.MyFeedRecyclerAdapter(context, recyclerView, layoutManager, mListener));
				} else if (fragmentType == CREATOR_FEED_FRAGMENT) {
					recyclerView.setAdapter(new CreatorFeedFragment.MyCreatorFeedRecyclerViewAdapter(context, recyclerView, layoutManager,
							mListener));
				} else if (fragmentType == GROUP_FRAGMENT) {
					recyclerView.setAdapter(new GroupFragment.MyGroupRecyclerAdapter(context, recyclerView, layoutManager, mListener));
				}
			}
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
}
