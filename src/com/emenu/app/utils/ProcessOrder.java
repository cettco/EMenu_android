package com.emenu.app.utils;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.emenu.app.activity.CartActivity;
import com.emenu.app.activity.MipcaActivityCapture;
import com.emenu.app.entities.RestaurantItemEntity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ProcessOrder {
	private String url = "http://www.qianglee.com/orderonline/index.php/UserControl/PlaceOrder";
	//private RequestParams params = null;
	private boolean isModifyOrderOK = false;
	
	public boolean add(String restaurantId, String tableID, String orderID, String number, String itemID){
		conectionToModify(restaurantId, tableID, "add", orderID, number, itemID);
		return true;
	}
	public boolean del(String restaurantId, String tableID, String orderID, String number, String itemID){
		conectionToModify(restaurantId, tableID, "delete", orderID, number, itemID);
		return true;
	}
	
	private boolean conectionToModify(String restaurantId, String tableID, String edit, String orderID, String number, String itemID){
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
		
		HttpConnection.post(url, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				Log.i("cate", "--------------->"+response.toString());
				
				try {
					if(response.getString("code").equals("10022")){
						isModifyOrderOK = true;
						Log.i("cate", "--------------->add successfully!");
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
			
			
		});
		return true;
	}
}
