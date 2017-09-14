package com.wasder.wasderapp.RecyclerAdapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.models.TwitchLiveItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class TwitchLiveRecyclerAdapter
		extends BaseRecyclerAdapter<TwitchLiveItem, TwitchLiveRecyclerAdapter.TwitchLiveViewHolder> {
	
	public TwitchLiveRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(TwitchLiveItem.class, R.layout.esports_item,
		      TwitchLiveViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyTwitchLiveAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final TwitchLiveViewHolder viewHolder, TwitchLiveItem model, int position) {
		
		viewHolder.twitchLiveDetailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.TwitchLiveFragment, viewHolder.twitchLiveItem, "Item");
			}
		});
		viewHolder.idTextView.setText("Twitch Stream id");
		viewHolder.contentTextView.setText("Twitch Stream content");
	}
	
	public static class TwitchLiveViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView idTextView;
		TextView contentTextView;
		TwitchLiveItem twitchLiveItem;
		ImageButton twitchLiveDetailsImageButton;
		
		public TwitchLiveViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			idTextView = itemView.findViewById(R.id.id);
			contentTextView = itemView.findViewById(R.id.content);
			twitchLiveDetailsImageButton = itemView.findViewById(R.id.twitch_live_details_imageButton);
		}
	}
}
