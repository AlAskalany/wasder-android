package com.wasder.wasderapp.NavigationFragments;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;

import java.util.Arrays;

import static com.wasder.wasderapp.Builders.WasderUiBuilder.TabFragmentBuilder;
import static com.wasder.wasderapp.Builders.WasderUiBuilder.TabType;

public class MarketFragment
		extends NavigationFragment {
	
	public MarketFragment() {
		
		super("MarketFragment",
		      "Market",
		      R.layout.fragment_market,
		      R.id.market_toolbar,
		      R.id.drawer_layout,
		      R.id.nav_view,
		      R.id.market_viewpager,
		      R.id.market_tablayout,
		      Arrays.asList(new TabFragmentBuilder().addTab(TabType.AllEvents)
		                                            .build(),
		                    new TabFragmentBuilder().addTab(TabType.RecommendedEvents)
		                                            .build(),
		                    new TabFragmentBuilder().addTab(TabType.FriendsEvents)
		                                            .build()));
	}
}
