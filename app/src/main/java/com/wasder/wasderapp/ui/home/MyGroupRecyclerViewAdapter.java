package com.wasder.wasderapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link GroupFragment.OnGroupFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder> {
	
	private final List<DummyItem> mValues;
	private final GroupFragment.OnGroupFragmentInteractionListener mListener;
	private GroupFragment.OnGroupDetailsListener mGroupDetailsListener;
	
	public MyGroupRecyclerViewAdapter(List<DummyItem> items, GroupFragment.OnGroupFragmentInteractionListener listener, GroupFragment
			.OnGroupDetailsListener groupDetailsListener) {
		
		mValues = items;
		mListener = listener;
		mGroupDetailsListener = groupDetailsListener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		
		holder.mItem = mValues.get(position);
		holder.mIdView.setText(mValues.get(position).id);
		holder.mContentView.setText(mValues.get(position).content);
		holder.mDetailsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mGroupDetailsListener.onGroupDetailsListener();
			}
		});
		
		holder.mView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (null != mListener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onGroupFragmentInteractionListener(holder.mItem);
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
		public final ImageButton mDetailsButton;
		public DummyItem mItem;
		
		public ViewHolder(View view) {
			
			super(view);
			mView = view;
			mIdView = (TextView) view.findViewById(R.id.id);
			mContentView = (TextView) view.findViewById(R.id.content);
			mDetailsButton = (ImageButton) view.findViewById(R.id.group_details_button);
		}
		
		@Override
		public String toString() {
			
			return super.toString() + " '" + mContentView.getText() + "'";
		}
	}
}
