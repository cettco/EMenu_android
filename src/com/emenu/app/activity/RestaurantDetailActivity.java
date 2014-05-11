package com.emenu.app.activity;

import com.emenu.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantDetailActivity extends Activity {

	private TextView resDetailOrderClick;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);
		resDetailOrderClick = (TextView) findViewById(R.id.resDetailOrderBtn);
		resDetailOrderClick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RestaurantDetailActivity.this,MenuListActivity.class);
				startActivity(intent);				
			}
		});
	}

}
