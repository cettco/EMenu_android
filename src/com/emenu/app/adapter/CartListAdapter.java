package com.emenu.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.emenu.app.entities.MenuItemEntity;

public class CartListAdapter extends ArrayAdapter<MenuItemEntity> {

	public CartListAdapter(Context context, int resource,
			List<MenuItemEntity> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
	

}
