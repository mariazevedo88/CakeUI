package com.cakeui.generic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Class that implements a generic service.
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 */
public class CakeService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		return new CakeBinder();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		performTask();
		
	}
	
	protected void performTask(){}

		
	/**
	 * Binder used to return a instance of the service when some activity or service binds with it.
	 * @author Sarah Caixeta
	 */
	public class CakeBinder extends Binder {
		
		public CakeService getService() {
			
			return CakeService.this;
		}		
	}
}
