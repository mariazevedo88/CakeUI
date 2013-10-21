package com.cakeui.generic.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.cakeui.generic.activity.GenericActivity;
import com.cakeui.utils.DialogType;

public class GenericDialog extends SherlockDialogFragment{

	private Context context;
	
	private DialogType dialogType;
	
	private GenericActivity activity;
	
	private FragmentManager sherlockFragmentManager;
	
	public GenericDialog(){}
	
	public GenericDialog(Context context, DialogType dialogType, GenericActivity activity){
		this.context = context;
		this.dialogType = dialogType;
		this.activity = activity;
	}
	
	public GenericDialog (Context context, DialogType dialogType, GenericActivity activity, 
			FragmentManager sherlockFragmentManager){
		this.context = context;
		this.dialogType = dialogType;
		this.activity = activity;
		this.sherlockFragmentManager = sherlockFragmentManager;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		
		if (dialogType.equals(DialogType.CONFIRMATION_DIALOG)){
			
		}else if (dialogType.equals(DialogType.ERROR_DIALOG)){
			
		}else if (dialogType.equals(DialogType.INFORMATION_DIALOG)){
			
		}else if (dialogType.equals(DialogType.SUCCESS_DIALOG)){
			
		}
		
		return builder.create();
	}
	
}
