package com.wasder.wasderapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FollowersFragment extends android.support.v4.app.Fragment {

    public FollowersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }
}
