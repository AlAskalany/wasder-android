package com.wasder.wasderapp.ui.home.tabs;

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
import com.wasder.wasderapp.models.GroupItem;
import com.wasder.wasderapp.ui.home.GroupDetailsActivity;
import com.wasder.wasderapp.util.Helpers;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class GroupsRecyclerAdapter
        extends BaseRecyclerAdapter<GroupItem, GroupsRecyclerAdapter.GroupViewHolder> {

    public GroupsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {

        super(GroupItem.class, R.layout.group_item, GroupViewHolder.class, FirebaseDatabase.getInstance().getReference().child("groups"),
                "GroupsRecyclerAdapter", context, mListener, feedLinearLayoutManager);

    }

    @Override
    protected void populateViewHolder(final GroupViewHolder viewHolder, final GroupItem groupItem, int position) {

        viewHolder.groupDetailsImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(viewHolder.itemView.getContext(), GroupDetailsActivity.class);
                intent.putExtra("group_item", groupItem);
                viewHolder.itemView.getContext()
                        .startActivity(intent);
                mListener.onFragmentInteractionListener(Helpers.TAG.GroupsFragment, viewHolder.feedModel, "Item");
            }
        });
        final String imageUrl = groupItem.getImageUrl();
        Helpers.Firebase.DownloadUrlImage(imageUrl, viewHolder.groupImageView, false, 0);
        viewHolder.groupTitleTextView.setText(groupItem.getTitle());
        viewHolder.groupSubheadTextView.setText(groupItem.getSupplementaryText());
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
            groupDetailsImageButton = itemView.findViewById(R.id.groups_details_imageButton);
        }
    }
}
