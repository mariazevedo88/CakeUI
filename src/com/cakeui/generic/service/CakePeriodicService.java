package com.cakeui.generic.service;


/**
 * Class that implements a generic service that is executed periodically.
 * Initially, is set to execute the task once per minute, but the time interval can be changed.
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 *
 */
public class CakePeriodicService extends CakeService implements Runnable{

	private static final long ONE_MINUTE = 60000;
	
	private Thread syncThread;
	private long interval = ONE_MINUTE;
	
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
