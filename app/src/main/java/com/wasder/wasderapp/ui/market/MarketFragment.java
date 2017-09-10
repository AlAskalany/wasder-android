package com.wasder.wasderapp.ui.market;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.NavigationFragment;

public class MarketFragment extends NavigationFragment {
	
	public MarketFragment() {
		
		super("MarketFragment", "Market", R.layout.fragment_market, R.id.market_toolbar, R.id
				.drawer_layout, R.id.nav_view, R.id.market_viewpager, R.id.market_tablayout, new
				EventFragment(), new RecommendedEventFragment(), new FriendEventFragment());
	}
}
