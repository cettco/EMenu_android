package com.emenu.app.activity;

import com.emenu.app.R;
import com.emenu.app.entities.QROrderEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CallServiceActivity extends Activity {

	private Button callWaiterButton;
	private Button payButton;
	private Button button1;
	private Button button2;
	private QROrderEntity qrOrderEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
	}

	private void getIntentData(){
		Intent intent = getIntent();
		qrOrderEntity = (QROrderEntity)intent.getSerializableExtra("QROrderEntity");
	}
	
}
