package com.wasder.wasderapp.ui.profile;

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
import com.wasder.wasderapp.models.EventItem;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class EventRecyclerAdapter
		extends BaseRecyclerAdapter<EventItem, EventRecyclerAdapter.EventViewHolder> {
	
	public EventRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(EventItem.class,
		      R.layout.events_item,
		      EventViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "EventsRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final EventViewHolder viewHolder, final EventItem eventItem, int position) {
		
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), EventDetailsActivity.class);
				intent.putExtra("event_item", eventItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener(Helpers.TAG.EventsFragment, viewHolder.eventItem, "Details");
			}
		});
		
		viewHolder.shareImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.EventsFragment, viewHolder.eventItem, "Share");
			}
		});
		
		viewHolder.photoImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(Helpers.TAG.EventsFragment, viewHolder.eventItem, "Profile");
			}
		});
		viewHolder.titleTextView.setText(eventItem.getTitle());
		viewHolder.subheadTextView.setText(eventItem.getSubhead());
		final String imageUrl = eventItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = eventItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.feedImageView.setImageDrawable(mContext.getResources()
		                                                  .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(eventItem.getSupplementaryText());
	}
	
	public static class EventViewHolder
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
		EventItem eventItem;
		
		public EventViewHolder(View itemView) {
			
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
			detailsImageButton = itemView.findViewById(R.id.events_details_imageButton);
		}
	}
}
