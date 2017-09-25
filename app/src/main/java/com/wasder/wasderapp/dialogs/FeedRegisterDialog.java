package com.wasder.wasderapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.FeedItem;
import com.wasder.wasderapp.ui.EventActivity;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/4/2017.
 */

public class FeedRegisterDialog extends Dialog {
	
	public FeedRegisterDialog(@NonNull Context context, final FeedItem feedItem) {
		
		super(context);
		setContentView(R.layout.dialog_feed_register);
		this.setTitle("Register For Event");
		findViewById(R.id.dialog_feed_register_confirm_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				hide();
				Intent intent = new Intent(getContext(), EventActivity.class);
				intent.putExtra("event", feedItem);
				view.getContext().startActivity(intent);
			}
		});
		findViewById(R.id.dialog_feed_register_cancel_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				hide();
			}
		});
	}
	
	public static FeedRegisterDialog newInstance(Context context, FeedItem feedItem) {
		
		return new FeedRegisterDialog(context, feedItem);
	}
}
