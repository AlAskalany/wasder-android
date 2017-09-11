package com.wasder.wasderapp.NavigationFragments;

import com.wasder.wasderapp.Builders.WasderUiBuilder;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;

import java.util.Arrays;

public class HomeFragment extends NavigationFragment {
	
	public HomeFragment() {
		// Required empty public constructor
		super("HomeFragment", "Home", R.layout.fragment_home, R.id.home_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.home_viewpager, R.id
				.home_tablayout, Arrays.asList(new WasderUiBuilder.TabFragmentBuilder().feedTab().build(), new WasderUiBuilder.TabFragmentBuilder()
				.creatorsFeedTab().build(), new WasderUiBuilder.TabFragmentBuilder().groupsFeedTab().build()));
	}
}
