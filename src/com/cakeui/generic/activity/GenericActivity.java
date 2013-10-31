package com.cakeui.generic.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.cakeui.R;
import com.cakeui.utils.PagesOpen;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 * Class that implements a generic activity.
 *
 */

public class GenericActivity extends SherlockFragmentActivity{

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
