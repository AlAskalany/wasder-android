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

public class FeedTabFragment extends TabFragment {
	
	private static final String TAG = "FeedTabFragment";
	private int columnCount;
	private String title = "Feed";
	private OnFragmentInteractionListener mListener;
	
	public FeedTabFragment() {
		
		super();
	}
	
	public static FeedTabFragment newInstance() {
		
		FeedTabFragment fragment = new FeedTabFragment();
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.feed_recycler_view, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.feedRecyclerView);
		
		if (recyclerView != null) {
			Context context = view.getContext();
			LinearLayoutManager layoutManager;
			layoutManager = columnCount <= 1 ? new LinearLayoutManager(context) : new GridLayoutManager(context, columnCount);
			recyclerView.setLayoutManager(layoutManager);
			FeedRecyclerAdapter feedRecyclerAdapter = new FeedRecyclerAdapter(getContext(), new LinearLayoutManager(getContext()), mListener);
			recyclerView.setAdapter(feedRecyclerAdapter);
			
		}
		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			/**
			 * Callback method to be invoked when the RecyclerView has been scrolled. This will be
			 * called after the scroll has completed.
			 * <p>
			 * This callback will also be called if visible item range changes after a layout
			 * calculation. In that case, dx and dy will be 0.
			 *
			 * @param recyclerView The RecyclerView which scrolled.
			 * @param dx           The amount of horizontal scroll.
			 * @param dy           The amount of vertical scroll.
			 */
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				
				super.onScrolled(recyclerView, dx, dy);
				if (dy > 0) {
					mListener.onFragmentInteractionListener("ScrollDown", null, null);
				} else if (dy < 0) {
					mListener.onFragmentInteractionListener("ScrollUp", null, null);
				}
			}
		});
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
	
	public void setTag(String tag) {
		
	}
}
