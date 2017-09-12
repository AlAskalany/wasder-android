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
import com.wasder.wasderapp.models.TwitchStreamItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class TwitchStreamRecyclerAdapter
		extends BaseRecyclerAdapter<TwitchStreamItem, TwitchStreamRecyclerAdapter.TwitchStreamViewHolder> {
	
	public TwitchStreamRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(TwitchStreamItem.class, R.layout.esports_item,
		      TwitchStreamViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyTwitchStreamAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final TwitchStreamViewHolder viewHolder, TwitchStreamItem model, int position) {
		
		viewHolder.mview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.TwitchStreamFragment, viewHolder.twitchStreamItem);
			}
		});
		viewHolder.idTextView.setText("Twitch Stream id");
		viewHolder.contentTextView.setText("Twitch Stream content");
	}
	
	public static class TwitchStreamViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView idTextView;
		TextView contentTextView;
		TwitchStreamItem twitchStreamItem;
		
		public TwitchStreamViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			idTextView = itemView.findViewById(R.id.id);
			contentTextView = itemView.findViewById(R.id.content);
		}
	}
}
