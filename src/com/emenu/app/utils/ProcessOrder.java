package com.emenu.app.utils;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.emenu.app.Data;
import com.emenu.app.activity.CartActivity;
import com.emenu.app.activity.MipcaActivityCapture;
import com.emenu.app.entities.RestaurantItemEntity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ProcessOrder {
	//private String url = "http://www.qianglee.com/orderonline/index.php/UserControl/PlaceOrder";
	//private RequestParams params = null;
	private boolean isModifyOrderOK = false;
	private Context context;
	
	public boolean add(Context context, String restaurantId, String tableID, String orderID, String number, String itemID){
		conectionToModify(context, restaurantId, tableID, "add", orderID, number, itemID);
		return true;
	}
	public boolean del(Context context,String restaurantId, String tableID, String orderID, String number, String itemID){
		conectionToModify(context, restaurantId, tableID, "delete", orderID, number, itemID);
		return true;
	}
	
	private void show(int i){
		switch (i) {
		case 1:
			Toast.makeText(this.context, "网络连接失败", Toast.LENGTH_LONG).show();
			break;

		case 2:
			Toast.makeText(this.context, "添加成功", Toast.LENGTH_LONG).show();
			break;
		}
		
	}
	
	private boolean conectionToModify(Context context,String restaurantId, String tableID, String edit, String orderID, String number, String itemID){
		this.context = context;
		RequestParams params = new RequestParams();
		Log.i("cate","restaurantid"+restaurantId);
		Log.i("cate","tableid"+tableID);
		Log.i("cate","edit"+edit);
		Log.i("cate","orderid"+ orderID);
		Log.i("cate","number"+ number);
		Log.i("cate","itemid"+ itemID);
		params.put("restaurantid", restaurantId);
		params.put("tableid", tableID);
		params.put("edit", edit);
		//params.put("orderid", orderID);
		params.put("number", number);
		params.put("itemid", itemID);
		
		HttpConnection.post(Data.PLACE_ORDER, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				Log.i("cate", "--------------->add/del dish:"+response.toString());
				try {
					if(response.getString("message").equals("quantity adds 1")){
						isModifyOrderOK = true;
						Log.i("cate", "--------------->add successfully!");
						//Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
						show(1);
					}
					if(response.getString("message").equals("order has been finished!")){
						isModifyOrderOK = true;
						Log.i("cate", "--------------->add successfully!");
						show(2);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					isModifyOrderOK = false;
					Log.i("cate", "--------------->add failed!");
				}
			}
			
			

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable e) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseBody, e);
				Log.i("cate", "--------------->onfailure,add failed!");
			}

/*			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseBody) {
				// TODO Auto-generated method stub
				//super.onSuccess(statusCode, headers, responseBody);
				Log.i("cate", "--------------->add/del dish:"+responseBody);
			}*/
			
			
		});
		return true;
	}
}
