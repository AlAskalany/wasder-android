package com.wasder.wasderapp.ui.home.models;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CreatorFeedContent {
	
	/**
	 * An array of sample (CreatorFeed) items.
	 */
	public static final List<CreatorFeedItem> ITEMS = new ArrayList<CreatorFeedItem>();
	/**
	 * A map of sample (CreatorFeed) items, by ID.
	 */
	public static final Map<String, CreatorFeedItem> ITEM_MAP = new HashMap<String, CreatorFeedItem>();
	/**
	 * A map of sample (CreatorFeed) items, images
	 */
	public static final Map<Drawable, CreatorFeedItem> ITEM_IMAGE = new HashMap<Drawable, CreatorFeedItem>();
	private static final int COUNT = 25;
	
	static {
		// Add some sample items.
		for (int i = 1; i <= COUNT; i++) {
			addItem(createCreatorFeedItem(i));
		}
	}
	
	private static void addItem(CreatorFeedItem item) {
		
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
		ITEM_IMAGE.put(item.image, item);
	}
	
	private static CreatorFeedItem createCreatorFeedItem(int position) {
		
		return new CreatorFeedItem(String.valueOf(position), "Item " + position, makeDetails(position), makeImage(position));
	}
	
	private static String makeDetails(int position) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Details about Item: ").append(position);
		for (int i = 0; i < position; i++) {
			builder.append("\nMore details information here.");
		}
		return builder.toString();
	}
	
	private static Drawable makeImage(int position) {
		
		return null;
	}
	
	/**
	 * A CreatorFeed item representing a piece of content.
	 */
	public static class CreatorFeedItem {
		
		public final String id;
		public final String content;
		public final String details;
		public final Drawable image;
		
		public CreatorFeedItem(String id, String content, String details, Drawable image) {
			
			this.id = id;
			this.content = content;
			this.details = details;
			this.image = image;
		}
		
		@Override
		public String toString() {
			
			return content;
		}
	}
}
