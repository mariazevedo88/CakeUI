package com.cakeui.generic.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.cakeui.R;
import com.cakeui.generic.fragment.GenericFragment;
import com.cakeui.utils.CakeBroadcastReceiver;
import com.cakeui.utils.CakeDataEncapsulation;
import com.cakeui.utils.PagesOpen;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 * Class that implements a generic activity.
 *
 */

public class GenericActivity extends SherlockFragmentActivity{

	private ActionBar sherlockActionBar;
	private Menu menu;
	
	private CakeBroadcastReceiver cakeBroadcastReceiver;
	
	public PagesOpen pagesOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sherlockActionBar = getSupportActionBar();
		sherlockActionBar.setDisplayHomeAsUpEnabled(true);
		setSherlockActionBar(sherlockActionBar);
		
		
		cakeBroadcastReceiver = new CakeBroadcastReceiver(this);
		registerReceiver(cakeBroadcastReceiver, new IntentFilter(CakeBroadcastReceiver.CAKE_BROADCAST));
		
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(cakeBroadcastReceiver);
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.layout.activity_action_bar, menu);
		
		this.menu = menu;
		actionBarItemsListeners(menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	private void actionBarItemsListeners (Menu menu){
		
		MenuItem menuLogoff = (MenuItem) menu.findItem(R.id.menu_logoff);
		menuLogoff.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return false;
			}
		});
		
	}
	
	public ActionBar getSherlockActionBar() {
		return sherlockActionBar;
	}


	public void setSherlockActionBar(ActionBar sherlockActionBar) {
		this.sherlockActionBar = sherlockActionBar;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public PagesOpen getPagesOpen() {
		return pagesOpen;
	}

	public void setPagesOpen(PagesOpen pagesOpen) {
		this.pagesOpen = pagesOpen;
	}
	
	@Override
	public void onBackPressed() {
		
		if (pagesOpen == PagesOpen.FRAGMENT_ONE)
			setPagesOpen(PagesOpen.JUST_ACTIVITY);
		else if (pagesOpen == PagesOpen.FRAGMENT_TWO)
			setPagesOpen(PagesOpen.FRAGMENT_ONE);
		else if (pagesOpen == PagesOpen.FRAGMENT_THREE)
			setPagesOpen(PagesOpen.FRAGMENT_TWO);
		else if (pagesOpen == PagesOpen.FRAGMENT_FOUR)
			setPagesOpen(PagesOpen.FRAGMENT_THREE);
		else if (pagesOpen == PagesOpen.FRAGMENT_FIVE)
			setPagesOpen(PagesOpen.FRAGMENT_FOUR);
	
		super.onBackPressed();
	}
	
	/**
	 * Replaces the content of the container view with the new layout.
	 * @param containerViewId - ID of the FrameLayout in which the fragment will be added.
	 * @param fragmentID - ID of the root Layout of the fragment xml. 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void addFragmentToScreen(int containerViewId, int fragmentID, Class<? extends GenericFragment> fragmentClass, boolean mainFragment) throws InstantiationException, IllegalAccessException {

		GenericFragment newFragment = (GenericFragment) getSupportFragmentManager().findFragmentById(fragmentID);

		if (newFragment == null){
			newFragment = fragmentClass.newInstance();
			
			Bundle parameters = new Bundle();
			parameters.putBoolean("mainFragment", mainFragment);
			newFragment.setArguments(parameters);
			
			/* Controla se uma instância de um fragment é mantida através do re-build de uma activity.  
			 * Essa solução só é permitida se não há fragments na backstack, cujo caso se aplica e
			 * corrige o problema de instanciação de fragments, que abortava o app*/
			newFragment.setRetainInstance(true);
			
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(containerViewId, newFragment, newFragment.getFragmentTag());
			fragmentTransaction.addToBackStack(null);
	
			/* permite o commit ser executado após o estado de uma activity estiver salvo.
			 * Como a instância do fragment está fixa na activity, não teremos problema
			 * ao recuperar o estado da activity*/
			fragmentTransaction.commitAllowingStateLoss(); 
		}
	}
	
	/**
	 * Receives an {@link CakeDataEncapsulation} object and make the proper use of its contents.
	 * @param dataEncapsulation
	 */
	public void handleDataFromBroadcast(CakeDataEncapsulation dataEncapsulation){
		
		if (dataEncapsulation.getDataType().equals(CakeDataEncapsulation.DataType.NETWORK_STATUS)){
			String networkStatus = (String) dataEncapsulation.getContent();
			if (networkStatus.equals(getString(R.string.broadcast_network_up))){
				//TODO change to green icon
			} else if (networkStatus.equals(getString(R.string.broadcast_network_down))){
				//TODO change to gray icon
			}
		}
		
	}
	
}
