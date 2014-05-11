package com.emenu.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.emenu.app.R;
import com.emenu.app.entities.MenuItemEntity;

public class CartListAdapter extends ArrayAdapter<MenuItemEntity> {

	private Context context;
	private List<MenuItemEntity> list;
	public CartListAdapter(Context context, int resource,
			List<MenuItemEntity> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cart_list_item, parent,
				false);
		return rowView;
	}
	

}
