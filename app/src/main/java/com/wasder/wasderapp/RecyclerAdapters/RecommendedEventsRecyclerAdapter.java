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
import com.wasder.wasderapp.models.RecommendedEventItem;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class RecommendedEventsRecyclerAdapter
		extends BaseRecyclerAdapter<RecommendedEventItem, RecommendedEventsRecyclerAdapter.RecommendedEventViewHolder> {
	
	public RecommendedEventsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager,
			OnFragmentInteractionListener mListener) {
		
		super(RecommendedEventItem.class, R.layout.recommended_event_item,
		      RecommendedEventViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyRecommendedRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final RecommendedEventViewHolder viewHolder, RecommendedEventItem model, int position) {
		
		viewHolder.mview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(TAG, viewHolder.recommendedEventItem);
			}
		});
		viewHolder.idTextView.setText("Twitch Stream id");
		viewHolder.contentTextView.setText("Twitch Stream content");
	}
	
	public static class RecommendedEventViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView idTextView;
		TextView contentTextView;
		RecommendedEventItem recommendedEventItem;
		
		public RecommendedEventViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			mview = itemView;
			idTextView = itemView.findViewById(R.id.id);
			contentTextView = itemView.findViewById(R.id.content);
		}
	}
}
