package com.emenu.app.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListAdapter;
import com.emenu.app.utils.HttpConnection;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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
import android.widget.ProgressBar;

public class MenuListActivity extends FragmentActivity {
	private ArrayList<MenuListFragment> pagerArrayList;
	private ArrayList<String> titleList;
	private Button viewCartBtn;
	private FragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabPageIndicator indicator;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menulist);
		pagerArrayList = new ArrayList<MenuListFragment>();
		titleList = new ArrayList<String>();

		progressBar = (ProgressBar) findViewById(R.id.menulist_progressbar);
		viewCartBtn = (Button) findViewById(R.id.viewCartBtn);
		viewCartBtn.setOnClickListener(viewCartBtncClickListener);
		pager = (ViewPager) findViewById(R.id.menuListPager);
		adapter = new MenuListAdapter(getSupportFragmentManager(),
				pagerArrayList, titleList);
		pager.setAdapter(adapter);
		indicator = (TabPageIndicator) findViewById(R.id.menuListIndicator);
		indicator.setViewPager(pager);
		onSetCategoryView();
	}

	private void onSetCategoryView() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		String menuID = intent.getStringExtra("menuid");
		RequestParams params = new RequestParams();
		params.put("menuid", menuID);
		Log.i("cate","menuid="+menuID);
		
		HttpConnection.post("http://qianglee.com/orderonline/index.php/ClientServer/MenuList", params, 
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject response) {
						// TODO Auto-generated method stub
						// super.onSuccess(response);
						progressBar.setVisibility(View.INVISIBLE);
						//System.out.println("success:" + response);

						try {
/*							JSONArray categoryJsonArray = response
									.getJSONArray("cat");
							int len = categoryJsonArray.length();
							for (int i = 0; i < len; i++) {
								JSONArray cateItemArray = response
										.getJSONArray(categoryJsonArray
												.getString(i));

								Log.i("cate", categoryJsonArray.getString(i));
								String aString = categoryJsonArray.getString(i);
								pagerArrayList.add(MenuListFragment
										.newInstance(response
												.getJSONArray(categoryJsonArray
														.getString(i))));
								titleList.add(categoryJsonArray.getString(i));
							}*/
							
							if(response.getString("message").equals("Select Ok")){
								JSONObject result = response.getJSONObject("result");
								JSONArray restaurantListArray = result.getJSONArray("menulist");
								int len = restaurantListArray.length();
								for(int i=0;i<len;i++){
									JSONObject restaurantJsonObject = restaurantListArray.getJSONObject(i);
									int categoryidID = Integer.parseInt(restaurantJsonObject.getString("categoryid"));
									pagerArrayList.add(MenuListFragment.newInstance(categoryidID));
									titleList.add(restaurantJsonObject.getString("categoryname"));
									
								}
							}
							adapter.notifyDataSetChanged();
							indicator.notifyDataSetChanged();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// adapter = new
						// MenuListAdapter(getSupportFragmentManager(),
						// pagerArrayList, titleList);
						// pager = (ViewPager)findViewById(R.id.menuListPager);
						// pager.setAdapter(adapter);

						// indicator =
						// (TabPageIndicator)findViewById(R.id.menuListIndicator);
						// indicator.setViewPager(pager);

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseBody, Throwable e) {
						// TODO Auto-generated method stub
						super.onFailure(statusCode, headers, responseBody, e);
						System.out.println("failure");
						progressBar.setVisibility(View.INVISIBLE);
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

	public boolean addMenuItem() {
		return true;
	}

}
