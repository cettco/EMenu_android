package com.emenu.app.activity;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.view.DropDownListView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.emenu.app.R;
import com.emenu.app.adapter.CartListAdapter;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.CartItemEntity;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.entities.QROrderEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class CartActivity extends Activity {

	private ArrayList<CartItemEntity> menuItemList;
	private DropDownListView listView;
	private CartListAdapter adapter;
	private QROrderEntity qrOrderEntity;
	
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
		
		
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "uguu2ceydHMuEmYuxWAf2q3u");
		getIntentData();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("cate", "---->update cart");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		PushManager.stopWork(getApplicationContext());
	}
	private void getIntentData(){
		Intent intent = getIntent();
		qrOrderEntity = (QROrderEntity)intent.getSerializableExtra("QROrderEntity");
		if (qrOrderEntity!=null) {
			List<String> tags = new ArrayList<String>();
			tags.add(qrOrderEntity.getQrCode());
			PushManager.setTags(getApplicationContext(), tags);
		}
	}

}
