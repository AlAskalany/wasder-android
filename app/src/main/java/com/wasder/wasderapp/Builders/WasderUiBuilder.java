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
	
	interface BuilderBase<T> {
		
		T build();
	}
	
	public static class TabFragmentBuilder implements BuilderBase<TabFragment> {
		
		//(String title, int columnCount, int resLayout, Class<? extends RecyclerViewAdapterBase>
		//recyclerViewAdapterBaseClass)
		private String title;
		private Integer columnCount = 1;
		private Integer resLayout = null;
		private Class<? extends RecyclerViewAdapterBase> recyclerAdapterClass = null;
		
		@Override
		public TabFragment build() {
			
			//			Bundle args = new Bundle();
			//			args.putString(TabFragment.ARG_TITLE, title);
			//			args.putInt(TabFragment.ARG_COLUMN_COUNT, columnCount);
			//			args.putSerializable(TabFragment.ARG_RECYCLER_ADAPTER_CLASS, recyclerAdapterClass);
			//			args.putInt(TabFragment.ARG_LAYOUT, resLayout);
			TabFragment fragment = new TabFragment();
			fragment.setTitle(title);
			fragment.setResLayout(resLayout);
			fragment.setColumnCount(columnCount);
			fragment.setRecyclerViewAdapterBaseClass(recyclerAdapterClass);
			//fragment.setArguments(args);
			return fragment;
		}
		
		public TabFragmentBuilder title(String title) {
			
			this.title = title;
			return this;
		}
		
		public TabFragmentBuilder layout(int layout) {
			
			this.resLayout = layout;
			return this;
		}
		
		public TabFragmentBuilder adapterClass(Class<? extends RecyclerViewAdapterBase> mrecyclerAdapterClass) {
			
			this.recyclerAdapterClass = mrecyclerAdapterClass;
			return this;
		}
		
		public TabFragmentBuilder feedTab() {
			
			return tab("Feed", 1, R.layout.fragment_feed_list, MyFeedRecyclerAdapter.class);
		}
		
		public TabFragmentBuilder tab(String title, int columnCount, int resLayout, Class<? extends RecyclerViewAdapterBase> recyclerAdapterClass) {
			
			this.title = title;
			this.columnCount = columnCount;
			this.resLayout = resLayout;
			this.recyclerAdapterClass = recyclerAdapterClass;
			return this;
		}
		
		public TabFragmentBuilder creatorsFeedTab() {
			
			return tab("Creators Feed", 1, R.layout.fragment_creatorfeed_list, MyCreatorFeedRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder groupsFeedTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_group_list, MyGroupRecyclerAdapter.class);
		}
		
		public TabFragmentBuilder TwitchStreamTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_twitchstream_list, MyTwitchStreamRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder TwitchLiveTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_twitchlive_list, MyTwitchLiveRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder EsportsTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_esports_list, MyEsportsRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder AllEventsTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_event_list, MyEventRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder RecommendedEventsTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_recommendedevent_list, MyRecommendedEventRecyclerViewAdapter.class);
		}
		
		public TabFragmentBuilder FriendsEventsTab() {
			
			return tab("Groups Feed", 2, R.layout.fragment_friendevent_list, MyFriendEventRecyclerViewAdapter.class);
		}
		
		
	}
}
