package com.wasder.wasderapp.Builders;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;
import com.wasder.wasderapp.Templates.NavigationFragment;
import com.wasder.wasderapp.Templates.TabFragment;
import com.wasder.wasderapp.ui.Social.tabs.GroupMentionsRecyclerAdapter;
import com.wasder.wasderapp.ui.Social.tabs.MentionsRecyclerAdapter;
import com.wasder.wasderapp.ui.Social.tabs.PMRecyclerAdapter;
import com.wasder.wasderapp.ui.home.tabs.CreatorFeedRecyclerAdapter;
import com.wasder.wasderapp.ui.home.tabs.FeedRecyclerAdapter;
import com.wasder.wasderapp.ui.home.tabs.GroupsRecyclerAdapter;
import com.wasder.wasderapp.ui.live.tabs.EsportsRecyclerAdapter;
import com.wasder.wasderapp.ui.live.tabs.TwitchLiveRecyclerAdapter;
import com.wasder.wasderapp.ui.live.tabs.TwitchStreamRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */
public class UiBuilder {
	
	public enum TabType {
		Feed("FeedFragment", "Feed", R.layout.feed_recycler_view, FeedRecyclerAdapter.class),
		Creators("CreatorsFragment", "Creators", R.layout.creators_feed_recycler_view, CreatorFeedRecyclerAdapter.class),
		Groups("GroupsFragment", "Groups", R.layout.groups_recycler_view, GroupsRecyclerAdapter.class),
		TwitchStream("TwitchStreamFragment", "Twitch Streams", R.layout.twitch_stream_recycler_view, TwitchStreamRecyclerAdapter.class),
		TwitchLive("TwitchLiveFragment", "Twitch Live", R.layout.twitch_live_recycler_view, TwitchLiveRecyclerAdapter.class),
		Esports("EsportsFragment", "Esports", R.layout.esports_recycler_view, EsportsRecyclerAdapter.class),
		Mentions("MentionsFragment", "Mentions", R.layout.mentions_recycler_view, MentionsRecyclerAdapter.class),
		PM("PMFragment", "PM", R.layout.pm_recycler_view,
				PMRecyclerAdapter.class),
		GroupMentions("GroupMentionsFragment", "Group Mentions", R.layout.group_mentions_recycler_view, GroupMentionsRecyclerAdapter.class);
		
		private final String title;
		private final int layout;
		private final Class<? extends BaseRecyclerAdapter> tabAdapter;
		private String tag;
		
		TabType(String tag, String title, int layout, Class<? extends BaseRecyclerAdapter> tabAdapter) {
			
			this.title = title;
			this.layout = layout;
			this.tabAdapter = tabAdapter;
		}
		
		public String getTitle() {
			
			return this.title;
		}
		
		public int getLayout() {
			
			return this.layout;
		}
		
		public Class<? extends BaseRecyclerAdapter> getTabAdapter() {
			
			return this.tabAdapter;
		}
		
		public String getTag() {
			
			return tag;
		}
	}
	
	/**
	 * The enum Num columns.
	 */
	private enum NumColumns {
		/**
		 * One num columns.
		 */
		ONE(1),
		/**
		 * Two num columns.
		 */
		TWO(2);
		private final int value;
		
		NumColumns(int value) {
			
			this.value = value;
		}
		
		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public int getValue() {
			
			return value;
		}
	}
	
	public enum NavigationFragmentType {
		HOME,
		LIVE,
		SOCIAL
	}
	
	/**
	 * The interface Builder base.
	 *
	 * @param <T> the type parameter
	 */
	interface BuilderBase<T> {
		
		/**
		 * Build t.
		 *
		 * @return the t
		 */
		T build();
	}
	
	public static class NavFragment
			implements BuilderBase<NavigationFragment> {
		
		List<TabFragment> tabs = new ArrayList<>();
		private String tag;
		private String title;
		private int layout;
		private int toolbar;
		private int drawerLayout;
		private int navigationView;
		private int viewPager;
		private int tabLayout;
		
		/**
		 * Build t.
		 *
		 * @return the t
		 */
		@Override
		public NavigationFragment build() {
			
			NavigationFragment navFragment = new NavigationFragment();
			
			navFragment.setmTAG(tag);
			navFragment.setmFragmentTitle(title);
			navFragment.setmResLayout(layout);
			navFragment.setmResToolbar(toolbar);
			navFragment.setmResDrawerLayout(drawerLayout);
			navFragment.setmResNavigationView(navigationView);
			navFragment.setmResViewPager(viewPager);
			navFragment.setmResTabLayout(tabLayout);
			navFragment.setmTabFragments(tabs);
			return navFragment;
		}
		
		public NavFragment addTab(TabType tabType) {
			
			tabs.add(new UiBuilder.TabFragmentBuilder().createTab(tabType)
					.build());
			return this;
		}
		
		public NavFragment Create() {
			
			return this;
		}
		
		public NavFragment setTag(String tag) {
			
			this.tag = tag;
			return this;
		}
		
		public NavFragment setTitle(String title) {
			
			this.title = title;
			return this;
		}
		
		public NavFragment setLayout(int layout) {
			
			this.layout = layout;
			return this;
		}
		
		public NavFragment setToolbar(int toolbar) {
			
			this.toolbar = toolbar;
			return this;
		}
		
		public NavFragment setDrawerLayout(int drawerLayout) {
			
			this.drawerLayout = drawerLayout;
			return this;
		}
		
		public NavFragment setNavigationView(int navigationView) {
			
			this.navigationView = navigationView;
			return this;
		}
		
		public NavFragment setViewPager(int viewPager) {
			
			this.viewPager = viewPager;
			return this;
		}
		
		public NavFragment setTabLayout(int tabLayout) {
			
			this.tabLayout = tabLayout;
			return this;
		}
	}
	
	/**
	 * The type Tab fragment builder.
	 */
	public static class TabFragmentBuilder
			implements BuilderBase<TabFragment> {
		
		//(String setTitle, int columnCount, int setLayout, Class<? extends BaseRecyclerAdapter>
		//recyclerViewAdapterBaseClass)
		private String tag;
		private String title;
		private Integer columnCount = 1;
		private Integer layout = null;
		private Class<? extends BaseRecyclerAdapter> recyclerAdapterClass = null;
		
		/**
		 * Title tab fragment builder.
		 *
		 * @param title the setTitle
		 * @return the tab fragment builder
		 */
		public TabFragmentBuilder setTitle(String title) {
			
			this.title = title;
			return this;
		}
		
		/**
		 * Layout tab fragment builder.
		 *
		 * @param layout the setLayout
		 * @return the tab fragment builder
		 */
		public TabFragmentBuilder setLayout(int layout) {
			
			this.layout = layout;
			return this;
		}
		
		public TabFragmentBuilder createTab(TabType tabType) {
			
			return tab(tabType.getTag(), tabType.getTitle(), NumColumns.ONE, tabType.getLayout(), tabType.getTabAdapter());
		}
		
		/**
		 * Tab tab fragment builder.
		 *
		 * @param title      the setTitle
		 * @param numColumns the num columns
		 * @param resLayout  the res setLayout
		 * @param tabAdapter the tab adapter
		 * @return the tab fragment builder
		 */
		TabFragmentBuilder tab(String tag, String title, NumColumns numColumns, int resLayout, Class<? extends BaseRecyclerAdapter> tabAdapter) {
			
			this.tag = tag;
			this.title = title;
			this.columnCount = numColumns.getValue();
			this.layout = resLayout;
			this.recyclerAdapterClass = tabAdapter;
			return this;
		}
		
		@Override
		public TabFragment build() {
			
			TabFragment fragment = new TabFragment();
			fragment.setTag(tag);
			fragment.setTitle(title);
			fragment.setResLayout(layout);
			fragment.setColumnCount(columnCount);
			fragment.setRecyclerViewAdapterBaseClass(recyclerAdapterClass);
			//fragment.setArguments(args);
			return fragment;
		}
	}
}
