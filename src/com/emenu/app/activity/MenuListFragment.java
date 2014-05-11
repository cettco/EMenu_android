package com.emenu.app.activity;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.MenuItemEntity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class MenuListFragment extends Fragment{

	private static final String KEY_CONTENT = "MenuListFragment:Content";
	public static final String imageUrl = "http:////www.google.com//ig//images//weather//mostly_sunny.gif";
	private int catId;
	private View listView;
	private View mView;
	private ArrayList<MenuItemEntity> itemList;
	MenuListItemAdapter adapter;
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("ondestroy");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		System.out.println("destroyview");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onpause");
	}

	View fragmentView;
	ListView mListView;
	
	public static MenuListFragment newInstance(int catId) {
		MenuListFragment fragment = new MenuListFragment();
		fragment.catId = catId;
        return fragment;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		System.out.println("oncreateactivity");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
//            mContent = savedInstanceState.getString(KEY_CONTENT);
//        }
		System.out.println("oncreate");
		itemList = new ArrayList<MenuItemEntity>();		
		for(int i = 0;i<10;i++)
		{
			MenuItemEntity item = new MenuItemEntity("test","test","test");
			itemList.add(item);
		}
		adapter  = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("oncreateactivity");
		if(adapter==null)
		{
			adapter  = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
		}
//		if ((fragmentView != null)) 
//		{
//          //mContent = savedInstanceState.getString(KEY_CONTENT);
//			System.out.println("not null");
//			return fragmentView;
//		}
//		else 
//		{
//			System.out.println("null");
//			//adapter  = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
//			//savedInstanceState.putString(KEY_CONTENT, "abc");
//			itemList = new ArrayList<MenuItemEntity>();		
//			for(int i = 0;i<10;i++)
//			{
//				MenuItemEntity item = new MenuItemEntity("test","test","test");
//				itemList.add(item);
//			}
//			//LayoutInflater lf = getActivity().getLayoutInflater();
//			fragmentView = inflater.inflate(R.layout.restaurant_list, container, false);
//			//mListView = (ListView)getActivity().findViewById(R.id.restList);
//			mListView = (ListView)fragmentView.findViewById(R.id.restList);
//			mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//						long arg3) {
//					// TODO Auto-generated method stub
//					Intent intent = new Intent();
//					intent.setClass(getActivity(), RestaurantDetailActivity.class);
//					startActivity(intent);
//				}
//				
//			});
//			adapter  = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
//			mListView.setAdapter(adapter);
//			return fragmentView;
//		}
//		itemList = new ArrayList<MenuItemEntity>();		
//		for(int i = 0;i<10;i++)
//		{
//			MenuItemEntity item = new MenuItemEntity("test","test","test");
//			itemList.add(item);
//		}
		//LayoutInflater lf = getActivity().getLayoutInflater();
		fragmentView = inflater.inflate(R.layout.restaurant_list, container, false);
		//mListView = (ListView)getActivity().findViewById(R.id.restList);
		mListView = (ListView)fragmentView.findViewById(R.id.restList);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), RestaurantDetailActivity.class);
				startActivity(intent);
			}
			
		});
		//adapter  = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
		mListView.setAdapter(adapter);
		return fragmentView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Toast.makeText(getActivity(), "save state", Toast.LENGTH_SHORT).show();
		outState.putString(KEY_CONTENT, "abc");
	}
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
//	}
//	
}
