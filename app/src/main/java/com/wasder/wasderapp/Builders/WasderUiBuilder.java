package com.wasder.wasderapp.Builders;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.RecyclerAdapters.MyCreatorFeedRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyEsportsRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyEventRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyFeedRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyFriendEventRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyGroupRecyclerAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyRecommendedEventRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyTwitchLiveRecyclerViewAdapter;
import com.wasder.wasderapp.RecyclerAdapters.MyTwitchStreamRecyclerViewAdapter;
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
		Feed("Feed", R.layout.fragment_feed_list, MyFeedRecyclerAdapter.class),
		Creators("Creators", R.layout.fragment_feed_list, MyCreatorFeedRecyclerViewAdapter.class),
		Groups("Groups", R.layout.fragment_feed_list, MyGroupRecyclerAdapter.class),
		TwitchStream("Twitch Streams", R.layout.fragment_feed_list, MyTwitchStreamRecyclerViewAdapter.class),
		TwitchLive("Twitch Live", R.layout.fragment_feed_list, MyTwitchLiveRecyclerViewAdapter.class),
		Esports("Esports", R.layout.fragment_feed_list, MyEsportsRecyclerViewAdapter.class),
		AllEvents("All Events", R.layout.fragment_feed_list, MyEventRecyclerViewAdapter.class),
		RecommendedEvents("Recommended Events", R.layout.fragment_feed_list, MyRecommendedEventRecyclerViewAdapter.class),
		FriendsEvents("Friends Events", R.layout.fragment_feed_list, MyFriendEventRecyclerViewAdapter.class);
		
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
