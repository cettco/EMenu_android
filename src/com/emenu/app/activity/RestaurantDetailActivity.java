package com.emenu.app.activity;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.RestaurantItemEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RestaurantDetailActivity extends Activity {

	//private TextView resDetailOrderClick = null;
	private LinearLayout restDetailOrder = null;
	private TextView restDetailName = null;
	private ImageView restPic = null;
	private TextView restDetailAddr = null;
	private TextView restTel = null;
	private TextView restDetail = null;
	private RestaurantItemEntity restaurantItemEntity = null;
	
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
	}

	private void onSetView(){
		
		setIntentContent();
		restDetailOrder = (LinearLayout)findViewById(R.id.restDetailOrderBtn);
		//resDetailOrderClick = (TextView) findViewById(R.id.resDetailOrderBtn);
		restDetailName = (TextView) findViewById(R.id.restDetailName);
		restPic = (ImageView)findViewById(R.id.restPic);
		restDetailAddr = (TextView)findViewById(R.id.restDetailAddr);
		restTel = (TextView)findViewById(R.id.restTel);
		restDetail = (TextView)findViewById(R.id.restDetail);
		
		restDetailOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RestaurantDetailActivity.this,MenuListActivity.class);
				intent.putExtra("menuid", restaurantItemEntity.getMenuID());
				startActivity(intent);				
			}
		});
		restDetailName.setText(restaurantItemEntity.getName());
		Data.IMAGE_CACHE.get(restaurantItemEntity.getUrl(),restPic);
		restDetailAddr.setText(restaurantItemEntity.getAddress());
		restTel.setText(restaurantItemEntity.getPhone());
		restDetail.setText(restaurantItemEntity.getDescription());
	}
}
