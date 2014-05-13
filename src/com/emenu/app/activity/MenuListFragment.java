package com.emenu.app.activity;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import cn.trinea.android.common.view.DropDownListView;
import cn.trinea.android.common.view.DropDownListView.OnDropDownListener;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListItemAdapter;
import com.emenu.app.entities.MenuItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
	private JSONArray cateItemArray;
	MenuListItemAdapter adapter;
	
	private View fragmentView;
	private DropDownListView mListView;
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	
	
	public static MenuListFragment newInstance(int catId) {
		MenuListFragment fragment = new MenuListFragment();
		fragment.catId = catId;
        return fragment;
    }
	
	public static MenuListFragment newInstance(JSONArray cateItemArray) {
		MenuListFragment fragment = new MenuListFragment();
		//fragment.catId = catId;
		//fragment.
		fragment.cateItemArray = cateItemArray;
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
		itemList = new ArrayList<MenuItemEntity>();		
		for(int i = 0;i<cateItemArray.length();i++)
		{
			try {
				JSONArray itemArray = cateItemArray.getJSONArray(i);
				MenuItemEntity item = new MenuItemEntity(itemArray.getString(0),""+itemArray.getDouble(1),itemArray.getString(2));
				itemList.add(item);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*MenuItemEntity item = new MenuItemEntity("test","test","test");
			itemList.add(item);*/
		}
		adapter  = new MenuListItemAdapter(getActivity(), R.layout.menu_list_item,itemList);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("oncreateactivity");
		if(adapter==null)
		{
			adapter  = new MenuListItemAdapter(getActivity(), R.layout.menu_list_item,itemList);
		}
		fragmentView = inflater.inflate(R.layout.menu_list, container, false);
		//mListView = (ListView)getActivity().findViewById(R.id.restList);
		mListView = (DropDownListView)fragmentView.findViewById(R.id.menuListView);
		mListView.setOnDropDownListener(new OnDropDownListener() {
			 
            @Override
            public void onDropDown() {
                Toast.makeText(getActivity(), "refresh clicke", Toast.LENGTH_SHORT).show();
            }
        });
		mListView.setOnBottomListener(new OnClickListener() {
			 
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "more clicke", Toast.LENGTH_SHORT).show();
            }
        });
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
