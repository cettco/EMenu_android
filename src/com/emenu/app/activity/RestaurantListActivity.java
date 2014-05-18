package com.emenu.app.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.trinea.android.common.view.DropDownListView;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.adapter.RestaurantListAdapter;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.entities.RestaurantItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RestaurantListActivity extends Activity {

	private Button locationButton;
	private DropDownListView resListView;
	private RestaurantListAdapter restaurantListAdapter;
	private ArrayList<RestaurantItemEntity> restaurantItemList;
	private ProgressBar restaurantProgressBar;
	private int startRestID = 0;
	private int len = 0;
	private QROrderEntity qrOrderEntity = null;
/*	String urlString = null;
	String nameString = null;
	String addString = null;*/
	
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
		restaurantProgressBar = (ProgressBar)findViewById(R.id.restListProgressbar);
		resListView = (DropDownListView)findViewById(R.id.resListView);
		restaurantItemList = new ArrayList<RestaurantItemEntity>();
/*		for(int i=0;i<10;i++)
		{
			RestaurantItemEntity item = new RestaurantItemEntity("test","test", "test");
			restaurantItemList.add(item);
		}*/
		Log.i("cate", "Long= "+restaurantItemList.size());
		restaurantListAdapter  = new RestaurantListAdapter(this, R.layout.menu_list_item,restaurantItemList);
		resListView.setAdapter(restaurantListAdapter);
		resListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				RestaurantItemEntity restaurantItemEntity = restaurantItemList.get(arg2);
				Intent intent = new Intent();
				intent.setClass(RestaurantListActivity.this, RestaurantDetailActivity.class);
				intent.putExtra("RestaurantItemEntity", restaurantItemEntity);
				intent.putExtra("qrOrderEntity", qrOrderEntity);
				startActivity(intent);
			}
			
		});
		resListView.setOnBottomListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!setOnListView(startRestID)) {
                    resListView.setHasMore(false);
                }

                // should call onBottomComplete function of DropDownListView at end of on bottom complete.
                resListView.onBottomComplete();
			}
		});
		setOnListView(startRestID);
	}
	
	private boolean setOnListView(int startID){
		
		startRestID = startRestID + 10;
		RequestParams params = new RequestParams();
		params.put("restaurantid", String.valueOf(startID));
		params.put("type", "all");
		HttpConnection.post("http://qianglee.com/orderonline/index.php/ClientServer/RestaurantList",params,	new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				super.onSuccess(response);
				restaurantProgressBar.setVisibility(View.INVISIBLE);
				try {
					if(response.getString("message").equals("Select Ok")){
						JSONObject result = response.getJSONObject("result");
						JSONArray restaurantListArray = result.getJSONArray("restaurantlist");
						len = restaurantListArray.length();
						for(int i=0;i<len;i++){
							JSONObject restaurantJsonObject = restaurantListArray.getJSONObject(i);
							String urlString = restaurantJsonObject.getString("pictureurl");
							String nameString = restaurantJsonObject.getString("restaurantname");
							String addString = restaurantJsonObject.getString("address");
							String typeString = restaurantJsonObject.getString("type");
							String phoneString = restaurantJsonObject.getString("phone");
							String restIDString = restaurantJsonObject.getString("restaurantid");
							String menuIDString = restaurantJsonObject.getString("menuid");
							String descriptionString = restaurantJsonObject.getString("description");
							RestaurantItemEntity item = new RestaurantItemEntity(urlString,nameString ,addString, typeString, restIDString, menuIDString, descriptionString, phoneString);
							//RestaurantItemEntity item = new RestaurantItemEntity("test","test", "test");
							restaurantItemList.add(item);
						}
						restaurantListAdapter.notifyDataSetChanged();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i("cate", "Json error!");
				}
				
			}
			
		});
		
		if(len>0) return true;
		else return false;
		
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
