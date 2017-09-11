package com.wasder.wasderapp.Builders;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.RecyclerAdapters.CreatorFeedRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.EsportsRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.EventRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.FeedRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.FriendsEventsRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.GroupsRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.RecommendedEventsRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.TwitchLiveRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.TwitchStreamRecyclerAdapter;
import com.wasder.wasderapp.Templates.NavigationFragment;
import com.wasder.wasderapp.Templates.RecyclerViewAdapterBase;
import com.wasder.wasderapp.Templates.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */
public class WasderUiBuilder {
	
	public enum TabType {
		Feed("Feed", R.layout.fragment_feed_list, FeedRecyclerAdapter.class),
		Creators("Creators", R.layout.fragment_creatorfeed_list, CreatorFeedRecyclerAdapter.class),
		Groups("Groups", R.layout.fragment_group_list, GroupsRecyclerAdapter.class),
		TwitchStream("Twitch Streams", R.layout.fragment_twitchstream_list, TwitchStreamRecyclerAdapter.class),
		TwitchLive("Twitch Live", R.layout.fragment_twitchlive_list, TwitchLiveRecyclerAdapter.class),
		Esports("Esports", R.layout.fragment_esports_list, EsportsRecyclerAdapter.class),
		AllEvents("All Events", R.layout.fragment_event_list, EventRecyclerAdapter.class),
		RecommendedEvents("Recommended Events", R.layout.fragment_recommendedevent_list, RecommendedEventsRecyclerAdapter.class),
		FriendsEvents("Friends Events", R.layout.fragment_friendevent_list, FriendsEventsRecyclerAdapter.class);
		
		private final String title;
		private final int layout;
		private final Class<? extends RecyclerViewAdapterBase> tabAdapter;
		
		TabType(String title, int layout, Class<? extends RecyclerViewAdapterBase> tabAdapter) {
			
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
		
		public Class<? extends RecyclerViewAdapterBase> getTabAdapter() {
			
			return this.tabAdapter;
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
		MARKET
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
	
	public static class NavigationFragmentBuilder
			implements BuilderBase<NavigationFragment> {
		
		List<TabFragment> mTabFragments = new ArrayList<>();
		private String mTAG;
		private String mFragmentTitle;
		private int mResLayout;
		private int mResToolbar;
		private int mResDrawerLayout;
		private int mResNavigationView;
		private int mResViewPager;
		private int mResTabLayout;
		
		/**
		 * Build t.
		 *
		 * @return the t
		 */
		@Override
		public NavigationFragment build() {
			
			NavigationFragment navigationFragment = new NavigationFragment();
			
			navigationFragment.setmTAG(mTAG);
			navigationFragment.setmFragmentTitle(mFragmentTitle);
			navigationFragment.setmResLayout(mResLayout);
			navigationFragment.setmResToolbar(mResToolbar);
			navigationFragment.setmResDrawerLayout(mResDrawerLayout);
			navigationFragment.setmResNavigationView(mResNavigationView);
			navigationFragment.setmResViewPager(mResViewPager);
			navigationFragment.setmResTabLayout(mResTabLayout);
			navigationFragment.setmTabFragments(mTabFragments);
			return navigationFragment;
		}
		
		public NavigationFragmentBuilder addTab(TabType tabType) {
			
			mTabFragments.add(new WasderUiBuilder.TabFragmentBuilder().addTab(tabType)
			                                                          .build());
			return this;
		}
		
		public NavigationFragmentBuilder Create() {
			
			return this;
		}
		
		public NavigationFragmentBuilder setmTAG(String mTAG) {
			
			this.mTAG = mTAG;
			return this;
		}
		
		public NavigationFragmentBuilder setmFragmentTitle(String mFragmentTitle) {
			
			this.mFragmentTitle = mFragmentTitle;
			return this;
		}
		
		public NavigationFragmentBuilder setmResLayout(int mResLayout) {
			
			this.mResLayout = mResLayout;
			return this;
		}
		
		public NavigationFragmentBuilder setmResToolbar(int mResToolbar) {
			
			this.mResToolbar = mResToolbar;
			return this;
		}
		
		public NavigationFragmentBuilder setmResDrawerLayout(int mResDrawerLayout) {
			
			this.mResDrawerLayout = mResDrawerLayout;
			return this;
		}
		
		public NavigationFragmentBuilder setmResNavigationView(int mResNavigationView) {
			
			this.mResNavigationView = mResNavigationView;
			return this;
		}
		
		public NavigationFragmentBuilder setmResViewPager(int mResViewPager) {
			
			this.mResViewPager = mResViewPager;
			return this;
		}
		
		public NavigationFragmentBuilder setmResTabLayout(int mResTabLayout) {
			
			this.mResTabLayout = mResTabLayout;
			return this;
		}
	}
	
	/**
	 * The type Tab fragment builder.
	 */
	public static class TabFragmentBuilder
			implements BuilderBase<TabFragment> {
		
		//(String title, int columnCount, int resLayout, Class<? extends RecyclerViewAdapterBase>
		//recyclerViewAdapterBaseClass)
		private String title;
		private Integer columnCount = 1;
		private Integer resLayout = null;
		private Class<? extends RecyclerViewAdapterBase> recyclerAdapterClass = null;
		
		/**
		 * Title tab fragment builder.
		 *
		 * @param title the title
		 * @return the tab fragment builder
		 */
		public TabFragmentBuilder title(String title) {
			
			this.title = title;
			return this;
		}
		
		/**
		 * Layout tab fragment builder.
		 *
		 * @param layout the layout
		 * @return the tab fragment builder
		 */
		public TabFragmentBuilder layout(int layout) {
			
			this.resLayout = layout;
			return this;
		}
		
		public TabFragmentBuilder addTab(TabType tabType) {
			
			return tab(tabType.getTitle(), NumColumns.ONE, tabType.getLayout(), tabType.getTabAdapter());
		}
		
		/**
		 * Tab tab fragment builder.
		 *
		 * @param title      the title
		 * @param numColumns the num columns
		 * @param resLayout  the res layout
		 * @param tabAdapter the tab adapter
		 * @return the tab fragment builder
		 */
		TabFragmentBuilder tab(String title, NumColumns numColumns, int resLayout, Class<? extends RecyclerViewAdapterBase> tabAdapter) {
			
			this.title = title;
			this.columnCount = numColumns.getValue();
			this.resLayout = resLayout;
			this.recyclerAdapterClass = tabAdapter;
			return this;
		}
		
		@Override
		public TabFragment build() {
			
			TabFragment fragment = new TabFragment();
			fragment.setTitle(title);
			fragment.setResLayout(resLayout);
			fragment.setColumnCount(columnCount);
			fragment.setRecyclerViewAdapterBaseClass(recyclerAdapterClass);
			//fragment.setArguments(args);
			return fragment;
		}
	}
}
