package com.cakeui.generic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 *
 * Class that implements a generic fragment.
 */

public class GenericFragment extends SherlockFragment{
	
	private int layoutID;
	
	public GenericFragment(){}
	
	public GenericFragment(int layoutID){
		this.layoutID = layoutID;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (layoutID != 0)
			return inflater.inflate(layoutID, container, false);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	

}
