package com.cakeui.generic.adapter;

import java.util.List;

import com.cakeui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CakeListViewAdapter<T> extends BaseAdapter{

	private Context context;
	private List<T> listViewItems;
	
	public CakeListViewAdapter(Context context, List<T> listViewItems){
		this.context = context;
		this.listViewItems = listViewItems;
	}
	
	@Override
	public int getCount() {
		return listViewItems.size();
	}

	@Override
	public Object getItem(int index) {
		return listViewItems.get(index);
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
			convertView = inflater.inflate(R.layout.activity_listview_items, parent, false);
			
			viewHolder = new ViewHolder();
			
			viewHolder.itemsContentTextView = (TextView) convertView.findViewById(R.id.list_TextView);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.itemsContentTextView.setText(listViewItems.get(position).toString());
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView itemsContentTextView;
	}

}
