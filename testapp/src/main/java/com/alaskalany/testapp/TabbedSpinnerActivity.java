package com.alaskalany.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TabbedSpinnerActivity
		extends AppCompatActivity {
	
	Map<Integer, PlaceholderFragment> fragmentMap = new HashMap<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabbed_spinner);
		
		fragmentMap.put(0, PlaceholderFragment.newInstance(0));
		fragmentMap.put(1, PlaceholderFragment.newInstance(1));
		fragmentMap.put(2, PlaceholderFragment.newInstance(2));
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		// Setup spinner
		Spinner spinner = findViewById(R.id.spinner);
		spinner.setAdapter(new MyAdapter(toolbar.getContext(),
		                                 new String[]{"Section 1",
		                                              "Section 2",
		                                              "Section 3",}));
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// When the given dropdown item is selected, show its contents in the
				// container view.
				getSupportFragmentManager().beginTransaction()
				                           .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
				                           .commit();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				        .setAction("Action", null)
				        .show();
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_tabbed_spinner, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, NavigationDrawerActivity.class));
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private static class MyAdapter
			extends ArrayAdapter<String>
			implements ThemedSpinnerAdapter {
		
		private final ThemedSpinnerAdapter.Helper mDropDownHelper;
		
		MyAdapter(Context context, String[] objects) {
			
			super(context, android.R.layout.simple_list_item_1, objects);
			mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
		}
		
		@Override
		public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
			
			View view;
			
			if (convertView == null) {
				// Inflate the drop down using the helper's LayoutInflater
				LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
				view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			} else {
				view = convertView;
			}
			
			TextView textView = view.findViewById(android.R.id.text1);
			textView.setText(getItem(position));
			
			return view;
		}
		
		@Override
		public Theme getDropDownViewTheme() {
			
			return mDropDownHelper.getDropDownViewTheme();
		}
		
		@Override
		public void setDropDownViewTheme(Theme theme) {
			
			mDropDownHelper.setDropDownViewTheme(theme);
		}
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment
			extends Fragment {
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		public PlaceholderFragment() {
			
		}
		
		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_tabbed_spinner, container, false);
			TextView textView = rootView.findViewById(R.id.section_label);
			textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
}
