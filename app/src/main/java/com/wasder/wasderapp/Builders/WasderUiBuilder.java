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
import com.wasder.wasderapp.Templates.RecyclerViewAdapterBase;
import com.wasder.wasderapp.Templates.TabFragment;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */
public class WasderUiBuilder {
	
	public enum TabType {
		Feed,
		Creators,
		Groups,
		TwitchStream,
		TwitchLive,
		Esports,
		AllEvents,
		RecommendedEvents,
		FriendsEvents
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
	
	/**
	 * The enum Tab adapter.
	 */
	private enum TabAdapter {
		/**
		 * Feed tab adapter.
		 */
		Feed(MyFeedRecyclerAdapter.class),
		/**
		 * Creators tab adapter.
		 */
		Creators(MyCreatorFeedRecyclerViewAdapter.class),
		/**
		 * Groups tab adapter.
		 */
		Groups(MyGroupRecyclerAdapter.class),
		/**
		 * Twitch stream tab adapter.
		 */
		TwitchStream(MyTwitchStreamRecyclerViewAdapter.class),
		/**
		 * Twitch live tab adapter.
		 */
		TwitchLive(MyTwitchLiveRecyclerViewAdapter.class),
		/**
		 * Esports tab adapter.
		 */
		Esports(MyEsportsRecyclerViewAdapter.class),
		/**
		 * All events tab adapter.
		 */
		AllEvents(MyEventRecyclerViewAdapter.class),
		/**
		 * Recommended events tab adapter.
		 */
		RecommendedEvents(MyRecommendedEventRecyclerViewAdapter.class),
		/**
		 * Friends events tab adapter.
		 */
		FriendsEvents(MyFriendEventRecyclerViewAdapter.class);
		
		private final Class<? extends RecyclerViewAdapterBase> value;
		
		TabAdapter(Class<? extends RecyclerViewAdapterBase> value) {
			
			this.value = value;
		}
		
		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public Class<? extends RecyclerViewAdapterBase> getValue() {
			
			return value;
		}
		
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
		
		/*public TabFragmentBuilder adapterClass(Class<? extends RecyclerViewAdapterBase> mrecyclerAdapterClass) {
			
			this.recyclerAdapterClass = mrecyclerAdapterClass;
			return this;
		}*/
		
		public TabFragmentBuilder addTab(TabType tabType) {
			
			switch (tabType) {
				case Feed:
					return tab("Feed",
					           NumColumns.ONE,
					           R.layout.fragment_feed_list,
					           TabAdapter.Feed);
				
				case Creators:
					return tab("Creators Feed",
					           NumColumns.ONE,
					           R.layout.fragment_creatorfeed_list,
					           TabAdapter.Creators);
				case Groups:
					return tab("Groups Feed",
					           NumColumns.TWO,
					           R.layout.fragment_group_list,
					           TabAdapter.Groups);
				case TwitchStream:
					return tab("Twitch Stream",
					           NumColumns.ONE,
					           R.layout.fragment_twitchstream_list,
					           TabAdapter.TwitchStream);
				case TwitchLive:
					return tab("Twitch Live",
					           NumColumns.ONE,
					           R.layout.fragment_twitchlive_list,
					           TabAdapter.TwitchLive);
				case Esports:
					return tab("Esports",
					           NumColumns.ONE,
					           R.layout.fragment_esports_list,
					           TabAdapter.Esports);
				case AllEvents:
					return tab("All Events",
					           NumColumns.ONE,
					           R.layout.fragment_event_list,
					           TabAdapter.AllEvents);
				case RecommendedEvents:
					return tab("Recommended Events",
					           NumColumns.ONE,
					           R.layout.fragment_recommendedevent_list,
					           TabAdapter.RecommendedEvents);
				case FriendsEvents:
					return tab("Friends Events",
					           NumColumns.ONE,
					           R.layout.fragment_friendevent_list,
					           TabAdapter.FriendsEvents);
			}
			return null;
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
		TabFragmentBuilder tab(String title,
		                       NumColumns numColumns,
		                       int resLayout,
		                       TabAdapter tabAdapter) {
			
			this.title = title;
			this.columnCount = numColumns.getValue();
			this.resLayout = resLayout;
			this.recyclerAdapterClass = tabAdapter.getValue();
			return this;
		}
	}
}
