package com.emenu.app.activity;

import java.net.URISyntaxException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.emenu.app.R;

public class MapActivity extends Activity {
	//BMapController controller;
	private static final String LTAG = MapActivity.class.getSimpleName();
	MapView mMapView;
	private BaiduMap mBaiduMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext()); 
		//setContentView(R.layout.activity_map);
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// 当用intent参数时，设置中心点为指定点
			Bundle b = intent.getExtras();
			LatLng p = new LatLng(31,121);
			mMapView = new MapView(this,
					new BaiduMapOptions().mapStatus(new MapStatus.Builder()
							.target(p).build()));
		} else {
			mMapView = new MapView(this, new BaiduMapOptions());
		}
		LatLng point = new LatLng(31,121);
		mMapView = new MapView(this,
				new BaiduMapOptions().mapStatus(new MapStatus.Builder()
						.target(point).build()));
		setContentView(mMapView);
		mBaiduMap = mMapView.getMap();
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
			    .fromResource(R.drawable.icon_marka);  
			//构建MarkerOption，用于在地图上添加Marker  
			OverlayOptions option = new MarkerOptions()  
			    .position(point)  
			    .icon(bitmap);  
			//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option);
//		
//		Button button = new Button(this);
//		button.setText("click");
//		button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				try {
//					Intent intent2 = Intent.getIntent("");
//				} catch (URISyntaxException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//		
//		mMapView.addView(button);
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity 销毁时同时销毁地图控件
		mMapView.onDestroy();
	}
	

}
