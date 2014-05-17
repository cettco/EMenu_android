package com.emenu.app.activity;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.view.DropDownListView;

import com.emenu.app.R;
import com.emenu.app.adapter.CartListAdapter;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.CartItemEntity;
import com.emenu.app.entities.MenuItemEntity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CartActivity extends Activity {

	private ArrayList<CartItemEntity> menuItemList;
	private DropDownListView listView;
	private CartListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewcart);
		listView = (DropDownListView)findViewById(R.id.cartListView);
		menuItemList = new ArrayList<CartItemEntity>();		
		for(int i = 0;i<10;i++)
		{
			CartItemEntity item = new CartItemEntity("DishTitle", "$5.1125", "http://www.qianglee.com/a.jpg", 5);
			menuItemList.add(item);
		}
		adapter = new CartListAdapter(this, R.layout.cart_list_item, menuItemList);
		listView.setAdapter(adapter);
	}

}
