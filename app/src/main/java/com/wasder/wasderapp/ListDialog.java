package com.wasder.wasderapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/4/2017.
 */

public class ListDialog extends Dialog {
	
	public ListDialog(@NonNull Context context) {
		super(context);
		setContentView(R.layout.dialog);
	}
	
	public static ListDialog newInstance(Context context){
		return new ListDialog(context);
	}
	
	public void ShowListDialog(Context context){
		this.show();
	}
}
