package com.cakeui.generic.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.cakeui.R;
import com.cakeui.utils.PagesOpen;

public class GenericActivity extends SherlockActivity{

	private ActionBar sherlockActionBar;
	private Menu menu;
	
	public PagesOpen pagesOpen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sherlockActionBar = getSupportActionBar();
		sherlockActionBar.setDisplayHomeAsUpEnabled(true);
		setSherlockActionBar(sherlockActionBar);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.layout.activity_action_bar, menu);
		
		this.menu = menu;
		actionBarItemsListeners(menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	private void actionBarItemsListeners (Menu menu){
		
		MenuItem menuLogoff = (MenuItem) menu.findItem(R.id.menu_logoff);
		menuLogoff.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return false;
			}
		});
		
	}
	
	public ActionBar getSherlockActionBar() {
		return sherlockActionBar;
	}


	public void setSherlockActionBar(ActionBar sherlockActionBar) {
		this.sherlockActionBar = sherlockActionBar;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public PagesOpen getPagesOpen() {
		return pagesOpen;
	}

	public void setPagesOpen(PagesOpen pagesOpen) {
		this.pagesOpen = pagesOpen;
	}
	
	
}
