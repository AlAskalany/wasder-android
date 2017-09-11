package com.wasder.wasderapp.Factories;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.wasder.wasderapp.Interfaces.OnFragmentInteractionListener;
import com.wasder.wasderapp.Templates.BaseRecyclerAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Wasder AB CONFIDENTIAL
 * Created by ahmed on 9/10/2017.
 */

public class RecyclerAdapterFactory {
	
	public static BaseRecyclerAdapter getInstance(Class<? extends BaseRecyclerAdapter> fragmentClass, Context context, LinearLayoutManager
			layoutManager, OnFragmentInteractionListener mListener) {
		
		try {
			Constructor<? extends BaseRecyclerAdapter> con = fragmentClass.getConstructor(Context.class,
			                                                                              LinearLayoutManager.class,
			                                                                              OnFragmentInteractionListener.class);
			return con.newInstance(context, layoutManager, mListener);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
