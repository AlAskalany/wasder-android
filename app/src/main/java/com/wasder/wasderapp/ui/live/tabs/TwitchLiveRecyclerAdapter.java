package com.wasder.wasderapp.ui.live.tabs;

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
import com.wasder.wasderapp.models.TwitchLiveItem;
import com.wasder.wasderapp.ui.live.TwitchLiveDetailsActivity;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class TwitchLiveRecyclerAdapter
		extends BaseRecyclerAdapter<TwitchLiveItem, TwitchLiveRecyclerAdapter.TwitchLiveViewHolder> {
	
	public TwitchLiveRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(TwitchLiveItem.class,
		      R.layout.twitch_live_item,
		      TwitchLiveViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "FeedRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final TwitchLiveViewHolder viewHolder, final TwitchLiveItem twitchLiveItem, int position) {
		
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), TwitchLiveDetailsActivity.class);
				intent.putExtra("twitch_live_item", twitchLiveItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener("FeedFragment", viewHolder.twitchLiveItem, "Details");
			}
		});
		
		viewHolder.shareImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("FeedFragment", viewHolder.twitchLiveItem, "Share");
			}
		});
		
		viewHolder.photoImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("FeedFragment", viewHolder.twitchLiveItem, "Profile");
			}
		});
		viewHolder.titleTextView.setText(twitchLiveItem.getTitle());
		viewHolder.subheadTextView.setText(twitchLiveItem.getSubhead());
		final String imageUrl = twitchLiveItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = twitchLiveItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.feedImageView.setImageDrawable(mContext.getResources()
		                                                  .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(twitchLiveItem.getSupplementaryText());
	}
	
	public static class TwitchLiveViewHolder
			extends RecyclerView.ViewHolder {
		
		final View mview;
		final TextView titleTextView;
		final TextView subheadTextView;
		final ImageButton photoImageButton;
		final ImageView feedImageView;
		final TextView supplementaryTextView;
		final ImageButton commentImageButton;
		final ImageButton likeImageButton;
		final ImageButton bookmarkImageButton;
		final ImageButton shareImageButton;
		final ImageButton detailsImageButton;
		TwitchLiveItem twitchLiveItem;
		
		public TwitchLiveViewHolder(View itemView) {
			
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
			detailsImageButton = itemView.findViewById(R.id.twitch_live_details_imageButton);
		}
	}
}
