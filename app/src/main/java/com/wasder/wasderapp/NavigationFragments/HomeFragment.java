package com.wasder.wasderapp.NavigationFragments;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;

import java.util.Arrays;

import static com.wasder.wasderapp.Builders.WasderUiBuilder.TabFragmentBuilder;
import static com.wasder.wasderapp.Builders.WasderUiBuilder.TabType;

public class HomeFragment
		extends NavigationFragment {
	
	public HomeFragment() {
		// Required empty public constructor
		super("HomeFragment",
		      "Home",
		      R.layout.fragment_home,
		      R.id.home_toolbar,
		      R.id.drawer_layout,
		      R.id.nav_view,
		      R.id.home_viewpager,
		      R.id.home_tablayout,
		      Arrays.asList(new TabFragmentBuilder().addTab(TabType.Feed)
		                                            .build(),
		                    new TabFragmentBuilder().addTab(TabType.Creators)
		                                            .build(),
		                    new TabFragmentBuilder().addTab(TabType.Groups)
		                                            .build()));
	}
}
