package com.wasder.wasderapp.RecyclerAdapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.models.EventItem;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class EventRecyclerAdapter
		extends BaseRecyclerAdapter<EventItem, EventRecyclerAdapter.EventViewHolder> {
	
	public EventRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(EventItem.class, R.layout.esports_item,
		      EventViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyEventRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final EventViewHolder viewHolder, EventItem model, int position) {
		
		viewHolder.mview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(TAG, viewHolder.eventItem);
			}
		});
		viewHolder.idTextView.setText("Twitch Stream id");
		viewHolder.contentTextView.setText("Twitch Stream content");
	}
	
	public static class EventViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView idTextView;
		TextView contentTextView;
		EventItem eventItem;
		
		public EventViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			idTextView = itemView.findViewById(R.id.id);
			contentTextView = itemView.findViewById(R.id.content);
		}
	}
}
