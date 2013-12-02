package com.cakeui.generic.adapter;

import java.util.List;

import com.cakeui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CakeGridViewAdapter extends BaseAdapter{

	private Context context;
	private List<Integer> itemsIconsArray;
	private List<String>  itemsNamesArray;
	
	public CakeGridViewAdapter(Context context, List<Integer> itemsIconsArray, List<String> itemsNamesArray) {
		this.context = context;
		this.itemsIconsArray = itemsIconsArray;
		this.itemsNamesArray = itemsNamesArray;
	}
	
	@Override
	public int getCount() {
		return itemsIconsArray.size();
	}

	@Override
	public Object getItem(int position) {
		return itemsIconsArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if (convertView == null){
			
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_gridview_items, parent, false);
			
			viewHolder = new ViewHolder();
			
			viewHolder.itemsNamesTextView = (TextView) convertView.findViewById(R.id.grid_TextView);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.itemsNamesTextView.setText(itemsNamesArray.get(position));
		viewHolder.itemsNamesTextView.setCompoundDrawablesWithIntrinsicBounds(null, context.getResources().getDrawable(itemsIconsArray.get(position)), 
				null, null);
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView itemsNamesTextView;
	}
}
