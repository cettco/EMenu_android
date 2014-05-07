package com.emenu.app.adapter;

import java.util.List;

import com.emenu.app.R;
import com.emenu.app.entities.MenuItemEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListItemAdapter extends ArrayAdapter<MenuItemEntity> {

	private List<MenuItemEntity> list;
	private Context context;

	public MenuListItemAdapter(Context context, int resource,
			List<MenuItemEntity> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.list = objects;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MenuItemEntity item =list.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.restaurant_list_items, parent,
				false);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.itemPic);
		TextView itemTitleView = (TextView) rowView.findViewById(R.id.itemTitle);
		TextView itemTextView = (TextView) rowView.findViewById(R.id.itemText);
		
		imageView.setImageResource(R.drawable.pic1);
		itemTitleView.setText(item.getItemTitle());
		itemTextView.setText(item.getItemText());

		return rowView;
	}

}
