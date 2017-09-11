package com.wasder.wasderapp.RecyclerAdapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.RecyclerViewAdapterBase;
import com.wasder.wasderapp.models.CreatorFeedModel;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */
public class MyCreatorFeedRecyclerViewAdapter
		extends RecyclerViewAdapterBase<CreatorFeedModel, MyCreatorFeedRecyclerViewAdapter.CreatorFeedViewHolder> {
	
	public MyCreatorFeedRecyclerViewAdapter(Context context, LinearLayoutManager linearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(CreatorFeedModel.class,
		      R.layout.fragment_creatorfeed,
		      CreatorFeedViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "MyCreatorAdapter",
		      context,
		      mListener,
		      linearLayoutManager);
	}
	
	@Override
	protected void populateViewHolder(final CreatorFeedViewHolder viewHolder, CreatorFeedModel creatorFeedModel, int position) {
		
		viewHolder.mView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(TAG, viewHolder.creatorFeedModel);
			}
		});
		viewHolder.titleTextView.setText(creatorFeedModel.getTitle());
		viewHolder.subheadTextView.setText(creatorFeedModel.getSubhead());
		final String imageUrl = creatorFeedModel.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.creatorFeedImageView, false, 0);
		
		final String photoUrl = creatorFeedModel.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		
		viewHolder.creatorFeedImageView.setImageDrawable(mContext.getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(creatorFeedModel.getSupplementaryText());
	}
	
	public static class CreatorFeedViewHolder
			extends RecyclerView.ViewHolder {
		
		View mView;
		TextView titleTextView;
		TextView subheadTextView;
		ImageButton photoImageButton;
		ImageView creatorFeedImageView;
		TextView supplementaryTextView;
		ImageButton commentImageButton;
		ImageButton likeImageButton;
		ImageButton bookmarkImageButton;
		ImageButton shareImageButton;
		CreatorFeedModel creatorFeedModel;
		
		public CreatorFeedViewHolder(View view) {
			
			super(view);
			mView = view;
			creatorFeedImageView = view.findViewById(R.id.creator_feed_imageView);
			subheadTextView = view.findViewById(R.id.creator_feed_subhead_textView);
			likeImageButton = view.findViewById(R.id.creator_feed_likee_imageButton);
			shareImageButton = view.findViewById(R.id.creator_feed_share_imageButton);
			commentImageButton = view.findViewById(R.id.creator_feed_comment_imageButton);
			//mDetailsButton = (ImageButton) view.findViewById(R.id.feed_card_avatar);
			titleTextView = view.findViewById(R.id.creator_feed_name_textView);
			supplementaryTextView = view.findViewById(R.id.creator_feed_description_textView);
			photoImageButton = view.findViewById(R.id.creator_feed_card_avatar);
		}
	}
}
