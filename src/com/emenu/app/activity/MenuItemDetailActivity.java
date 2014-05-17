package com.emenu.app.activity;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.entities.RestaurantItemEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuItemDetailActivity extends Activity {

	private MenuItemEntity menuItem;
	
	private ImageView menuItemPic = null;
	private TextView menuItemDetailName = null;
	private TextView menuItemPrice = null;
	private TextView menuItemUnit = null;
	private TextView menuItemDescription = null;
	private LinearLayout backToMenu = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_item_detail);
		
		onSetView();
		
	}
	
	private void getIntentData(){
		Intent intent = getIntent();
		menuItem = (MenuItemEntity)intent.getSerializableExtra("MenuItemEntity"); 
	}
	
	private void onSetView(){
		getIntentData();
		menuItemPic = (ImageView)findViewById(R.id.menuItemPic);
		menuItemDetailName = (TextView)findViewById(R.id.menuItemDetailName);
		menuItemPrice = (TextView)findViewById(R.id.menuItemPirce);
		menuItemUnit = (TextView)findViewById(R.id.menuItemUnit);
		menuItemDescription = (TextView)findViewById(R.id.menuItemDescription);
		backToMenu = (LinearLayout)findViewById(R.id.backToMenu);
		
		Data.IMAGE_CACHE.get(menuItem.getItemPicUrl(),menuItemPic);
		menuItemDetailName.setText(menuItem.getItemTitle());
		menuItemPrice.setText(menuItem.getItemText());
		menuItemUnit.setText(menuItem.getItemUnit());
		menuItemDescription.setText(menuItem.getDescription());
		backToMenu.setOnClickListener(listener);
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	};

}
