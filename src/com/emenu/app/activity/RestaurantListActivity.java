package com.emenu.app.activity;

import java.util.ArrayList;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.adapter.RestaurantListAdapter;
import com.emenu.app.entities.RestaurantItemEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class RestaurantListActivity extends Activity {

	private Button locationButton;
	private ListView resListView;
	private RestaurantListAdapter restaurantListAdapter;
	private ArrayList<RestaurantItemEntity> restaurantItemList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurantlist);
		initPopupMenu();
		initListView();
	}
	private void initListView()
	{
		resListView = (ListView)findViewById(R.id.resListView);
		restaurantItemList = new ArrayList<RestaurantItemEntity>();
		for(int i=0;i<10;i++)
		{
			RestaurantItemEntity item = new RestaurantItemEntity("test","test", "test");
			restaurantItemList.add(item);
		}
		restaurantListAdapter  = new RestaurantListAdapter(this, R.layout.restaurant_list_items,restaurantItemList);
		resListView.setAdapter(restaurantListAdapter);
		resListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RestaurantListActivity.this, RestaurantDetailActivity.class);
				startActivity(intent);
			}
			
		});
	}
	private void initPopupMenu()
	{
		locationButton = (Button)findViewById(R.id.locationBtn);
		final PopupMenu popupMenu = new PopupMenu(this, locationButton);
		popupMenu.inflate(R.menu.popup_location_menu);
		locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
		popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_red:
//                        btn.setBackgroundResource(R.color.LightRed);
//                        break;
//                    case R.id.menu_blue:
//                        btn.setBackgroundResource(R.color.DullBlue);
//                        break;
//                    case R.id.menu_green:
//                        btn.setBackgroundResource(R.color.LightGreen);
//                        break;
//                }
            	Toast.makeText(RestaurantListActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
	}

}
