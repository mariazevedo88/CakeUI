package com.cakeui.application;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 * Class that implements a generic Application.
 *
 */

public class CakeApplication extends Application{

	private static CakeApplication instance;
	private ConnectivityManager connectivityManager; 
	
	public CakeApplication(){
		super();
		if (instance == null)
			instance = this;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	}
	
	public boolean isConnectedToNetwork(){
		
		if (connectivityManager == null)
			return false;
		
		NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if ((mobileInfo != null && mobileInfo.getState() == NetworkInfo.State.CONNECTED)
				|| (wifiInfo != null && wifiInfo.getState() == NetworkInfo.State.CONNECTED)) {
			return true;

		}
		return false; 
	}
	
}
