package com.emenu.app.adapter;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.activity.CartActivity;
import com.emenu.app.adapter.MenuListItemAdapter.lvButtonListener;
import com.emenu.app.entities.CartItemEntity;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.utils.ProcessOrder;

public class CartListAdapter extends ArrayAdapter<CartItemEntity> {

	private Context context;
	private List<CartItemEntity> list;
	private Button addButton;
	private Button delButton;
	private ProcessOrder processOrder;
	private QROrderEntity qrOrderEntity;
	private TextView cartStatus;
	
	public CartListAdapter(Context context, int resource,
			List<CartItemEntity> objects, QROrderEntity	qrOrderEntity) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
		this.qrOrderEntity = qrOrderEntity; 
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return super.getView(position, convertView, parent);
		processOrder = new ProcessOrder();
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
		cartAmountTextView.setText(item.getCartAmount()+"");
		ImageView cartImageView = (ImageView)rowView.findViewById(R.id.cartImage);
		Data.IMAGE_CACHE.get(item.getCartPicUrl(), cartImageView);
		cartStatus = (TextView)rowView.findViewById(R.id.cartStatus);
		cartStatus.setText(item.getItemStatus());
		
		addButton = (Button)rowView.findViewById(R.id.addDish);
		delButton = (Button)rowView.findViewById(R.id.deleteDish);
		addButton.setOnClickListener(new lvButtonListener(position));
		delButton.setOnClickListener(new lvButtonListener(position));
		
	
		return rowView;
	}
	
	class lvButtonListener implements OnClickListener{
		private int position;
		
		public lvButtonListener(int pos) {
			// TODO Auto-generated constructor stub
			position = pos;
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			CartItemEntity cartItemEntity = list.get(position);
			switch (v.getId()) {
			case R.id.addDish:
				Log.i("cate","click add btn");
				processOrder.add(context, qrOrderEntity.getRestaurantID(), qrOrderEntity.getTableID(), qrOrderEntity.getOrderID(), "1", cartItemEntity.getCartItemID());
				break;

			case R.id.deleteDish:
				Log.i("cate","click del btn");
				Log.i("cate", "id"+cartItemEntity.getCartTitle());
				processOrder.del(context, qrOrderEntity.getRestaurantID(), qrOrderEntity.getTableID(), qrOrderEntity.getOrderID(), "1", cartItemEntity.getCartItemID());
				break;
			}
			
		}
		
	}


}
