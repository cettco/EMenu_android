package com.emenu.app;

import com.emenu.app.activity.CartActivity;
import com.emenu.app.entities.QROrderEntity;

import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.util.CacheManager;
import android.app.Application;

public class Data extends Application {

	public static CartActivity tmpActivity;
	public static QROrderEntity qrOrderEntity;
	public static final int RESULT_FROM_REST_TO_MAIN_CODE = 5;
	public static final ImageCache IMAGE_CACHE = CacheManager.getImageCache();
//	private static final String baseUrl = "http://180.160.35.36";
	private static final String baseUrl = "http://m.tzwm.me:9999";
	public static final String ORDER_CONFIRM_URL = baseUrl+"/orderonline/index.php/UserControl/FinishOrder";
	public static final String CHECK_QR_CODE_URL = baseUrl+"/orderonline/index.php/UserControl/CheckCode";
	public static final String GET_ORDER_ACTIVITY_URL = baseUrl+"/orderonline/index.php/UserControl/MyActivity";
	public static final String GET_MENU_LIST = baseUrl+"/orderonline/index.php/ClientServer/MenuList";
	public static final String GET_MENU_ITEM_LIST = baseUrl+"/orderonline/index.php/ClientServer/MenuItemList";
	public static final String GET_RESTAURANT_LIST = baseUrl+"/orderonline/index.php/ClientServer/RestaurantList";
	public static final String PLACE_ORDER = baseUrl+"/orderonline/index.php/UserControl/PlaceOrder";
	public static final String HURRY_ORDER = baseUrl+"/orderonline/index.php/UserControl/HurryOrder";
	
/*	public String getBaseUrl()
	{
		return baseUrl;
	}
	public void setBaseUrl(String s)
	{
		this.baseUrl = s;
	}*/
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//baseUrl="http://180.160.35.36/";
		super.onCreate();
	}
	

}
