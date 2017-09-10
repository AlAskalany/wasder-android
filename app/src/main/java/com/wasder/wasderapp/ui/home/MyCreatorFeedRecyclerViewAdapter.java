package com.wasder.wasderapp.ui.home;

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
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.OnFragmentInteractionListener;
import com.wasder.wasderapp.ui.home.models.CreatorFeedModel;

public class MyCreatorFeedRecyclerViewAdapter extends FirebaseRecyclerAdapter<CreatorFeedModel,
		MyCreatorFeedRecyclerViewAdapter.CreatorFeedViewHolder> {
	
	private static final String TAG = "FeedAdapter";
	private Context mContext;
	private OnFragmentInteractionListener mListener;
	
	public MyCreatorFeedRecyclerViewAdapter(Context context, final RecyclerView recyclerView,
	                                        final LinearLayoutManager linearLayoutManager,
	                                        OnFragmentInteractionListener mListener) {
		
		super(CreatorFeedModel.class, R.layout.fragment_creatorfeed, CreatorFeedViewHolder.class,
				FirebaseDatabase.getInstance().getReference().child("feed"));
		this.mContext = context;
		this.mListener = mListener;
		this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
			
			@Override
			public void onItemRangeInserted(int positionStart, int itemCount) {
				
				super.onItemRangeInserted(positionStart, itemCount);
				int creatorFeedModelCount = getItemCount();
				int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
				if (lastVisiblePosition == -1 || ((positionStart >= (creatorFeedModelCount - 1))
						&& (lastVisiblePosition == (positionStart - 1)))) {
					recyclerView.scrollToPosition(positionStart);
				}
			}
		});
	}
	
	@Override
	protected CreatorFeedModel parseSnapshot(DataSnapshot snapshot) {
		
		CreatorFeedModel creatorFeedModel = super.parseSnapshot(snapshot);
		if (creatorFeedModel != null) {
			creatorFeedModel.setId(snapshot.getKey());
		}
		return creatorFeedModel;
	}
	
	@Override
	protected void populateViewHolder(final CreatorFeedViewHolder viewHolder, CreatorFeedModel
			creatorFeedModel, int position) {
		
		viewHolder.mView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(viewHolder.creatorFeedModel);
			}
		});
		viewHolder.titleTextView.setText(creatorFeedModel.getTitle());
		viewHolder.subheadTextView.setText(creatorFeedModel.getSubhead());
		final String imageUrl = creatorFeedModel.getImageUrl();
		if (imageUrl != null) {
			if (imageUrl.startsWith("gs://")) {
				StorageReference storageReference = FirebaseStorage.getInstance()
						.getReferenceFromUrl(imageUrl);
				storageReference.getDownloadUrl().addOnCompleteListener(new
						                                                        OnCompleteListener<Uri>() {
					
					@Override
					public void onComplete(@NonNull Task<Uri> task) {
						
						if (task.isSuccessful()) {
							String downloadUrl = task.getResult().toString();
							Glide.with(viewHolder.creatorFeedImageView.getContext()).load
									(imageUrl).into(viewHolder.creatorFeedImageView);
						} else {
							Log.d(TAG, "Getting Download URL was not successful", task
									.getException());
						}
					}
				});
			} else {
				Glide.with(viewHolder.creatorFeedImageView.getContext()).load(creatorFeedModel
						.getImageUrl()).into(viewHolder.creatorFeedImageView);
			}
		} else {
			viewHolder.creatorFeedImageView.setVisibility(ImageView.GONE);
		}
		final String photoUrl = creatorFeedModel.getPhotoUrl();
		if (photoUrl != null) {
			if (photoUrl.startsWith("gsL//")) {
				StorageReference storageReference = FirebaseStorage.getInstance()
						.getReferenceFromUrl(photoUrl);
				storageReference.getDownloadUrl().addOnCompleteListener(new
						                                                        OnCompleteListener<Uri>() {
					
					@Override
					public void onComplete(@NonNull Task<Uri> task) {
						
						if (task.isSuccessful()) {
							String downloadUrl = task.getResult().toString();
							Glide.with(viewHolder.photoImageButton.getContext()).load(photoUrl)
									.into(viewHolder.photoImageButton);
						} else {
							Log.d(TAG, "Getting Download URL was not successful", task
									.getException());
						}
					}
				});
			} else {
				Glide.with(viewHolder.photoImageButton.getContext()).load(creatorFeedModel
						.getPhotoUrl()).into(viewHolder.photoImageButton);
			}
			
		} else {
			viewHolder.photoImageButton.setImageDrawable(mContext.getDrawable(R.drawable.avatar));
		}
		
		viewHolder.creatorFeedImageView.setImageDrawable(mContext.getDrawable(R.drawable
				.event_pic));
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
			creatorFeedImageView = (ImageView) view.findViewById(R.id.creator_feed_imageView);
			subheadTextView = (TextView) view.findViewById(R.id.creator_feed_subhead_textView);
			likeImageButton = (ImageButton) view.findViewById(R.id.creator_feed_likee_imageButton);
			shareImageButton = (ImageButton) view.findViewById(R.id
					.creator_feed_share_imageButton);
			commentImageButton = (ImageButton) view.findViewById(R.id
					.creator_feed_comment_imageButton);
			//mDetailsButton = (ImageButton) view.findViewById(R.id.feed_card_avatar);
			titleTextView = (TextView) view.findViewById(R.id.creator_feed_name_textView);
			supplementaryTextView = (TextView) view.findViewById(R.id
					.creator_feed_description_textView);
			photoImageButton = (ImageButton) view.findViewById(R.id.creator_feed_card_avatar);
		}
	}
}
