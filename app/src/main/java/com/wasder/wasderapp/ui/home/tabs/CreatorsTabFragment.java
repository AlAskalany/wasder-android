package com.wasder.wasderapp.ui.home.tabs;

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

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class CreatorsTabFragment extends TabFragment {
	
	private int columnCount;
	private String title = "Creators";
	private OnFragmentInteractionListener<Object, String> mListener;
	
	public CreatorsTabFragment() {
		
		super();
	}
	
	public static CreatorsTabFragment newInstance() {
		
		return new CreatorsTabFragment();
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			//noinspection unchecked
			mListener = (OnFragmentInteractionListener<Object, String>) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement " + "OnFragmentInteractionListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.creators_feed_recycler_view, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.creators_recyclerView);
		if (recyclerView != null) {
			Context context = view.getContext();
			LinearLayoutManager layoutManager;
			layoutManager = columnCount <= 1 ? new LinearLayoutManager(context) : new GridLayoutManager(context, columnCount);
			recyclerView.setLayoutManager(layoutManager);
			CreatorFeedRecyclerAdapter creatorFeedRecyclerAdapter = new CreatorFeedRecyclerAdapter(getContext(), new LinearLayoutManager(getContext
					()), mListener);
			recyclerView.setAdapter(creatorFeedRecyclerAdapter);
			
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
		
	}
	
	@SuppressWarnings({"EmptyMethod", "unused"})
	public void setTag(String tag) {
		
	}
}
