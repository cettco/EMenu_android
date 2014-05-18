package com.emenu.app.utils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.emenu.app.activity.CartActivity;

public class MyPushMessageReceiver extends FrontiaPushMessageReceiver {

	@Override
	public void onBind(Context arg0, int arg1, String arg2, String arg3,
			String arg4, String arg5) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onListTags(Context arg0, int arg1, List<String> arg2,
			String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(Context arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		String messageString = "透传消息 message=" + arg1 + " customContentString=" + arg2;
		System.out.println(messageString);
		onUpdateMenu(arg0);
		Log.i("cate", "--->"+messageString);
		if (arg1!=null) {
			try {
				JSONObject jsonObject = new JSONObject(arg1);
				System.out.println("ok!");
				if (jsonObject.getString("update").equals("1")) {
					System.out.println("=====================================================!");
					//onUpdateMenu(arg0);
				}
			} catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onNotificationClicked(Context arg0, String arg1, String arg2,
			String arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetTags(Context arg0, int arg1, List<String> arg2,
			List<String> arg3, String arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnbind(Context arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub

	}
	
	private void onUpdateMenu(Context context){
        Intent intent = new Intent();
        intent.setClass(context.getApplicationContext(), CartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
	}

}
