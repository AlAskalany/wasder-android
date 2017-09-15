package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.models.CreatorFeedItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */
public class CreatorFeedRecyclerAdapter
		extends BaseRecyclerAdapter<CreatorFeedItem, CreatorFeedRecyclerAdapter.CreatorFeedViewHolder> {
	
	public CreatorFeedRecyclerAdapter(Context context, LinearLayoutManager linearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(CreatorFeedItem.class, R.layout.creators_feed_item,
		      CreatorFeedViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"), "CreatorFeedAdapter",
		      context,
		      mListener,
		      linearLayoutManager);
	}
	
	@Override
	protected void populateViewHolder(final CreatorFeedViewHolder viewHolder, final CreatorFeedItem creatorFeedItem, int position) {
		
		viewHolder.creatorFeedDetailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), CreatorFeedActivity.class);
				intent.putExtra("creator_feed_item", creatorFeedItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener(Helpers.TAG.CreatorsFragment, viewHolder.creatorFeedItem, "Item");
			}
		});
		viewHolder.titleTextView.setText(creatorFeedItem.getTitle());
		viewHolder.subheadTextView.setText(creatorFeedItem.getSubhead());
		final String imageUrl = creatorFeedItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.creatorFeedImageView, false, 0);
		
		final String photoUrl = creatorFeedItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		
		viewHolder.creatorFeedImageView.setImageDrawable(mContext.getResources()
		                                                         .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(creatorFeedItem.getSupplementaryText());
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
		CreatorFeedItem creatorFeedItem;
		ImageButton creatorFeedDetailsImageButton;
		
		public CreatorFeedViewHolder(View view) {
			
			super(view);
			mView = view;
			creatorFeedImageView = view.findViewById(R.id.creator_feed_imageView);
			subheadTextView = view.findViewById(R.id.creator_feed_subhead_textView);
			likeImageButton = view.findViewById(R.id.creator_feed_like_imageButton);
			shareImageButton = view.findViewById(R.id.creator_feed_share_imageButton);
			commentImageButton = view.findViewById(R.id.creator_feed_comment_imageButton);
			//mDetailsButton = (ImageButton) view.findViewById(R.id.feed_card_avatar);
			titleTextView = view.findViewById(R.id.creator_feed_name_textView);
			supplementaryTextView = view.findViewById(R.id.creator_feed_description_textView);
			photoImageButton = view.findViewById(R.id.creator_feed_card_avatar);
			creatorFeedDetailsImageButton = itemView.findViewById(R.id.creators_feed_details_imageButton);
		}
	}
}
