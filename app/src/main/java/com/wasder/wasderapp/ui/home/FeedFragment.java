package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.OnFragmentInteractionListener;
import com.wasder.wasderapp.ui.TabFragment;
import com.wasder.wasderapp.util.Helpers;

public class FeedFragment extends TabFragment {
	
	public FeedFragment() {
		
		super(1, R.layout.fragment_feed_list, 1);
	}
	
	/**
	 * Wasder AB CONFIDENTIAL
	 * Created by ahmed on 9/8/2017.
	 */
	
	public static class MyFeedRecyclerAdapter extends FirebaseRecyclerAdapter<FeedModel, MyFeedRecyclerAdapter.FeedViewHolder> {
		
		private static final String TAG = "FeedAdapter";
		private Context mContext;
		private OnFragmentInteractionListener mListener;
		
		public MyFeedRecyclerAdapter(Context context, final RecyclerView feedRecyclerView, final LinearLayoutManager feedLinearLayoutManager,
		                             OnFragmentInteractionListener mListener) {
			
			super(FeedModel.class, R.layout.fragment_feed, FeedViewHolder.class, FirebaseDatabase.getInstance().getReference().child("feed"));
			this.mContext = context;
			this.mListener = mListener;
			this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
				
				@Override
				public void onItemRangeInserted(int positionStart, int itemCount) {
					
					super.onItemRangeInserted(positionStart, itemCount);
					int feedModelCount = getItemCount();
					int lastVisiblePosition = feedLinearLayoutManager.findLastVisibleItemPosition();
					if (lastVisiblePosition == -1 || ((positionStart >= (feedModelCount - 1)) && (lastVisiblePosition == (positionStart - 1)))) {
						feedRecyclerView.scrollToPosition(positionStart);
					}
				}
			});
		}
		
		@Override
		protected FeedModel parseSnapshot(DataSnapshot snapshot) {
			
			FeedModel feedModel = super.parseSnapshot(snapshot);
			if (feedModel != null) {
				feedModel.setId(snapshot.getKey());
			}
			return feedModel;
		}
		
		@Override
		protected void populateViewHolder(final FeedViewHolder viewHolder, FeedModel model, int position) {
			
			viewHolder.mview.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					mListener.onFragmentInteractionListener(viewHolder.feedModel);
				}
			});
			viewHolder.titleTextView.setText(model.getTitle());
			viewHolder.subheadTextView.setText(model.getSubhead());
			final String imageUrl = model.getImageUrl();
			Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.feedImageView, false, 0);
			final String photoUrl = model.getPhotoUrl();
			Helpers.Firebase.DownloadUrlImage(photoUrl, viewHolder.photoImageButton, true, R.drawable.avatar);
			viewHolder.feedImageView.setImageDrawable(mContext.getDrawable(R.drawable.event_pic));
			viewHolder.supplementaryTextView.setText(model.getSupplementaryText());
		}
		
		public static class FeedViewHolder extends RecyclerView.ViewHolder {
			
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
			FeedModel feedModel;
			
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
			}
		}
	}
	
	/**
	 * Wasder AB CONFIDENTIAL
	 * Created by ahmed on 9/8/2017.
	 */
	
	public static class FeedModel {
		
		private String uId;
		private String id;
		private String title;
		private String subhead;
		private String photoUrl;
		private String imageUrl;
		private String supplementaryText;
		
		public FeedModel() {
			
		}
		
		public FeedModel(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
			
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
