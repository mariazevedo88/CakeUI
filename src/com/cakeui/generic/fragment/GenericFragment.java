package com.cakeui.generic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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

	public String getFragmentTag(){
		return this.getClass().getName();
	}

	/**
	 * 
	 * @param containerViewId - ID of the FrameLayout in which the fragment will be added.
	 * @param fragmentID - ID of the root Layout of the fragment xml. 
	 */
	protected void addFragment(int containerViewId, int fragmentID) {

		GenericFragment newFragment = (GenericFragment) getFragmentManager().findFragmentById(fragmentID);

		if (newFragment == null){
			newFragment = new GenericFragment(fragmentID);

			/* Controla se uma inst�ncia de um fragment � mantida atrav�s do re-build de uma activity.  
			 * Essa solu��o s� � permitida se n�o h� fragments na backstack, cujo caso se aplica e
			 * corrige o problema de instancia��o de fragments, que abortava o app*/
			newFragment.setRetainInstance(true);
			
			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			fragmentTransaction.replace(containerViewId, newFragment, newFragment.getFragmentTag());
			fragmentTransaction.addToBackStack(null);

			/* permite o commit ser executado ap�s o estado de uma activity estiver salvo.
			 * Como a inst�ncia do fragment est� fixa na activity, n�o teremos problema
			 * ao recuperar o estado da activity*/
			fragmentTransaction.commitAllowingStateLoss(); 
		}
	}

}
