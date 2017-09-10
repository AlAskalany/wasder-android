package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.OnFragmentInteractionListener;
import com.wasder.wasderapp.ui.RecyclerViewAdapterBase;
import com.wasder.wasderapp.ui.TabFragment;
import com.wasder.wasderapp.ui.WasderDataModel;

public class GroupFragment extends TabFragment {
	
	public GroupFragment() {
		
		super("Groups", 2, R.layout.fragment_group_list, MyGroupRecyclerAdapter.class);
	}
	
	/**
	 * Wasder AB CONFIDENTIAL
	 * Created by ahmed on 9/8/2017.
	 */
	
	public static class MyGroupRecyclerAdapter extends RecyclerViewAdapterBase<GroupModel, MyGroupRecyclerAdapter.GroupViewHolder> {
		
		public MyGroupRecyclerAdapter(Context context, final RecyclerView feedRecyclerView, final LinearLayoutManager feedLinearLayoutManager,
		                              OnFragmentInteractionListener mListener) {
			
			super(GroupModel.class, R.layout.fragment_group, GroupViewHolder.class, FirebaseDatabase.getInstance().getReference().child("feed"),
					"MyGroupRecyclerAdapter", context, mListener, feedLinearLayoutManager);
			
		}
		
		@Override
		protected void populateViewHolder(final GroupViewHolder viewHolder, GroupModel model, int position) {
			
			viewHolder.mview.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					mListener.onFragmentInteractionListener(viewHolder.feedModel);
				}
			});
			viewHolder.groupImageView.setImageDrawable(mContext.getDrawable(R.drawable.gamers));
			viewHolder.groupTitleTextView.setText("My Group!");
			viewHolder.groupSubheadTextView.setText("Best Eva!");
			viewHolder.groupDetailsImageButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
				}
			});
		}
		
		public static class GroupViewHolder extends RecyclerView.ViewHolder {
			
			View mview;
			ImageView groupImageView;
			TextView groupTitleTextView;
			TextView groupSubheadTextView;
			ImageButton groupDetailsImageButton;
			GroupModel feedModel;
			
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
	
	/**
	 * Wasder AB CONFIDENTIAL
	 * Created by ahmed on 9/8/2017.
	 */
	
	public static class GroupModel implements WasderDataModel {
		
		private String uId;
		private String id;
		private String title;
		private String subhead;
		private String photoUrl;
		private String imageUrl;
		private String supplementaryText;
		
		public GroupModel() {
			
		}
		
		public GroupModel(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
			
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
