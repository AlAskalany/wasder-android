package com.wasder.wasderapp.ui.market;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.market.FriendEventFragment.OnFriendEventFragmentInteractionListener;
import com.wasder.wasderapp.ui.market.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnFriendEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFriendEventRecyclerViewAdapter extends RecyclerView.Adapter<MyFriendEventRecyclerViewAdapter.ViewHolder> {
	
	private final List<DummyItem> mValues;
	private final OnFriendEventFragmentInteractionListener mListener;
	
	public MyFriendEventRecyclerViewAdapter(List<DummyItem> items, OnFriendEventFragmentInteractionListener listener) {
		
		mValues = items;
		mListener = listener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_friendevent, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		
		holder.mItem = mValues.get(position);
		holder.mIdView.setText(mValues.get(position).id);
		holder.mContentView.setText(mValues.get(position).content);
		
		holder.mView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (null != mListener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onFriendEventFragmentInteractionListener(holder.mItem);
				}
			}
		});
	}
	
	@Override
	public int getItemCount() {
		
		return mValues.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		public final View mView;
		public final TextView mIdView;
		public final TextView mContentView;
		public DummyItem mItem;
		
		public ViewHolder(View view) {
			
			super(view);
			mView = view;
			mIdView = (TextView) view.findViewById(R.id.id);
			mContentView = (TextView) view.findViewById(R.id.content);
		}
		
		@Override
		public String toString() {
			
			return super.toString() + " '" + mContentView.getText() + "'";
		}
	}
}
