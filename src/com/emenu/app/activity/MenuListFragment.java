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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class MenuListFragment extends Fragment {

	private static final String KEY_CONTENT = "MenuListFragment:Content";
	public static final String imageUrl = "http:////www.google.com//ig//images//weather//mostly_sunny.gif";
	private int catId;
	private View listView;
	private View mView;
	private ArrayList<MenuItemEntity> itemList;
	
	public static MenuListFragment newInstance(int catId) {
		MenuListFragment fragment = new MenuListFragment();
		fragment.catId = catId;
        return fragment;
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
//            mContent = savedInstanceState.getString(KEY_CONTENT);
//        }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		itemList = new ArrayList<MenuItemEntity>();		
		for(int i = 0;i<10;i++)
		{
			MenuItemEntity item = new MenuItemEntity("test","test","test");
			itemList.add(item);
		}
		ListView mListView = new ListView(getActivity());
		
		//LayoutInflater lf = getActivity().getLayoutInflater();
		View fragmentView = inflater.inflate(R.layout.restaurant_list, container, false);
		//mListView = (ListView)getActivity().findViewById(R.id.restList);
		mListView = (ListView)fragmentView.findViewById(R.id.restList);
		final MenuListItemAdapter adapter = new MenuListItemAdapter(getActivity(), R.layout.restaurant_list_items,itemList);
		mListView.setAdapter(adapter);
		return fragmentView;
		//return super.onCreateView(inflater, container, savedInstanceState);
//		TextView v = new TextView(getActivity());
//		v.setText("hello world");
//		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		//outState.putString(KEY_CONTENT, mContent);
	}
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
//	}
//	
}
