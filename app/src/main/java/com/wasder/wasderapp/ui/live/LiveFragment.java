package com.wasder.wasderapp.ui.live;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.NavigationFragment;
import com.wasder.wasderapp.ui.home.CreatorFeedFragment;
import com.wasder.wasderapp.ui.home.FeedFragment;
import com.wasder.wasderapp.ui.home.GroupFragment;

import java.util.Arrays;

public class LiveFragment extends NavigationFragment {
	
	public LiveFragment() {
		
		super("LiveFragment", "Live", R.layout.fragment_live, R.id.live_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.live_viewpager, R.id
				.live_tablayout, Arrays.asList(new FeedFragment(), new CreatorFeedFragment(), new GroupFragment()));
	}
}
