package com.wasder.wasderapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.dummy.DummyContent;

import java.util.List;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of MarketItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MarketItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MarketItemListActivity
		extends AppCompatActivity {
	
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marketitem_list);
		Toolbar mToolbar = findViewById(R.id.toolbar_market_item_list);
		setSupportActionBar(mToolbar);
		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		View recyclerView = findViewById(R.id.marketitem_list);
		assert recyclerView != null;
		setupRecyclerView((RecyclerView) recyclerView);
		
		if (findViewById(R.id.marketitem_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;
		}
	}
	
	private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
		
		int mColumnCount = 3;
		//noinspection ConstantConditions
		if (mColumnCount <= 1) {
			recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
		} else {
			recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), mColumnCount));
		}
		recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == android.R.id.home) {
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class SimpleItemRecyclerViewAdapter
			extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
		
		private final List<DummyContent.DummyItem> mValues;
		
		public SimpleItemRecyclerViewAdapter() {
			
			mValues = DummyContent.ITEMS;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.marketitem_list_content, parent, false);
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			
			holder.mItem = mValues.get(position);
			holder.mIdView.setText(mValues.get(position).id);
			holder.mContentView.setText(mValues.get(position).content);
			
			holder.mView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if (mTwoPane) {
						Bundle arguments = new Bundle();
						arguments.putString(MarketItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
						MarketItemDetailFragment fragment = new MarketItemDetailFragment();
						fragment.setArguments(arguments);
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.marketitem_detail_container, fragment)
								.commit();
					} else {
						Context context = v.getContext();
						Intent intent = new Intent(context, MarketItemDetailActivity.class);
						intent.putExtra(MarketItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
						
						context.startActivity(intent);
					}
				}
			});
		}
		
		@Override
		public int getItemCount() {
			
			return mValues.size();
		}
		
		public class ViewHolder
				extends RecyclerView.ViewHolder {
			
			public final View mView;
			public final TextView mIdView;
			public final TextView mContentView;
			public DummyContent.DummyItem mItem;
			
			public ViewHolder(View view) {
				
				super(view);
				mView = view;
				mIdView = view.findViewById(R.id.id);
				mContentView = view.findViewById(R.id.content);
			}
			
			@Override
			public String toString() {
				
				return super.toString() + " '" + mContentView.getText() + "'";
			}
		}
	}
}
