package com.wasder.wasderapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wasder.wasderapp.R;
import com.wasder.wasderapp.models.DummyContent;
import com.wasder.wasderapp.models.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnGroupFragmentInteractionListener}
 * interface.
 */
public class GroupFragment extends Fragment {
	
	// TODO: Customize parameter argument names
	private static final String ARG_COLUMN_COUNT = "column-count";
	// TODO: Customize parameters
	private int mColumnCount = 2;
	private OnGroupFragmentInteractionListener mListener;
	private OnGroupDetailsListener mGroupDetailsListener;
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public GroupFragment() {
		
	}
	
	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static GroupFragment newInstance(int columnCount) {
		
		GroupFragment fragment = new GroupFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnGroupFragmentInteractionListener) {
			mListener = (OnGroupFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement " + "OnTwitchLiveFragmentInteractionListener");
		}
		
		if (context instanceof OnGroupDetailsListener) {
			mGroupDetailsListener = (OnGroupDetailsListener) context;
		} else {
			throw new RuntimeException((context.toString()) + " must implement " + "OnGroupDetailsListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_group_list, container, false);
		
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}
			recyclerView.setAdapter(new MyGroupRecyclerViewAdapter(DummyContent.ITEMS, mListener, mGroupDetailsListener));
		}
		return view;
	}
	
	@Override
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnGroupFragmentInteractionListener {
		
		// TODO: Update argument type and name
		void onGroupFragmentInteractionListener(DummyItem item);
	}
	
	public interface OnGroupDetailsListener {
		
		void onGroupDetailsListener();
	}
	
	/**
	 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
	 * specified {@link OnGroupFragmentInteractionListener}.
	 * TODO: Replace the implementation with code for your data type.
	 */
	public static class MyGroupRecyclerViewAdapter extends RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder> {
		
		private final List<DummyItem> mValues;
		private final OnGroupFragmentInteractionListener mListener;
		private OnGroupDetailsListener mGroupDetailsListener;
		
		public MyGroupRecyclerViewAdapter(List<DummyItem> items, OnGroupFragmentInteractionListener listener, OnGroupDetailsListener
				groupDetailsListener) {
			
			mValues = items;
			mListener = listener;
			mGroupDetailsListener = groupDetailsListener;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group, parent, false);
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			
			holder.mItem = mValues.get(position);
			holder.mIdView.setText(mValues.get(position).id);
			holder.mContentView.setText(mValues.get(position).content);
			holder.mDetailsButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					mGroupDetailsListener.onGroupDetailsListener();
				}
			});
			
			holder.mView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if (null != mListener) {
						// Notify the active callbacks interface (the activity, if the
						// fragment is attached to one) that an item has been selected.
						mListener.onGroupFragmentInteractionListener(holder.mItem);
					}
				}
			});
		}
		
		@Override
		public int getItemCount() {
			
			return mValues.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			
			public final View mView;
			public final TextView mIdView;
			public final TextView mContentView;
			public final ImageButton mDetailsButton;
			public DummyItem mItem;
			
			public ViewHolder(View view) {
				
				super(view);
				mView = view;
				mIdView = view.findViewById(R.id.id);
				mContentView = view.findViewById(R.id.content);
				mDetailsButton = view.findViewById(R.id.group_details_button);
			}
			
			@Override
			public String toString() {
				
				return super.toString() + " '" + mContentView.getText() + "'";
			}
		}
	}
}
