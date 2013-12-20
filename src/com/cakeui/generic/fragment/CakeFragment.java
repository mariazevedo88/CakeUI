package com.cakeui.generic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.cakeui.generic.activity.CakeActivity;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 *
 * Class that implements a generic Fragment.
 */

public class CakeFragment extends SherlockFragment{

	private String previousFragmentTag;
	private String mainFragmentTag;
	
	private boolean mainFragment;

	public CakeFragment(){}
	
	@Override
	public void setArguments(Bundle args) {
		super.setArguments(args);
		
		this.mainFragment = args.getBoolean("mainFragment");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public String getFragmentTag(){
		return this.getClass().getName();
	}

	public FragmentManager getSherlockFragmentManager(){
		return getFragmentManager();
	}
	
	/**
	 * Sets the Fragment that is in the top of the FragmentTransaction's back stack. 
	 * You should set this attribute, if you want control the Android back button's iteration
	 * between previous Fragments. 
	 */
	public void setFirstFragmentOnBackStack(String previousFragmentTag){
		this.previousFragmentTag = previousFragmentTag;
	}
	
	/**
	 * Get the Fragment that is in the top of the FragmentTransaction's back stack.
	 * @return
	 */
	public String getFirstFragmentOnBackStack(){
		return previousFragmentTag;
	}
	
	/**
	 * Sets the operation's Main Fragment. You should set this attribute, 
	 * if you want that in the end of operations, the iteration with the positive option
	 * in dialogs lead your user to the Main Fragment's page.
	 */
	public void setMainFragmentTag(String tag){
		this.mainFragmentTag = tag;
	}
	
	/**
	 * Get the operation's Main Fragment.
	 * @return
	 */
	public String getMainFragmentTag(){
		return mainFragmentTag;
	}
	
	/**
	 * Sets true, if your Fragments' behavior is lead the user to the Main Fragment's page
	 * after the operation is finished, and false if your Fragments' behavior is back to 
	 * the previous Fragment's page after the operation is finished. 
	 * @param backtoMain
	 * @return
	 */
	public boolean isMainFragment(){
		return mainFragment;
	}
	
	/**
	 * Replaces the content of the container view with the new layout.
	 * @param containerViewId - ID of the FrameLayout in which the fragment will be added.
	 * @param fragmentID - ID of the root Layout of the fragment xml. 
	 * @throws IllegalAccessException 
	 * @throws java.lang.InstantiationException 
	 */
	protected void addFragment(int containerViewID, int fragmentID, Class<? extends CakeFragment> newFragment, boolean mainFragment) throws java.lang.InstantiationException, IllegalAccessException{
		((CakeActivity) this.getActivity()).addFragmentToScreen(containerViewID, fragmentID, newFragment, mainFragment);
	}
}
