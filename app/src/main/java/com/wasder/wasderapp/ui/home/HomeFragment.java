package com.wasder.wasderapp.ui.home;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.ui.NavigationFragment;
import com.wasder.wasderapp.ui.WasderUiBuilder;

import java.util.Arrays;

public class HomeFragment extends NavigationFragment {
	
	public HomeFragment() {
		// Required empty public constructor
		super("HomeFragment", "Home", R.layout.fragment_home, R.id.home_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.home_viewpager, R.id
				.home_tablayout, Arrays.asList(new WasderUiBuilder.TabFragmentBuilder().feedTab().build(), new WasderUiBuilder.TabFragmentBuilder()
				.creatorsFeedTab().build(), new WasderUiBuilder.TabFragmentBuilder().groupsFeedTab().build()));
	}
}
