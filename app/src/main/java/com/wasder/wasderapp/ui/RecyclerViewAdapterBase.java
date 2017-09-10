package com.wasder.wasderapp.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public abstract class RecyclerViewAdapterBase<M extends WasderDataModel, V extends RecyclerView.ViewHolder> extends FirebaseRecyclerAdapter<M, V> {
	
	protected static String TAG;
	protected Context mContext;
	protected OnFragmentInteractionListener mListener;
	protected RecyclerView.LayoutManager layoutManager;
	
	public RecyclerViewAdapterBase(Class<M> modelClass, int modelLayout, Class<V> viewHolderClass, Query ref, String TAG, Context mContext,
	                               OnFragmentInteractionListener mListener, final LinearLayoutManager layoutManager) {
		
		super(modelClass, modelLayout, viewHolderClass, ref);
		this.TAG = TAG;
		this.mContext = mContext;
		this.mListener = mListener;
		this.layoutManager = layoutManager;
		this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
			
			@Override
			public void onItemRangeInserted(int positionStart, int itemCount) {
				
				super.onItemRangeInserted(positionStart, itemCount);
				int feedModelCount = getItemCount();
				int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
				if (lastVisiblePosition == -1 || ((positionStart >= (feedModelCount - 1)) && (lastVisiblePosition == (positionStart - 1)))) {
					layoutManager.scrollToPosition(positionStart);
				}
			}
		});
		
	}
	
	@Override
	protected M parseSnapshot(DataSnapshot snapshot) {
		
		M model = super.parseSnapshot(snapshot);
		if (model != null) {
			model.setId(snapshot.getKey());
		}
		return model;
	}
	
	@Override
	abstract protected void populateViewHolder(final V viewHolder, M model, int position);
}