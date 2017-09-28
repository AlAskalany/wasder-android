package com.wasder.wasderapp.models;

import com.wasder.wasderapp.Interfaces.WasderDataModel;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class FeedItem
		extends WasderDataModel {
	
	public FeedItem() {
		
	}
	
	public FeedItem(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
		
		super(uId, id, title, subhead, photoUrl, imageUrl, supplementaryText);
	}
}
