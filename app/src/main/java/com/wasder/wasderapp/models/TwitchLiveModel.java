package com.wasder.wasderapp.models;

import com.wasder.wasderapp.Interfaces.WasderDataModel;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/8/2017.
 */

public class TwitchLiveModel implements WasderDataModel {
	
	private String uId;
	private String id;
	private String title;
	private String subhead;
	private String photoUrl;
	private String imageUrl;
	private String supplementaryText;
	
	public TwitchLiveModel() {
		
	}
	
	public TwitchLiveModel(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
		
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
