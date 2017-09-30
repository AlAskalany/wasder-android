package com.wasder.wasderapp.Templates;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wasder.wasderapp.Factories.RecyclerAdapterFactory;
import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.R;

import java.lang.reflect.InvocationTargetException;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class TabFragment
        extends Fragment {
	
	private int columnCount;
	private String title;
	private int resLayout;
	private OnFragmentInteractionListener<Object, String> mListener;
	private Class<? extends BaseRecyclerAdapter> recyclerViewAdapterBaseClass;
	
	public TabFragment() {

        super();
	}
	
	@Override
	public void onAttach(Context context) {
		
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			//noinspection unchecked
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement " + "OnFragmentInteractionListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(resLayout, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.feedRecyclerView);
		if (recyclerView != null) {
			Context context = view.getContext();
			LinearLayoutManager layoutManager;
			layoutManager = columnCount <= 1 ? new LinearLayoutManager(context) : new GridLayoutManager(context, columnCount);
			recyclerView.setLayoutManager(layoutManager);
			BaseRecyclerAdapter b = null;
			try {
				b = RecyclerAdapterFactory.getInstance(recyclerViewAdapterBaseClass, context, layoutManager, mListener);
			} catch (java.lang.InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			recyclerView.setAdapter(b);
			
		}
		return view;
	}
	
	@Override
	public void onDetach() {
		
		super.onDetach();
		mListener = null;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setColumnCount(int columnCount) {
		
		this.columnCount = columnCount;
	}
	
	public void setResLayout(int resLayout) {
		
		this.resLayout = resLayout;
	}
	
	public void setRecyclerViewAdapterBaseClass(Class<? extends BaseRecyclerAdapter> recyclerViewAdapterBaseClass) {
		
		this.recyclerViewAdapterBaseClass = recyclerViewAdapterBaseClass;
	}
}
