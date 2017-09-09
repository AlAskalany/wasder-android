package com.wasder.wasderapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wasder.wasderapp.models.FeedModel;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class FeedFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<FeedModel, FeedFirebaseRecyclerAdapter.FeedViewHolder> {
	
	private static final String TAG = "FeedAdapter";
	private Context mContext;
	
	public FeedFirebaseRecyclerAdapter(Context context, final RecyclerView feedRecyclerView, final LinearLayoutManager feedLinearLayoutManager) {
		
		super(FeedModel.class, R.layout.fragment_feed, FeedViewHolder.class, FirebaseDatabase.getInstance().getReference().child("feed"));
		this.mContext = context;
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
		
		viewHolder.titleTextView.setText(model.getTitle());
		viewHolder.subheadTextView.setText(model.getSubhead());
		final String imageUrl = model.getImageUrl();
		if (imageUrl != null) {
			if (imageUrl.startsWith("gs://")) {
				StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
				storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
					
					@Override
					public void onComplete(@NonNull Task<Uri> task) {
						
						if (task.isSuccessful()) {
							String downloadUrl = task.getResult().toString();
							Glide.with(viewHolder.feedImageView.getContext()).load(imageUrl).into(viewHolder.feedImageView);
						} else {
							Log.d(TAG, "Getting Download URL was not successful", task.getException());
						}
					}
				});
			} else {
				Glide.with(viewHolder.feedImageView.getContext()).load(model.getImageUrl()).into(viewHolder.feedImageView);
			}
		} else {
			viewHolder.feedImageView.setVisibility(ImageView.GONE);
		}
		final String photoUrl = model.getPhotoUrl();
		if (photoUrl != null) {
			if (photoUrl.startsWith("gsL//")) {
				StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(photoUrl);
				storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
					
					@Override
					public void onComplete(@NonNull Task<Uri> task) {
						
						if (task.isSuccessful()) {
							String downloadUrl = task.getResult().toString();
							Glide.with(viewHolder.photoImageButton.getContext()).load(photoUrl).into(viewHolder.photoImageButton);
						} else {
							Log.d(TAG, "Getting Download URL was not successful", task.getException());
						}
					}
				});
			} else {
				Glide.with(viewHolder.photoImageButton.getContext()).load(model.getPhotoUrl()).into(viewHolder.photoImageButton);
			}
			
		} else {
			viewHolder.photoImageButton.setImageDrawable(mContext.getDrawable(R.drawable.avatar));
		}
		
		viewHolder.feedImageView.setImageDrawable(mContext.getDrawable(R.drawable.event_pic));
		viewHolder.supplementaryTextView.setText(model.getSupplementaryText());
	}
	
	public static class FeedViewHolder extends RecyclerView.ViewHolder {
		
		TextView titleTextView;
		TextView subheadTextView;
		ImageButton photoImageButton;
		ImageView feedImageView;
		TextView supplementaryTextView;
		ImageButton commentImageButton;
		ImageButton likeImageButton;
		ImageButton bookmarkImageButton;
		ImageButton shareImageButton;
		
		public FeedViewHolder(View itemView) {
			
			super(itemView);
			titleTextView = (TextView) itemView.findViewById(R.id.feed_card_header);
			subheadTextView = (TextView) itemView.findViewById(R.id.feed_card_subheader);
			photoImageButton = (ImageButton) itemView.findViewById(R.id.feed_card_avatar);
			feedImageView = (ImageView) itemView.findViewById(R.id.feed_card_rich_media);
			supplementaryTextView = (TextView) itemView.findViewById(R.id.feed_card_supplementary_text);
			commentImageButton = (ImageButton) itemView.findViewById(R.id.feed_card_comment_imageButton);
			likeImageButton = (ImageButton) itemView.findViewById(R.id.feed_likee_imageButton);
			bookmarkImageButton = (ImageButton) itemView.findViewById(R.id.feed_bookmark_imageButton);
			shareImageButton = (ImageButton) itemView.findViewById(R.id.feed_share_imageButton);
		}
	}
}
