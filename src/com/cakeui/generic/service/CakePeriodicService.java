package com.cakeui.generic.service;


/**
 * Class that implements a generic service that is executed periodically.
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 */
public class CakePeriodicService extends CakeService implements Runnable{

	private Thread syncThread;
	private long interval;
	
	@Override
	public void onCreate() {
		super.onCreate();
		startThread();
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
	
	public void setTimeInterval(long timeMillis){
		this.interval = timeMillis;
	}
	
	public long getTimeInterval(){
		return this.interval;
	}
		
}
