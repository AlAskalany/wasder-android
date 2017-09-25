package com.wasder.wasderapp.ui.home.tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.dialogs.FeedRegisterDialog;
import com.wasder.wasderapp.models.FeedItem;
import com.wasder.wasderapp.ui.DisplayImageActivity;
import com.wasder.wasderapp.ui.home.FeedDetailsActivity;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class FeedRecyclerAdapter extends BaseRecyclerAdapter<FeedItem, FeedRecyclerAdapter.FeedViewHolder> {
	
	public FeedRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(FeedItem.class, R.layout.feed_item, FeedViewHolder.class, FirebaseDatabase.getInstance().getReference().child("feed"),
				"FeedRecyclerAdapter", context, mListener, feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final FeedViewHolder viewHolder, final FeedItem feedItem, int position) {
		
		viewHolder.feedImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(view.getContext(), DisplayImageActivity.class);
				intent.putExtra("data_item", feedItem.getImageUrl());
				view.getContext().startActivity(intent);
			}
		});
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View view) {
				
				PopupMenu feedPopupMenu = new PopupMenu(view.getContext(), view, Gravity.RIGHT);
				MenuInflater inflater = feedPopupMenu.getMenuInflater();
				inflater.inflate(R.menu.feed_menu, feedPopupMenu.getMenu());
				feedPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						
						int id = item.getItemId();
						switch (id) {
							case R.id.action_feed_bookmark:
								return true;
							case R.id.action_feed_register:
								FeedRegisterDialog feedRegisterDialog = new FeedRegisterDialog(view.getContext(), feedItem);
								feedRegisterDialog.show();
								
								return true;
							case R.id.action_feed_set_reminder:
								return true;
							default:
						}
						return false;
					}
				});
				feedPopupMenu.show();
				mListener.onFragmentInteractionListener("FeedFragment", FeedDetailsActivity.class, "Details");
			}
		});
		
		viewHolder.shareButton.setOnClickListener(new View.OnClickListener() {
			
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
		viewHolder.titleTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(view.getContext(), FeedDetailsActivity.class);
				intent.putExtra("data_item", feedItem);
				view.getContext().startActivity(intent);
			}
		});
		viewHolder.titleTextView.setText(feedItem.getTitle());
		viewHolder.subheadTextView.setText(feedItem.getSubhead());
		final String imageUrl = feedItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = feedItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.supplementaryTextView.setText(feedItem.getSupplementaryText());
	}
	
	public static class FeedViewHolder extends RecyclerView.ViewHolder {
		
		final View mview;
		final TextView titleTextView;
		final TextView subheadTextView;
		final ImageButton photoImageButton;
		final ImageView feedImageView;
		final TextView supplementaryTextView;
		final ImageButton commentImageButton;
		final ImageButton likeImageButton;
		final ImageButton bookmarkImageButton;
		final ImageButton shareButton;
		final ImageButton detailsImageButton;
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
			shareButton = itemView.findViewById(R.id.feed_share_imageButton);
			detailsImageButton = itemView.findViewById(R.id.feed_details_imageButton);
		}
	}
}
