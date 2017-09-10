package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.OnFragmentInteractionListener;
import com.wasder.wasderapp.ui.RecyclerViewAdapterBase;
import com.wasder.wasderapp.ui.TabFragment;
import com.wasder.wasderapp.ui.WasderDataModel;
import com.wasder.wasderapp.util.Helpers;

public class CreatorFeedFragment extends TabFragment {
	
	public CreatorFeedFragment() {
		
		super("Creators", 1, R.layout.fragment_creatorfeed_list, 2);
	}
	
	public static class MyCreatorFeedRecyclerViewAdapter extends RecyclerViewAdapterBase<CreatorFeedModel, MyCreatorFeedRecyclerViewAdapter
			.CreatorFeedViewHolder> {
		
		public MyCreatorFeedRecyclerViewAdapter(Context context, final RecyclerView recyclerView, final LinearLayoutManager linearLayoutManager,
		                                        OnFragmentInteractionListener mListener) {
			
			super(CreatorFeedModel.class, R.layout.fragment_creatorfeed, CreatorFeedViewHolder.class, FirebaseDatabase.getInstance().getReference()
					.child("feed"), "MyCreatorAdapter", context, mListener, linearLayoutManager);
		}
		
		@Override
		protected void populateViewHolder(final CreatorFeedViewHolder viewHolder, CreatorFeedModel creatorFeedModel, int position) {
			
			viewHolder.mView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					mListener.onFragmentInteractionListener(viewHolder.creatorFeedModel);
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
		
		public static class CreatorFeedViewHolder extends RecyclerView.ViewHolder {
			
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
	
	/**
	 * Helper class for providing sample content for user interfaces created by
	 * Android template wizards.
	 * <p>
	 * TODO: Replace all uses of this class before publishing your app.
	 */
	public static class CreatorFeedModel implements WasderDataModel {
		
		private String uId;
		private String id;
		private String title;
		private String subhead;
		private String photoUrl;
		private String imageUrl;
		private String supplementaryText;
		
		public CreatorFeedModel() {
			
		}
		
		public CreatorFeedModel(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
			
			this.uId = uId;
			this.id = id;
			this.title = title;
			this.subhead = subhead;
			this.photoUrl = photoUrl;
			this.imageUrl = imageUrl;
			this.supplementaryText = supplementaryText;
		}
		
		public String getId() {
			
			return this.id;
		}
		
		public void setId(String id) {
			
			this.id = id;
		}
		
		public String getTitle() {
			
			return title;
		}
		
		public void setTitle(String title) {
			
			this.title = title;
		}
		
		public String getSubhead() {
			
			return subhead;
		}
		
		public void setSubhead(String subhead) {
			
			this.subhead = subhead;
		}
		
		public String getPhotoUrl() {
			
			return photoUrl;
		}
		
		public void setPhotoUrl(String photoUrl) {
			
			this.photoUrl = photoUrl;
		}
		
		public String getImageUrl() {
			
			return imageUrl;
		}
		
		public void setImageUrl(String imageUrl) {
			
			this.imageUrl = imageUrl;
		}
		
		public String getSupplementaryText() {
			
			return supplementaryText;
		}
		
		public void setSupplementaryText(String supplementaryText) {
			
			this.supplementaryText = supplementaryText;
		}
		
		public String getuId() {
			
			return uId;
		}
		
		public void setuId(String uId) {
			
			this.uId = uId;
		}
	}
}
