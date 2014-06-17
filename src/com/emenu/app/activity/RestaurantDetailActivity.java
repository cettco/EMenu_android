package com.emenu.app.activity;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.entities.RestaurantItemEntity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantDetailActivity extends Activity {

	//private TextView resDetailOrderClick = null;
	private LinearLayout restDetailOrder = null;
	private TextView restDetailName = null;
	private ImageView restPic = null;
	private TextView restDetailAddr = null;
	private TextView restTel = null;
	private TextView restDetail = null;
	private RestaurantItemEntity restaurantItemEntity = null;
	private QROrderEntity qrOrderEntity = null;
	private TextView restDetailTableNo = null;
	private ImageButton backButton;
	private String macAddress = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);
		onSetView();

	}
	
	private void setIntentContent(){
		Intent intent = getIntent();
		restaurantItemEntity = (RestaurantItemEntity)intent.getSerializableExtra("RestaurantItemEntity");
		qrOrderEntity = (QROrderEntity)intent.getSerializableExtra("QROrderEntity");
		macAddress = (String)intent.getSerializableExtra("macaddress");
		if (qrOrderEntity!=null) {
			Data.qrOrderEntity = qrOrderEntity;
		}
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	};

	private void onSetView(){
		
		setIntentContent();
		restDetailOrder = (LinearLayout)findViewById(R.id.restDetailOrderBtn);
		//resDetailOrderClick = (TextView) findViewById(R.id.resDetailOrderBtn);
		restDetailName = (TextView) findViewById(R.id.restDetailName);
		restPic = (ImageView)findViewById(R.id.restPic);
		restDetailAddr = (TextView)findViewById(R.id.restDetailAddr);
		restTel = (TextView)findViewById(R.id.restTel);
		restDetail = (TextView)findViewById(R.id.restDetail);
		restDetailTableNo = (TextView)findViewById(R.id.restDetailTableNo);
		backButton = (ImageButton)findViewById(R.id.titleBarBack);
		backButton.setOnClickListener(listener);
		
		restDetailAddr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2;
				String address = restDetailAddr.getText().toString();
				String uri = "intent://map/geocoder?address="+address+"&src=com.emenu.app#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
				try {
					intent2 = Intent.getIntent(uri);
					startActivity(intent2);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
//				Intent intent = new Intent();
//				intent.setClass(RestaurantDetailActivity.this, MapActivity.class);
//				startActivity(intent);
				
			}
		});
		
		restTel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String num=restTel.getText().toString();
				Pattern p = Pattern.compile("\\d+?");
				Matcher match = p.matcher(num);
				//正则验证输入的是否为数字
				if(match.matches()){
					Intent intent=new Intent("android.intent.action.CALL",Uri.parse("tel:"+num));
					startActivity(intent);
				}else{
					Toast.makeText(RestaurantDetailActivity.this, "号码不对",Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		restDetailOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RestaurantDetailActivity.this,MenuListActivity.class);
				intent.putExtra("menuid", restaurantItemEntity.getMenuID());
				intent.putExtra("QROrderEntity", qrOrderEntity);
				startActivity(intent);
			}
		});
		if (qrOrderEntity!=null) {
			restDetailTableNo.setText("桌号: " + qrOrderEntity.getTableID());
		}
		restDetailName.setText(restaurantItemEntity.getName());
		Data.IMAGE_CACHE.get(restaurantItemEntity.getUrl(),restPic);
		restDetailAddr.setText(restaurantItemEntity.getAddress());
		restTel.setText(restaurantItemEntity.getPhone());
		restDetail.setText(restaurantItemEntity.getDescription());
	}
}
