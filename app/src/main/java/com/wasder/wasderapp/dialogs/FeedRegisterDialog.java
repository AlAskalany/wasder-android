package com.wasder.wasderapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.wasder.wasderapp.R;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/4/2017.
 */

public class FeedRegisterDialog extends Dialog {

	public FeedRegisterDialog(@NonNull Context context) {
		super(context);
		setContentView(R.layout.dialog_feed_register);
		Button dialogConfirmButton = findViewById(R.id.dialog_feed_register_confirm_button);
		dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				hide();
			}
		});
		Button dialogCancelButton = findViewById(R.id.dialog_feed_register_cancel_button);
		dialogCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				hide();
			}
		});
	}

	public static FeedRegisterDialog newInstance(Context context) {
		return new FeedRegisterDialog(context);
	}

	public void ShowListFeedRegisterDialog(Context context) {
		this.show();
	}
}
