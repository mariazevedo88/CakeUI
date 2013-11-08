package com.cakeui.utils;

import com.cakeui.R;
import com.cakeui.generic.activity.GenericActivity;
import com.cakeui.utils.enums.AsyncTaskType;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class CakeAsyncTask<T> extends AsyncTask<T, Void, Boolean>{

	private ProgressDialog dialog;
	private GenericActivity activity;
	
	private AsyncTaskType taskType;
	
	public CakeAsyncTask(GenericActivity activity, AsyncTaskType taskType){
		this.activity = activity;
		this.taskType = taskType;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(activity, activity.getString(R.string.dialog_information),
				activity.getString(R.string.message_dialog_information), true);
	}	
	
	@Override
	protected Boolean doInBackground(T... params) {
		
		if (taskType.equals(AsyncTaskType.LOGIN_TASK)){
			
		}else if (taskType.equals(AsyncTaskType.INFORMATION_TASK)){
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

}
