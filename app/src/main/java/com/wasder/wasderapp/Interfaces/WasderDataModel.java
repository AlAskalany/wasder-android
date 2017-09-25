package com.wasder.wasderapp.Interfaces;

import java.io.Serializable;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class WasderDataModel
		implements Serializable {
	
	private String id;
	private String title;
	private String photoUrl;
	private String imageUrl;
	private String supplementaryText;
	private String uId;
	private String subhead;
	
	@SuppressWarnings("WeakerAccess")
	public WasderDataModel() {
		
	}
	
	public WasderDataModel(String uId, String id, String title, String subhead, String photoUrl, String imageUrl, String supplementaryText) {
		
		this.id = id;
		this.title = title;
		this.supplementaryText = supplementaryText;
		this.photoUrl = photoUrl;
		this.imageUrl = imageUrl;
		this.uId = uId;
		this.subhead = subhead;
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
	
	public String getSupplementaryText() {
		
		return supplementaryText;
	}
	
	public void setSupplementaryText(String supplementaryText) {
		
		this.supplementaryText = supplementaryText;
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
	
	public String getSubhead() {
		
		return subhead;
	}
	
	public void setSubhead(String subhead) {
		
		this.subhead = subhead;
	}
	
	public String getuId() {
		
		return uId;
	}
	
	public void setuId(String uId) {
		
		this.uId = uId;
	}
}
