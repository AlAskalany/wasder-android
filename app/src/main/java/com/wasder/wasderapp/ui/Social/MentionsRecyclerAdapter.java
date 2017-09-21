package com.wasder.wasderapp.ui.Social;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.models.EventItem;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class MentionsRecyclerAdapter
        extends BaseRecyclerAdapter<EventItem, MentionsRecyclerAdapter.EventViewHolder> {

    public MentionsRecyclerAdapter(Context context, LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {

        super(EventItem.class,
                R.layout.mention_item,
                EventViewHolder.class,
		      FirebaseDatabase.getInstance()
		                      .getReference()
		                      .child("feed"),
		      "EventsRecyclerAdapter",
		      context,
		      mListener,
		      feedLinearLayoutManager);
		
	}
	
	@Override
	protected void populateViewHolder(final EventViewHolder viewHolder, final EventItem eventItem, int position) {

        viewHolder.subheadTextView.setText(eventItem.getSubhead());
        viewHolder.supplementaryTextView.setText(eventItem.getSupplementaryText());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View view) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), MentionDetailsActivity.class);
                //intent.putExtra("data_item", feedItem);
                viewHolder.itemView.getContext().startActivity(intent);
                mListener.onFragmentInteractionListener("MentionsFragment", MentionDetailsActivity.class, "Details");
            }
		});
	}
	
	public static class EventViewHolder
			extends RecyclerView.ViewHolder {
		
		View mview;
		TextView titleTextView;
		TextView subheadTextView;
		ImageButton photoImageButton;
		TextView supplementaryTextView;

        public EventViewHolder(View itemView) {
			
			super(itemView);
			mview = itemView;
			subheadTextView = itemView.findViewById(R.id.feed_card_subheader);
			photoImageButton = itemView.findViewById(R.id.feed_card_avatar);
			supplementaryTextView = itemView.findViewById(R.id.feed_card_supplementary_text);
		}
	}
}
