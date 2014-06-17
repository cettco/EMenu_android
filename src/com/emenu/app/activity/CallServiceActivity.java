package com.emenu.app.activity;

import org.apache.http.Header;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CallServiceActivity extends Activity {

	private Button callWaiterButton;
	private Button payButton;
	private Button button1;
	private Button button2;
	private QROrderEntity qrOrderEntity;
	private ImageButton backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		getIntentData();
		callWaiterButton = (Button)findViewById(R.id.serviceCallWaiter);
		callWaiterButton.setOnClickListener(listener);
		backButton = (ImageButton)findViewById(R.id.titleBarBack);
		backButton.setOnClickListener(listener);

	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.serviceCallWaiter:
				if(qrOrderEntity!=null)
					hurryOrderConnection();
				break;
			case R.id.titleBarBack:
				CallServiceActivity.this.finish();
			default:
				break;
			}
		}
	};
	
	private void hurryOrderConnection(){
		String pushUrl = "http://m.tzwm.me:8000/hurry/"+qrOrderEntity.getRestaurantID();
		HttpConnection.get(pushUrl, new JsonHttpResponseHandler());
		Toast.makeText(CallServiceActivity.this, "正在呼叫服务", Toast.LENGTH_LONG).show();
		RequestParams params = new RequestParams();
		params.put("orderid", qrOrderEntity.getOrderID());
		HttpConnection.post(Data.HURRY_ORDER, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				//super.onSuccess(statusCode, headers, response);
				Log.i("cate", "hurry order: "+response.toString());	
				try {
					String message = response.getString("message");
					if (message.equals("insert Ok!")) {
						//Toast.makeText(CallServiceActivity.this, "正在呼叫服务", Toast.LENGTH_LONG).show();
					}else {
						//Toast.makeText(CallServiceActivity.this, "呼叫失败", Toast.LENGTH_LONG).show();
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//Toast.makeText(CallServiceActivity.this, "呼叫失败", Toast.LENGTH_LONG).show();
				}
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseBody, Throwable e) {
				// TODO Auto-generated method stub
				//super.onFailure(statusCode, headers, responseBody, e);
				Toast.makeText(CallServiceActivity.this, "呼叫失败", Toast.LENGTH_LONG).show();
			}
			
			
			
		});
	}

	private void getIntentData(){
		Intent intent = getIntent();
		qrOrderEntity = (QROrderEntity)intent.getSerializableExtra("QROrderEntity");
	}
	
}
