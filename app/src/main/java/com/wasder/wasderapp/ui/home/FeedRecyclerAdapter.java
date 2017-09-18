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
import com.wasder.wasderapp.models.FeedItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class FeedRecyclerAdapter
		extends BaseRecyclerAdapter<FeedItem, FeedRecyclerAdapter.FeedViewHolder> {
	
	public FeedRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(FeedItem.class, R.layout.feed_item,
		      FeedViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"), "FeedRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final FeedViewHolder viewHolder, final FeedItem feedItem, int position) {
		
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), FeedDetailsActivity.class);
				intent.putExtra("data_item", feedItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener("FeedFragment", FeedDetailsActivity.class, "Details");
			}
		});
		
		viewHolder.shareImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("FeedFragment", viewHolder.feedItem, "Share");
			}
		});
		
		viewHolder.photoImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("FeedFragment", viewHolder.feedItem, "Profile");
			}
		});
		viewHolder.titleTextView.setText(feedItem.getTitle());
		viewHolder.subheadTextView.setText(feedItem.getSubhead());
		final String imageUrl = feedItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = feedItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.feedImageView.setImageDrawable(mContext.getResources()
		                                                  .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(feedItem.getSupplementaryText());
	}
	
	public static class FeedViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView titleTextView;
		TextView subheadTextView;
		ImageButton photoImageButton;
		ImageView feedImageView;
		TextView supplementaryTextView;
		ImageButton commentImageButton;
		ImageButton likeImageButton;
		ImageButton bookmarkImageButton;
		ImageButton shareImageButton;
		ImageButton detailsImageButton;
		FeedItem feedItem;
		
		public FeedViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			titleTextView = itemView.findViewById(R.id.feed_card_header);
			subheadTextView = itemView.findViewById(R.id.feed_card_subheader);
			photoImageButton = itemView.findViewById(R.id.feed_card_avatar);
			feedImageView = itemView.findViewById(R.id.feed_card_rich_media);
			supplementaryTextView = itemView.findViewById(R.id.feed_card_supplementary_text);
			commentImageButton = itemView.findViewById(R.id.feed_card_comment_imageButton);
			likeImageButton = itemView.findViewById(R.id.feed_likee_imageButton);
			bookmarkImageButton = itemView.findViewById(R.id.feed_bookmark_imageButton);
			shareImageButton = itemView.findViewById(R.id.feed_share_imageButton);
			detailsImageButton = itemView.findViewById(R.id.feed_details_imageButton);
		}
	}
}
