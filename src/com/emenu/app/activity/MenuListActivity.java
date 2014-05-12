package com.emenu.app.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListAdapter;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuListActivity extends FragmentActivity {
	private ArrayList<MenuListFragment> pagerArrayList;
	private ArrayList<String> titleList;
	private Button viewCartBtn;
	private FragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabPageIndicator indicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*setContentView(R.layout.activity_menulist);
		viewCartBtn = (Button)findViewById(R.id.viewCartBtn);
		viewCartBtn.setOnClickListener(viewCartBtncClickListener);
		pagerArrayList = new ArrayList<MenuListFragment>();
		titleList = new ArrayList<String>();*/
/*		pagerArrayList.add(MenuListFragment.newInstance(0));
		titleList.add("Dish");
		pagerArrayList.add(MenuListFragment.newInstance(1));
		titleList.add("meal");
		pagerArrayList.add(MenuListFragment.newInstance(2));
		titleList.add("drink");
		pagerArrayList.add(MenuListFragment.newInstance(3));
		titleList.add("special");*/
		
		onSetCategoryView();
		
/*		adapter = new MenuListAdapter(getSupportFragmentManager(), pagerArrayList, titleList);
		pager = (ViewPager)findViewById(R.id.menuListPager);
        pager.setAdapter(adapter);
        indicator = (TabPageIndicator)findViewById(R.id.menuListIndicator);
        indicator.setViewPager(pager);*/
		
	}
	
	
	private void onSetCategoryView() {
		// TODO Auto-generated method stub
		HttpConnection.post("http://www.qianglee.com/test.php",  new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(JSONObject response) {
				// TODO Auto-generated method stub
				//super.onSuccess(response);
				
				setContentView(R.layout.activity_menulist);
				viewCartBtn = (Button)findViewById(R.id.viewCartBtn);
				viewCartBtn.setOnClickListener(viewCartBtncClickListener);
				pagerArrayList = new ArrayList<MenuListFragment>();
				titleList = new ArrayList<String>();
				
				try {
					JSONArray categoryJsonArray = response.getJSONArray("cat");
					int len = categoryJsonArray.length();
					for(int i=0;i<len;i++){
						JSONArray cateItemArray = response.getJSONArray(categoryJsonArray.getString(i));
						
						Log.i("cate",categoryJsonArray.getString(i));
						String aString = categoryJsonArray.getString(i);
						pagerArrayList.add(MenuListFragment.newInstance(response.getJSONArray(categoryJsonArray.getString(i))));
						titleList.add(categoryJsonArray.getString(i));
					}
					//adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				adapter = new MenuListAdapter(getSupportFragmentManager(), pagerArrayList, titleList);
				pager = (ViewPager)findViewById(R.id.menuListPager);
		        pager.setAdapter(adapter);

		        indicator = (TabPageIndicator)findViewById(R.id.menuListIndicator);
		        indicator.setViewPager(pager);
				
			}
			
		});
	}


	private OnClickListener viewCartBtncClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MenuListActivity.this, CartActivity.class);
			startActivity(intent);
			
		}
	};
	public boolean addMenuItem()
	{
		return true;
	}
	
}
