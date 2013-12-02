package com.cakeui.generic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Class that implements a generic service that is executed periodically.
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 */
public class CakePeriodicService extends Service implements Runnable{

	private Thread syncThread;
	private long interval;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return new CakeBinder();
	}
	
	public void startThread() {
		if (syncThread == null){
			syncThread = new Thread(this);
			syncThread.start();
		}
	}
	
	@Override
	public void run() {
		while (true) {

			try {
				performTask();
				
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void performTask(){}
	
	public void setTimeInterval(long timeMillis){
		this.interval = timeMillis;
	}
	
	public long getTimeInterval(){
		return this.interval;
	}
		
	/**
	 * Binder used to return a instance of the service when some activity or service binds with it.
	 * @author Sarah Caixeta
	 */
	public class CakeBinder extends Binder {
		
		public CakePeriodicService getService() {
			
			return CakePeriodicService.this;
		}		
	}


}
