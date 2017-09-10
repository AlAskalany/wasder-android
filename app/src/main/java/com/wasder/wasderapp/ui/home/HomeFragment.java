package com.wasder.wasderapp.ui.home;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.NavigationFragment;

public class HomeFragment extends NavigationFragment {
	
	public HomeFragment() {
		// Required empty public constructor
		super("HomeFragment", "Home", R.layout.fragment_home, R.id.home_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.home_viewpager, R.id
				.home_tablayout, new FeedFragment(), new CreatorFeedFragment(), new GroupFragment());
	}
}
