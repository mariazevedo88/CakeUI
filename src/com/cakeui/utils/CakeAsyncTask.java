package com.cakeui.utils;

import com.cakeui.R;
import com.cakeui.generic.activity.CakeActivity;
import com.cakeui.utils.enums.AsyncTaskType;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 *
 * Class that implements generic AsyncTask. 
 */

public class CakeAsyncTask<T> extends AsyncTask<T, T, Boolean>{

	private ProgressDialog dialog;
	private CakeActivity activity;
	
	private AsyncTaskType taskType;
	
	private int duration;
	
	public CakeAsyncTask(CakeActivity activity, AsyncTaskType taskType){
		this.activity = activity;
		this.taskType = taskType;
		
		this.duration = 0;
	}
	
	public CakeAsyncTask(CakeActivity activity, AsyncTaskType taskType, int duration){
		this.activity = activity;
		this.taskType = taskType;
		this.duration = duration;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(activity, activity.getString(R.string.dialog_information),
				activity.getString(R.string.message_dialog_information), true);
	}	
	
	@Override
	protected Boolean doInBackground(T... params) {
		
		try {
			new Thread();
			publishProgress(params);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		dialog.dismiss();
	}
	
	@Override
	protected void onProgressUpdate(T... values) {
		if (taskType.equals(AsyncTaskType.LOGIN_TASK)){
			if (activity.getCakeApplication().isConnectedToNetwork()){
				dialog.setMessage(activity.getResources().getString(R.string.async_task_logging_online));
			}else{
				dialog.setMessage(activity.getResources().getString(R.string.async_task_logging_offline));
			}
		}else if (taskType.equals(AsyncTaskType.INFORMATION_TASK)){
			dialog.setMessage(activity.getResources().getString(R.string.async_task_checking_updates));
		}
		super.onProgressUpdate(values);
	}
}
