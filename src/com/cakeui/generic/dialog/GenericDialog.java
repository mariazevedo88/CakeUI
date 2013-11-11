package com.cakeui.generic.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.cakeui.R;
import com.cakeui.generic.activity.GenericActivity;
import com.cakeui.generic.fragment.GenericFragment;
import com.cakeui.utils.enums.DialogType;
import com.cakeui.utils.enums.PagesOpen;

/**
 * Class that implements generic dialogs. 
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 *
 */

public class GenericDialog extends SherlockDialogFragment{

	private Context context;
	
	private DialogType dialogType;
	
	private GenericActivity activity;
	
	private GenericFragment fragment;
	
	public GenericDialog(){}
	
	public GenericDialog(Context context, DialogType dialogType, GenericActivity activity){
		this.context = context;
		this.dialogType = dialogType;
		this.activity = activity;
	}
	
	public GenericDialog (Context context, DialogType dialogType, GenericFragment fragment){
		this.context = context;
		this.dialogType = dialogType;
		this.fragment = fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		
		if (dialogType.equals(DialogType.CONFIRMATION_DIALOG)){
			builder = this.showConfirmationDialog(builder);
		}else if (dialogType.equals(DialogType.ERROR_DIALOG)){
			builder = this.showErrorDialog(builder);
		}else if (dialogType.equals(DialogType.INFORMATION_DIALOG)){
			builder = this.showInformationDialog(builder);
		}else if (dialogType.equals(DialogType.SUCCESS_DIALOG)){
			builder = this.showSuccessDialog(builder);
		}
		
		return builder.create();
	}
	private AlertDialog.Builder showConfirmationDialog(AlertDialog.Builder builder){
		
		LayoutInflater inflater = LayoutInflater.from(context);	
		
		View view = inflater.inflate(R.layout.dialog_confirmation, null);
		
		Button buttonConfirmation = (Button) view.findViewById(R.id.button_confirmation);
		buttonConfirmation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		Button buttonCancel = (Button) view.findViewById(R.id.button_cancel);
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		builder.setTitle(context.getResources().getString(R.string.dialog_confirmation));
		builder.setView(view);
		builder.setCancelable(false);
		
		return builder;
	}
	
	private AlertDialog.Builder showErrorDialog(AlertDialog.Builder builder){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View view = inflater.inflate(R.layout.dialog_error, null);
		
		Button button = (Button) view.findViewById(R.id.button_error);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragment != null){
					
					GenericFragment supportFragment = 
							(GenericFragment) fragment.getSherlockFragmentManager().
							findFragmentByTag(fragment.getFragmentTag());
					
					GenericActivity genericActivity = (GenericActivity) supportFragment.getActivity();
					
					if (fragment.isMainFragment())
						genericActivity.setPagesOpen(PagesOpen.JUST_ACTIVITY);
					
					genericActivity.onBackPressed();
				}
				
				dismiss();
			}
		});
		
		builder.setTitle(context.getResources().getString(R.string.dialog_information));
		builder.setView(view);
		builder.setCancelable(false);
		
		return builder;
	}
	
	private AlertDialog.Builder showInformationDialog(AlertDialog.Builder builder){
	
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View view = inflater.inflate(R.layout.dialog_information, null);
		
		Button button = (Button) view.findViewById(R.id.button_information);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	
		builder.setTitle(context.getResources().getString(R.string.dialog_information));
		builder.setView(view);
		builder.setCancelable(false);
		
		return builder;
	}

	private AlertDialog.Builder showSuccessDialog(AlertDialog.Builder builder){
	
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View view = inflater.inflate(R.layout.dialog_success, null);
		
		Button button = (Button) view.findViewById(R.id.button_success);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (fragment != null){
					
					GenericFragment supportFragment = 
							(GenericFragment) fragment.getSherlockFragmentManager().
							findFragmentByTag(fragment.getFragmentTag());
					
					GenericActivity genericActivity = (GenericActivity) supportFragment.getActivity();
					
					if (fragment.isMainFragment())
						genericActivity.setPagesOpen(PagesOpen.JUST_ACTIVITY);
					
					genericActivity.onBackPressed();
				}
				
				dismiss();
			}
		});
		
		builder.setTitle(context.getResources().getString(R.string.dialog_information));
		builder.setView(view);
		builder.setCancelable(false);
		
		
		
		return builder;
		
	}
	
}
