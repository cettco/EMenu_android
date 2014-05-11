package com.emenu.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.emenu.app.R;
import com.emenu.app.adapter.CartListAdapter;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.MenuItemEntity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewCartActivity extends Activity {

	private ArrayList<MenuItemEntity> menuItemList;
	private ListView listView;
	private CartListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new CartListAdapter(this, R.layout.activity_viewcart, menuItemList);
		listView.setAdapter(adapter);
	}

}
