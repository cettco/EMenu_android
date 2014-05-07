package com.emenu.app.activity;

import java.util.ArrayList;

import com.emenu.app.R;
import com.emenu.app.adapter.MenuListAdapter;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;

public class MenuListActivity extends FragmentActivity {
	private ArrayList<MenuListFragment> pagerArrayList;
	private ArrayList<String> titleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menulist);
		pagerArrayList = new ArrayList<MenuListFragment>();
		pagerArrayList.add(MenuListFragment.newInstance(0));
		pagerArrayList.add(MenuListFragment.newInstance(1));
		pagerArrayList.add(MenuListFragment.newInstance(2));
		pagerArrayList.add(MenuListFragment.newInstance(3));
		titleList = new ArrayList<String>();
		titleList.add("特价");
		titleList.add("推荐");
		titleList.add("第三");
		titleList.add("第四");
		FragmentPagerAdapter adapter = new MenuListAdapter(getSupportFragmentManager(), pagerArrayList, titleList);
		ViewPager pager = (ViewPager)findViewById(R.id.menuListPager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.menuListIndicator);
        indicator.setViewPager(pager);
		
	}
	
}
