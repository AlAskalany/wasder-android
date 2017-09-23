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

public class ListDialog extends Dialog {

	public ListDialog(@NonNull Context context) {
		super(context);
		setContentView(R.layout.dialog);
		Button dialogConfirmButton = findViewById(R.id.dialog_confirm_button);
		dialogConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				hide();
			}
		});
		Button dialogCancelButton = findViewById(R.id.dialog_cancel_button);
		dialogCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				hide();
			}
		});
	}

	public static ListDialog newInstance(Context context) {
		return new ListDialog(context);
	}

	public void ShowListDialog(Context context) {
		this.show();
	}
}
