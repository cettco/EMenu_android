package com.emenu.app;

import android.app.Application;

public class Data extends Application {

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
