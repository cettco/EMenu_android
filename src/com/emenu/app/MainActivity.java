package com.emenu.app;


import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.emenu.app.R;
import com.emenu.app.activity.CartActivity;
import com.emenu.app.activity.MenuListActivity;
import com.emenu.app.activity.MipcaActivityCapture;
import com.emenu.app.activity.RestaurantListActivity;
import com.emenu.app.adapter.ImagePagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	private AutoScrollViewPager viewPager = null;
	private CirclePageIndicator indicator = null;
	private int pos = 0;
	private Button preOrderBtn;
	private Button scanBtn;
	private Button resListBtn;
	private ImageButton returnButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (AutoScrollViewPager) findViewById(R.id.scrollViewPager);
		indicator = (CirclePageIndicator) findViewById(R.id.circlePage);
		returnButton = (ImageButton)findViewById(R.id.titleBarBack);
		returnButton.setVisibility(View.INVISIBLE);
		
		List<Integer> imageIdList = new ArrayList<Integer>();
		imageIdList.add(R.drawable.pic1);
		imageIdList.add(R.drawable.pic2);
		imageIdList.add(R.drawable.pic3);
		imageIdList.add(R.drawable.pic4);
		//imageIdList.add(R.drawable.pic5);
		
		viewPager.setAdapter(new ImagePagerAdapter(getApplicationContext(),
				imageIdList));
		indicator.setViewPager(viewPager);
		viewPager.setInterval(2000);
		viewPager.startAutoScroll();

		indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				pos = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		scanBtn = (Button)findViewById(R.id.scanCode);
		preOrderBtn = (Button)findViewById(R.id.preOrder);
		resListBtn = (Button) findViewById(R.id.restaurantList);
		scanBtn.setOnClickListener(scanBtnClickListener);
		preOrderBtn.setOnClickListener(preOrderBtncClickListener);
		resListBtn.setOnClickListener(resListBtnClickListener);
		
		
		//���Դ���
		
	}
	private OnClickListener scanBtnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MipcaActivityCapture.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			
		}
	};
	private OnClickListener preOrderBtncClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.putExtra("QROrderEntity", Data.qrOrderEntity);
			intent.setClass(MainActivity.this, CartActivity.class);
			startActivity(intent);
		}
	};
	private OnClickListener resListBtnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, RestaurantListActivity.class);
			startActivity(intent);
			
		}
	};
	
	//Testing code
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				//��ʾɨ�赽������
				//scanResultText.setText(bundle.getString("result"));
				//��ʾ
				//mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(Data.qrOrderEntity!=null){
			preOrderBtn.setClickable(true);
		}else {
			preOrderBtn.setClickable(false);
		}
	}
	
	
	
}
