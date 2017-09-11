package com.wasder.wasderapp.NavigationFragments;

import com.wasder.wasderapp.Builders.WasderUiBuilder;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.Templates.NavigationFragment;

import java.util.Arrays;

public class LiveFragment extends NavigationFragment {
	
	public LiveFragment() {
		
		super("LiveFragment", "Live", R.layout.fragment_live, R.id.live_toolbar, R.id.drawer_layout, R.id.nav_view, R.id.live_viewpager, R.id.live_tablayout, Arrays.asList(new WasderUiBuilder.TabFragmentBuilder().TwitchStreamTab().build(), new WasderUiBuilder.TabFragmentBuilder().TwitchLiveTab().build(), new WasderUiBuilder.TabFragmentBuilder().EsportsTab().build()));
	}
}
