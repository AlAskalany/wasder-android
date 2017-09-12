package com.wasder.wasderapp.RecyclerAdapters;

import android.content.Context;
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
import com.wasder.wasderapp.models.GroupItem;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class GroupsRecyclerAdapter
		extends BaseRecyclerAdapter<GroupItem, GroupsRecyclerAdapter.GroupViewHolder> {
	
	public GroupsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		super(GroupItem.class, R.layout.group_item,
		      GroupViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"), "GroupsRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final GroupViewHolder viewHolder, GroupItem model, int position) {
		
		viewHolder.mview.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				mListener.onFragmentInteractionListener(TAG, viewHolder.feedModel);
			}
		});
		viewHolder.groupImageView.setImageDrawable(mContext.getResources()
		                                                   .getDrawable(R.drawable.gamers));
		viewHolder.groupTitleTextView.setText("My Group!");
		viewHolder.groupSubheadTextView.setText("Best Eva!");
		viewHolder.groupDetailsImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
			}
		});
	}
	
	public static class GroupViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		ImageView groupImageView;
		TextView groupTitleTextView;
		TextView groupSubheadTextView;
		ImageButton groupDetailsImageButton;
		GroupItem feedModel;
		
		public GroupViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			groupImageView = itemView.findViewById(R.id.fragment_group_imageView);
			groupTitleTextView = itemView.findViewById(R.id.fragment_group_title_textView);
			groupSubheadTextView = itemView.findViewById(R.id.fragment_group_subhead_textView);
			groupDetailsImageButton = itemView.findViewById(R.id.fragment_group_details_imageButton);
		}
	}
}
