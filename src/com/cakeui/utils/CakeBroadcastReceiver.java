package com.cakeui.utils;

import com.cakeui.generic.activity.GenericActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 *  Class that implements a BroadcastReceiver to exchange data between different application components.
 *
 */
public class CakeBroadcastReceiver extends BroadcastReceiver{

	public static final String CAKE_BROADCAST = "com.cakeui.broadcast.receiver";
	public static final String CAKE_BROADCAST_DATA = "cakeui.broadcast.receiver.data";
	
	private GenericActivity activity;
	
	public CakeBroadcastReceiver (GenericActivity activity){
		this.activity = activity;
	}
	
	@Override
	public void onReceive(Context context, Intent dataIntent) {

		if (dataIntent != null && dataIntent.hasExtra(CAKE_BROADCAST_DATA)){
			
			CakeDataEncapsulation cakeDataEncapsulation = (CakeDataEncapsulation) dataIntent.
					getSerializableExtra(CAKE_BROADCAST_DATA);
			activity.handleDataFromBroadcast(cakeDataEncapsulation);
		}
		
	}

}
