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
import com.wasder.wasderapp.models.EsportsItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class EsportsRecyclerAdapter
		extends BaseRecyclerAdapter<EsportsItem, EsportsRecyclerAdapter.EsportsViewHolder> {
	
	public EsportsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(EsportsItem.class, R.layout.esports_item,
		      EsportsViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyEsportsRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final EsportsViewHolder viewHolder, EsportsItem model, int position) {
		
		viewHolder.mview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.EsportsFragment, viewHolder.feedModel, "Item");
			}
		});
		viewHolder.idTextView.setText("Twitch Stream id");
		viewHolder.contentTextView.setText("Twitch Stream content");
	}
	
	public static class EsportsViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView idTextView;
		TextView contentTextView;
		EsportsItem feedModel;
		
		public EsportsViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			idTextView = itemView.findViewById(R.id.id);
			contentTextView = itemView.findViewById(R.id.content);
		}
	}
}
