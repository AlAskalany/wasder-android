package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplitude.api.Amplitude;
import com.wasder.wasderapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FollowersFragment extends android.support.v4.app.Fragment {
	
	public FollowersFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View inflate = inflater.inflate(R.layout.fragment_followers, container, false);
		Amplitude.getInstance().initialize(getActivity(), "937ae55b73eb164890021fe9b2d4fa63").enableForegroundTracking(getActivity().getApplication());
		Amplitude.getInstance().logEvent("Started_Followers_Fragment");
		return inflate;
	}
}
