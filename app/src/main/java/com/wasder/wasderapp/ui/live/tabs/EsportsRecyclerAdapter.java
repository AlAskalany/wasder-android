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
import com.wasder.wasderapp.models.EsportsItem;
import com.wasder.wasderapp.ui.live.EsportsDetailsActivity;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class EsportsRecyclerAdapter
		extends BaseRecyclerAdapter<EsportsItem, EsportsRecyclerAdapter.EsportsViewHolder> {
	
	public EsportsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(EsportsItem.class,
		      R.layout.esports_item,
		      EsportsViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "EsportsRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final EsportsViewHolder viewHolder, final EsportsItem esportsItem, int position) {
		
		viewHolder.detailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent(viewHolder.itemView.getContext(), EsportsDetailsActivity.class);
				intent.putExtra("esports_item", esportsItem);
				viewHolder.itemView.getContext()
				                   .startActivity(intent);
				mListener.onFragmentInteractionListener("EsportsFragment", viewHolder.esportsItem, "Details");
			}
		});
		
		viewHolder.shareImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("EsportsFragment", viewHolder.esportsItem, "Share");
			}
		});
		
		viewHolder.photoImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener("EsportsFragment", viewHolder.esportsItem, "Profile");
			}
		});
		viewHolder.titleTextView.setText(esportsItem.getTitle());
		viewHolder.subheadTextView.setText(esportsItem.getSubhead());
		final String imageUrl = esportsItem.getImageUrl();
		Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
		final String photoUrl = esportsItem.getPhotoUrl();
		Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
		viewHolder.feedImageView.setImageDrawable(mContext.getResources()
		                                                  .getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(esportsItem.getSupplementaryText());
	}
	
	public static class EsportsViewHolder
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
		EsportsItem esportsItem;
		
		public EsportsViewHolder(View itemView) {
			
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
			detailsImageButton = itemView.findViewById(R.id.esports_details_imageButton);
		}
	}
}
