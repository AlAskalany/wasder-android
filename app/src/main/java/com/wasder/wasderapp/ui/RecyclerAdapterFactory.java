package com.wasder.wasderapp.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class RecyclerAdapterFactory {
	
	static RecyclerViewAdapterBase getInstance(Class<? extends RecyclerViewAdapterBase> fragmentClass, Context context, RecyclerView
			feedRecyclerView,
	                                           LinearLayoutManager feedLinearLayoutManager, OnFragmentInteractionListener mListener) {
		
		try {
			//return fragmentClass.getConstructors(context.getClass(), feedRecyclerView.getClass(), feedLinearLayoutManager.getClass(), mListener
			//		.getClass()).newInstance(context, feedRecyclerView, feedLinearLayoutManager, mListener);
			return (RecyclerViewAdapterBase) fragmentClass.getConstructors()[0].newInstance(context, feedRecyclerView, feedLinearLayoutManager, mListener);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
