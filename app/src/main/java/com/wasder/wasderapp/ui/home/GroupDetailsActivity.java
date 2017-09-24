package com.wasder.wasderapp.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.wasder.wasderapp.BaseDetailsActivity;
import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.GroupItem;
import com.wasder.wasderapp.util.Helpers;

public class GroupDetailsActivity
        extends BaseDetailsActivity {

    public static final String ARG_GROUP_ITEM = "group_item";
    private GroupItem groupItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Toolbar toolbar = findViewById(R.id.toolbar_group_activity);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageView imageView = findViewById(R.id.app_bar_image);
        TextView textView = findViewById(R.id.activity_feed_textView);
        if (getIntent().getExtras()
                .containsKey(ARG_GROUP_ITEM)) {
            groupItem = (GroupItem) getIntent().getExtras()
                    .getSerializable(ARG_GROUP_ITEM);
            getSupportActionBar().setTitle(groupItem.getTitle());
            final String imageUrl = groupItem.getImageUrl();
            Helpers.Firebase.DownloadUrlImage(imageUrl, imageView, false, 0);
        }
    }
}
