package com.emenu.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emenu.app.R;
import com.emenu.app.entities.CartItemEntity;
import com.emenu.app.entities.MenuItemEntity;

public class CartListAdapter extends ArrayAdapter<CartItemEntity> {

	private Context context;
	private List<CartItemEntity> list;
	public CartListAdapter(Context context, int resource,
			List<CartItemEntity> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		CartItemEntity item = list.get(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.cart_list_item, parent,
				false);
		TextView cartTitleTextView = (TextView)rowView.findViewById(R.id.cartTitle);
		cartTitleTextView.setText(item.getCartTitle());
		TextView cartPriceTextView = (TextView)rowView.findViewById(R.id.cartPrice);
		cartPriceTextView.setText(item.getCartPrice());
		TextView cartAmountTextView = (TextView)rowView.findViewById(R.id.cartAmount);
		cartAmountTextView.setText(item.getcartAmount()+"");
		ImageView cartImageView = (ImageView)rowView.findViewById(R.id.cartImage);
	
		return rowView;
	}
	

}
