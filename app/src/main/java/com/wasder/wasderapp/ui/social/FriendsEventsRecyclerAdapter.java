package com.wasder.wasderapp.ui.social;

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
import com.wasder.wasderapp.models.FriendEventItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class FriendsEventsRecyclerAdapter
		extends BaseRecyclerAdapter<FriendEventItem, FriendsEventsRecyclerAdapter.FriendsEventViewHolder> {
	
	public FriendsEventsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(FriendEventItem.class,
		      R.layout.friends_events_item,
		      FriendsEventViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "FeedRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final FriendsEventViewHolder viewHolder, final FriendEventItem friendEventItem, int position) {
		
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), FriendEventActivity.class);
				intent.putExtra("friend_event_item", friendEventItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener(Helpers.TAG.FeedFragment, viewHolder.friendEventItem, "Details");
			}
		});
		
		viewHolder.shareImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.FeedFragment, viewHolder.friendEventItem, "Share");
			}
		});
		
		viewHolder.photoImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.FeedFragment, viewHolder.friendEventItem, "Profile");
			}
		});
		viewHolder.titleTextView.setText(friendEventItem.getTitle());
		viewHolder.subheadTextView.setText(friendEventItem.getSubhead());
		final String imageUrl = friendEventItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = friendEventItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.feedImageView.setImageDrawable(mContext.getResources()
		                                                  .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(friendEventItem.getSupplementaryText());
	}
	
	public static class FriendsEventViewHolder
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
		FriendEventItem friendEventItem;
		
		public FriendsEventViewHolder(View itemView) {
			
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
			detailsImageButton = itemView.findViewById(R.id.friends_events_details_imageButton);
		}
	}
}
