package com.emenu.app.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.trinea.android.common.view.DropDownListView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.emenu.app.R;
import com.emenu.app.adapter.CartListAdapter;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.CartItemEntity;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.entities.RestaurantItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CartActivity extends Activity {

	public static CartActivity cActivity;
	private ArrayList<CartItemEntity> menuItemList;
	private DropDownListView listView;
	private CartListAdapter adapter;
	private QROrderEntity qrOrderEntity;
	private CartItemEntity cartItemEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		cActivity = this;
		setContentView(R.layout.activity_viewcart);
		listView = (DropDownListView)findViewById(R.id.cartListView);
		menuItemList = new ArrayList<CartItemEntity>();		
		for(int i = 0;i<10;i++)
		{
			CartItemEntity item = new CartItemEntity("DishTitle", "$5.1125", "http://www.qianglee.com/a.jpg","1", 5);
			menuItemList.add(item);
		}
		adapter = new CartListAdapter(this, R.layout.cart_list_item, menuItemList, qrOrderEntity);
		listView.setAdapter(adapter);
		
		
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "uguu2ceydHMuEmYuxWAf2q3u");
		getIntentData();
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
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
	
	private void updateView(){
		adapter.notifyDataSetChanged();
	}
	
	private void getHttpData(){
		RequestParams params = new RequestParams();
		params.put("orderid", qrOrderEntity.getOrderID());
		HttpConnection.post("http://www.qianglee.com/orderonline/index.php/UserControl/MyActivity", params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(response);
				try {
					if(response.getString("message").equals("Select Ok")){
						JSONObject result = response.getJSONObject("result");
						JSONArray restaurantListArray = result.getJSONArray("restaurantlist");
						int len = restaurantListArray.length();
						menuItemList.clear();
						for(int i=0;i<len;i++){
							//todo
						}
						//adapter.notifyDataSetChanged();
						updateView();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i("cate", "Json error!");
				}
				
			}
			
		});
	}

}
