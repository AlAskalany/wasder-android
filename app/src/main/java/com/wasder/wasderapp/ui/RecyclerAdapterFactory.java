package com.wasder.wasderapp.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class RecyclerAdapterFactory {
	
	static RecyclerViewAdapterBase getInstance(Class<? extends RecyclerViewAdapterBase> fragmentClass, Context context, LinearLayoutManager
			layoutManager, OnFragmentInteractionListener mListener) {
		
		try {
			Constructor<? extends RecyclerViewAdapterBase> con = fragmentClass.getConstructor(
					Context.class,
					LinearLayoutManager.class,
					OnFragmentInteractionListener.class
			);
			return con.newInstance(context, layoutManager, mListener);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
