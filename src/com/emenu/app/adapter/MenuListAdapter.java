package com.emenu.app.adapter;

import java.util.ArrayList;

import com.emenu.app.activity.MenuListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MenuListAdapter extends FragmentPagerAdapter {

	private ArrayList<MenuListFragment> menuListFragmentList;
	private ArrayList<String> titleList;

	public MenuListAdapter(FragmentManager fm,
			ArrayList<MenuListFragment> menuListFragmentList,
			ArrayList<String> titleList) {
		super(fm);
		// TODO Auto-generated constructor stub
		// System.out.println("titlelist:" + titleList);
		this.titleList = titleList;
		this.menuListFragmentList = menuListFragmentList;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		if (menuListFragmentList == null || menuListFragmentList.size() == 0) {
			return null;
		} else {
			return menuListFragmentList.get(arg0);
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		if (titleList == null) {
//			return 0;
//		} else {
//			System.out.println("title size:" + titleList.size());
//			return titleList.size();
//		}
		return titleList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return titleList.get(position);
	}

}
