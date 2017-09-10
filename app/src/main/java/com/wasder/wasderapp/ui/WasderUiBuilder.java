package com.wasder.wasderapp.ui;

import android.os.Bundle;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.home.MyCreatorFeedRecyclerViewAdapter;
import com.wasder.wasderapp.ui.home.MyFeedRecyclerAdapter;
import com.wasder.wasderapp.ui.home.MyGroupRecyclerAdapter;

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
		
		
	}
}
