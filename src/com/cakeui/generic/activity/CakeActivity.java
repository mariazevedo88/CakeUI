package com.cakeui.generic.activity;

import java.util.HashMap;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.cakeui.R;
import com.cakeui.application.CakeApplication;
import com.cakeui.generic.fragment.CakeFragment;
import com.cakeui.generic.service.CakeService;
import com.cakeui.utils.CakeBroadcastReceiver;
import com.cakeui.utils.CakeDataEncapsulation;
import com.cakeui.utils.enums.PagesOpen;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 * Class that implements a generic Activity.
 *
 */

public class CakeActivity extends SherlockFragmentActivity{

	private ActionBar sherlockActionBar;
	private Menu menu;
	
	private CakeApplication cakeApp;
	private CakeBroadcastReceiver cakeBroadcastReceiver;
	
	private HashMap<CakeService, ServiceConnection> services;
	
	public PagesOpen pagesOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sherlockActionBar = getSupportActionBar();
		sherlockActionBar.setDisplayHomeAsUpEnabled(true);
		setSherlockActionBar(sherlockActionBar);
		
		cakeApp = (CakeApplication) getApplication();
		
		cakeBroadcastReceiver = new CakeBroadcastReceiver(this);
		registerReceiver(cakeBroadcastReceiver, new IntentFilter(CakeBroadcastReceiver.CAKE_BROADCAST));
		
		services = new HashMap<CakeService, ServiceConnection>();
		
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(cakeBroadcastReceiver);
		for (CakeService service : services.keySet()){
			unbindService(services.get(service));
		}
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
	
	public CakeApplication getCakeApplication(){
		return cakeApp;
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
	public void addFragmentToScreen(int containerViewId, int fragmentID, Class<? extends CakeFragment> fragmentClass, boolean mainFragment) throws InstantiationException, IllegalAccessException {

		CakeFragment newFragment = (CakeFragment) getSupportFragmentManager().findFragmentById(fragmentID);

		if (newFragment == null){
			newFragment = fragmentClass.newInstance();
			
			Bundle parameters = new Bundle();
			parameters.putBoolean("mainFragment", mainFragment);
			newFragment.setArguments(parameters);
			
			/* Controla se uma inst�ncia de um fragment � mantida atrav�s do re-build de uma activity.  
			 * Essa solu��o s� � permitida se n�o h� fragments na backstack, cujo caso se aplica e
			 * corrige o problema de instancia��o de fragments, que abortava o app*/
			newFragment.setRetainInstance(true);
			
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(containerViewId, newFragment, newFragment.getFragmentTag());
			fragmentTransaction.addToBackStack(null);
	
			/* permite o commit ser executado ap�s o estado de uma activity estiver salvo.
			 * Como a inst�ncia do fragment est� fixa na activity, n�o teremos problema
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
	
	public void bindToService(Class<? extends CakeService> cakeServiceClass){
		
		Intent serviceIntent = new Intent(this, cakeServiceClass);
		
		ServiceConnection serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

				services.put(((CakeService.CakeBinder) service).getService(), this);

			}

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}
		};

		startService(serviceIntent);
		
		bindService(serviceIntent, serviceConnection, 0);
	}
	
}
