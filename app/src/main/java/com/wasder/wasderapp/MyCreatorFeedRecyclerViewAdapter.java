package com.wasder.wasderapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wasder.wasderapp.dummy.DummyContent.DummyItem;

import java.util.List;

import static com.wasder.wasderapp.CreatorFeedFragment.OnAvatarListener;
import static com.wasder.wasderapp.CreatorFeedFragment.OnFeedItemShareListener;
import static com.wasder.wasderapp.CreatorFeedFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCreatorFeedRecyclerViewAdapter extends RecyclerView.Adapter<MyCreatorFeedRecyclerViewAdapter.ViewHolder> {
	
	private final List<DummyItem> mValues;
	private final OnListFragmentInteractionListener mListener;
	private OnFeedItemShareListener mShareListener;
	private OnAvatarListener mAvatarListener;
	
	public MyCreatorFeedRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener, OnAvatarListener avatarListener) {
		
		mValues = items;
		mListener = listener;
		mAvatarListener = avatarListener;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_creatorfeed, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		
		holder.mItem = mValues.get(position);
		//holder.mIdView.setText(mValues.get(position).id);
		//holder.mContentView.setText(mValues.get(position).content);
		//holder.mDetailsButton.setImageDrawable(mValues.get(position).image);
		holder.mAvatar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mAvatarListener.onAvatarListener();
			}
		});
		
		holder.mShareButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				if (null != mShareListener) {
					mShareListener.onFeedItemShareListener();
				}
			}
		});
		
		holder.mView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (null != mListener) {
					// Notify the active callbacks interface (the activity, if the
					// fragment is attached to one) that an item has been selected.
					mListener.onListFragmentInteraction(holder.mItem);
				}
			}
		});
	}
	
	@Override
	public int getItemCount() {
		
		return mValues.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		public final ImageButton mLikeButton;
		public final ImageButton mShareButton;
		public final ImageButton mCommentButton;
		//public final ImageButton mDetailsButton;
		public final TextView mFeedTitle;
		public final TextView mFeedContent;
		public final View mView;
		public final TextView mIdView;
		public final TextView mContentView;
		public final ImageButton mAvatar;
		public DummyItem mItem;
		
		public ViewHolder(View view) {
			
			super(view);
			mView = view;
			mIdView = (TextView) view.findViewById(R.id.id);
			mContentView = (TextView) view.findViewById(R.id.content);
			mLikeButton = (ImageButton) view.findViewById(R.id.feed_likee_imageButton);
			mShareButton = (ImageButton) view.findViewById(R.id.feed_share_imageButton);
			mCommentButton = (ImageButton) view.findViewById(R.id.feed_comment_imageButton);
			//mDetailsButton = (ImageButton) view.findViewById(R.id.feed_card_avatar);
			mFeedTitle = (TextView) view.findViewById(R.id.feed_card_header);
			mFeedContent = (TextView) view.findViewById(R.id.feed_card_supplementary_text);
			mAvatar = (ImageButton) view.findViewById(R.id.feed_card_avatar);
		}
		
		@Override
		public String toString() {
			
			return super.toString() + " '" + mContentView.getText() + "'";
		}
	}
}
