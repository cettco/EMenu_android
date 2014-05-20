package com.emenu.app;

import com.emenu.app.activity.CartActivity;

import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.util.CacheManager;
import android.app.Application;

public class Data extends Application {

	public static final ImageCache IMAGE_CACHE = CacheManager.getImageCache();
	public static CartActivity tmpActivity;
	private String baseUrl;
	public String getBaseUrl()
	{
		return baseUrl;
	}
	public void setBaseUrl(String s)
	{
		this.baseUrl = s;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		baseUrl="http://baidu.com";
		super.onCreate();
	}
	

}
