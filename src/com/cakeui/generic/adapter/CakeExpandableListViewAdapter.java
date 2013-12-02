package com.cakeui.generic.adapter;

import java.util.List;

import com.cakeui.R;
import com.cakeui.utils.CakeMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CakeExpandableListViewAdapter<T> extends BaseExpandableListAdapter{

	private Context context;
	private CakeMap<T, List<T>> expandableListViewItems;
	
	public CakeExpandableListViewAdapter(Context context, CakeMap<T, List<T>> expandableListViewItems){
		this.context = context;
		this.expandableListViewItems = expandableListViewItems;
		
	}
		
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return expandableListViewItems.getEntryFromIndex(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View row = convertView;
		
		ViewHolder viewHolder;
		
		if (row == null){
			
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.activity_expandable_listview_child, parent, false);
			
			viewHolder = new ViewHolder();
			
			viewHolder.childItemsContentTextView = (TextView) row.findViewById(R.id.expandableListView_child);
			row.setTag(viewHolder);
			
			}else{
				viewHolder = (ViewHolder) row.getTag();
			}
		
		viewHolder.childItemsContentTextView.setText
			(expandableListViewItems.getEntryFromIndex(groupPosition).get(childPosition).toString());
		
		return row;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return expandableListViewItems.getEntryFromIndex(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return expandableListViewItems.getKeyFromIndex(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return expandableListViewItems.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if (convertView == null){
			
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_expandable_listview_group, parent, false);
			
			viewHolder = new ViewHolder();
			
			viewHolder.groupItemsContentTextView = (TextView) convertView.findViewById(R.id.expandableListView_group);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		T object = (T) expandableListViewItems.getKeyFromIndex(groupPosition);
		
		viewHolder.groupItemsContentTextView.setText(object != null? object.toString(): "");
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
	
	static class ViewHolder{
		TextView groupItemsContentTextView;
		TextView childItemsContentTextView;
	}

}
