package com.emenu.app.utils;
import com.loopj.android.http.*;
public class HttpConnection {
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	static{
		client.setTimeout(10);
	}
	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(url, params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(url, params, responseHandler);
	  }
	  
	  public static void get(String url, AsyncHttpResponseHandler responseHandler){
		  client.get(url, responseHandler);
	  }
	  
	  public static void post(String url, AsyncHttpResponseHandler responseHandler) {
	      client.post(url, responseHandler);
	  }
	  
	  public static void setTimeout(int timeout){
		  client.setTimeout(timeout);
	  }
}
