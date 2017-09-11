package com.wasder.wasderapp.NavigationFragments;

import com.wasder.wasderapp.Builders.WasderUiBuilder;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;

import java.util.Arrays;

public class MarketFragment extends NavigationFragment {
	
	public MarketFragment() {
		
		super("MarketFragment", "Market", R.layout.fragment_market, R.id.market_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.market_viewpager, R.id.market_tablayout, Arrays.asList(new WasderUiBuilder.TabFragmentBuilder().AllEventsTab().build(), new WasderUiBuilder.TabFragmentBuilder().RecommendedEventsTab().build(), new WasderUiBuilder.TabFragmentBuilder().FriendsEventsTab().build()));
	}
}
