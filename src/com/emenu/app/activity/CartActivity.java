package com.emenu.app.activity;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.trinea.android.common.view.DropDownListView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.emenu.app.Data;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CartActivity extends Activity {

	public static CartActivity cActivity;
	private ArrayList<CartItemEntity> menuItemList;
	private DropDownListView listView;
	private CartListAdapter adapter;
	private QROrderEntity qrOrderEntity;
	private CartItemEntity cartItemEntity;
	private ProgressBar cartViewProgressBar;
	private Button viewCartRefresh;
	private Button viewCartConfirm;
	private ImageButton backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		cActivity = this;
		
		Data.tmpActivity = CartActivity.this;
		
		setContentView(R.layout.activity_viewcart);
		listView = (DropDownListView)findViewById(R.id.cartListView);
		cartViewProgressBar = (ProgressBar)findViewById(R.id.cartViewProgressbar);
		menuItemList = new ArrayList<CartItemEntity>();	
		viewCartRefresh = (Button)findViewById(R.id.viewCartRefresh);
		viewCartConfirm = (Button)findViewById(R.id.viewCartConfirm);
		backButton = (ImageButton)findViewById(R.id.titleBarBack);
		backButton.setOnClickListener(listener);
/*		for(int i = 0;i<10;i++)
		{
			CartItemEntity item = new CartItemEntity("DishTitle", "$5.1125", "http://www.qianglee.com/a.jpg","1", 5);
			menuItemList.add(item);
		}*/
		getIntentData();
		adapter = new CartListAdapter(this, R.layout.cart_list_item, menuItemList, qrOrderEntity);
		listView.setAdapter(adapter);
		
		
		/*PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, "uguu2ceydHMuEmYuxWAf2q3u");*/
		PushManager.startWork(CartActivity.this,
				PushConstants.LOGIN_TYPE_API_KEY, "uguu2ceydHMuEmYuxWAf2q3u");
		
		Log.i("cate", "qr~~~~~~~~~~~~"+qrOrderEntity.getQrCode());
		setTagName(qrOrderEntity.getQrCode());
		
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				getHttpData();
			}
			
		});
		viewCartRefresh.setOnClickListener(listener);
		viewCartConfirm.setOnClickListener(listener);
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.viewCartRefresh:
				getHttpData();
				break;

			case R.id.viewCartConfirm:
				confirmOrder();
				break;
			case R.id.titleBarBack:
				CartActivity.this.finish();
			}

		}
	};
	
	private void confirmOrder(){
		RequestParams params = new RequestParams();
		params.put("tableid", qrOrderEntity.getTableID());
		String pushUrl = "http://m.tzwm.me:8000/push/"+qrOrderEntity.getRestaurantID();
		HttpConnection.get(pushUrl, new JsonHttpResponseHandler());
		HttpConnection.post(Data.ORDER_CONFIRM_URL,params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				//super.onSuccess(response);
				try {
					String message = response.getString("message");
					Log.i("cate", "que ren ding dan "+message);
					if (message.equals("confirm Ok!")) {
						viewCartConfirm.setClickable(false);
						Toast.makeText(CartActivity.this, "订单已经确认", Toast.LENGTH_LONG).show();
						viewCartConfirm.setText("已确认");
					}else {
						Toast.makeText(CartActivity.this, "确认失败", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(CartActivity.this, "确认失败", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable e) {
				// TODO Auto-generated method stub
				Toast.makeText(CartActivity.this, "确认失败", Toast.LENGTH_LONG).show();
			}

			
				
			
		});
		
	}
	
	public void test(){
		getHttpData();
	}
	
	private void setTagName(String tagName){
		List<String> tags = new ArrayList<String>();
		tags.add(tagName);
		PushManager.setTags(getApplicationContext(), tags);
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
			if (qrOrderEntity.getOrderID().equals("0")) {
				getOrderID();
			}else {
				getHttpData();
			}
		}
	}
	
	private void getOrderID(){
		String qrcode = qrOrderEntity.getQrCode();
		RequestParams params = new RequestParams();
		params.put("qrcode", qrcode);
		HttpConnection.post(Data.CHECK_QR_CODE_URL,params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, response);
				try {
					if(response.getString("code").equals("10021")){	
						JSONObject result = response.getJSONObject("result");
						JSONObject resultFromQRCode = result.getJSONObject("tableinfo");
						String orderIDString = resultFromQRCode.getString("orderid");
						qrOrderEntity.setOrderID(orderIDString);
						if (orderIDString.equals("0")) {
							cartViewProgressBar.setVisibility(View.INVISIBLE);
							Toast.makeText(CartActivity.this, "当前订单没有数据", Toast.LENGTH_LONG).show();
						}else{
							getDataFirstTime();
						}
					}else {
						Toast.makeText(CartActivity.this, "有误", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(CartActivity.this, "您扫描的二维码不能获取到数据", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable e) {
				// TODO Auto-generated method stub
				//super.onFailure(statusCode, headers, responseBody, e);
				Toast.makeText(CartActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
			}
			
			

		});
	}
	
	private void getDataFirstTime(){
		getHttpData();
	}
	
	private void updateView(){
		adapter.notifyDataSetChanged();
	}
	
	private void getHttpData(){
		RequestParams params = new RequestParams();
		params.put("orderid", qrOrderEntity.getOrderID());
		HttpConnection.post(Data.GET_ORDER_ACTIVITY_URL, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub 
				super.onSuccess(response);
				Log.i("cate", "cart response==>"+response.toString());
				try {
					if(response.getString("code").equals("10021")){
						cartViewProgressBar.setVisibility(View.INVISIBLE);
						JSONObject result = response.getJSONObject("result");
						JSONObject activityJsonObject  = result.getJSONObject("activity");
						JSONArray orderItemArray = activityJsonObject.getJSONArray("orderitem");
						String orderStatus = activityJsonObject.getString("orderstatus");
						if(!orderStatus.equals("ordering")){
							viewCartConfirm.setClickable(false);
							viewCartConfirm.setText("已确认");
						}
						int len = orderItemArray.length();
						menuItemList.clear();
						for(int i=0;i<len;i++){
							//todo
							JSONObject orderItem = orderItemArray.getJSONObject(i);
							String itemName = orderItem.getString("orderitemname");
							String itemID = orderItem.getString("itemid");
							String itemUrl = orderItem.getString("itemimage");
							String itemNum = orderItem.getString("quantity");
							String itemPrice = orderItem.getString("price");
							String itemStatus = orderItem.getString("itemstatus");
							int itemAmount = Integer.valueOf(itemNum);
							CartItemEntity item = new CartItemEntity(itemName, itemPrice, itemUrl, itemID,itemStatus, itemAmount);
							menuItemList.add(item);
						}
						//adapter.notifyDataSetChanged();
						updateView();
					}else {
						Toast.makeText(CartActivity.this, "获取订单数据失败", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i("cate", "Json error!");
					Toast.makeText(CartActivity.this, "获取订单数据失败", Toast.LENGTH_LONG).show();
				}
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable e) {
				// TODO Auto-generated method stub
				//super.onFailure(statusCode, headers, responseBody, e);
				Toast.makeText(CartActivity.this, "获取订单数据失败", Toast.LENGTH_LONG).show();
			}
		});
	}

}
