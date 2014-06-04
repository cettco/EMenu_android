package com.emenu.app.activity;

import java.io.IOException;
import java.util.Vector;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.emenu.app.Data;
import com.emenu.app.R;
import com.emenu.app.entities.QROrderEntity;
import com.emenu.app.entities.RestaurantItemEntity;
import com.emenu.app.utils.HttpConnection;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mining.app.zxing.camera.CameraManager;
import com.mining.app.zxing.decoding.CaptureActivityHandler;
import com.mining.app.zxing.decoding.InactivityTimer;
import com.mining.app.zxing.view.ViewfinderView;
/**
 * Initial the camera
 * @author Ryan.Tang
 */
public class MipcaActivityCapture extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private boolean isCodeCorrect = false;
	private RestaurantItemEntity restaurantItemEntity = null;
	private QROrderEntity qrOrderEntity = null;
	private String qrCodeString;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);
		//ViewUtil.addTopView(getApplicationContext(), this, R.string.scan_card);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		
/*		Button mButtonBack = (Button) findViewById(R.id.button_back);
		mButtonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MipcaActivityCapture.this.finish();
				
			}
		});*/
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	/**
	 * ����ɨ����
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		if (resultString.equals("")) {
			Toast.makeText(MipcaActivityCapture.this, "Scan failed!", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(MipcaActivityCapture.this, "正在解析二维码", Toast.LENGTH_LONG).show();
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("result", resultString);
			bundle.putParcelable("bitmap", barcode);
			resultIntent.putExtras(bundle);
			this.setResult(RESULT_OK, resultIntent);
		}
		qrCodeString = resultString;
		processResult(resultString);
		
	}
	
	private void jumpToRestDetailActivity() {
		// TODO Auto-generated method stub
		if(isCodeCorrect){
			Intent toIntent = new Intent();
			//RestaurantItemEntity restaurantItemEntity = new RestaurantItemEntity("null", "Name", "aaa", "te", "1", "1", "description", "12312"); 
			toIntent.putExtra("RestaurantItemEntity", restaurantItemEntity);
			toIntent.putExtra("QROrderEntity", qrOrderEntity);
			toIntent.setClass(MipcaActivityCapture.this, RestaurantDetailActivity.class);
			startActivity(toIntent);
			
		}
		MipcaActivityCapture.this.finish();
	}
	
	private void processResult(String qrString){
		RequestParams params = new RequestParams();
		params.put("qrcode", qrString);
		Log.i("cate", "start connect qr");
		HttpConnection.post(Data.CHECK_QR_CODE_URL,params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				// TODO Auto-generated method stub
				Log.i("cate", "---->QR:"+response.toString());
				//super.onSuccess(statusCode, response);
				try {
					if(response.getString("code").equals("10021")){		
						JSONObject result = response.getJSONObject("result");
						JSONObject resultFromQRCode = result.getJSONObject("tableinfo");
						String urlString = resultFromQRCode.getString("pictureurl");
						String nameString = resultFromQRCode.getString("restaurantname");
						String addString = resultFromQRCode.getString("address");
						String typeString = resultFromQRCode.getString("type");
						String phoneString = resultFromQRCode.getString("phone");
						String restIDString = resultFromQRCode.getString("restaurantid");
						String menuIDString = resultFromQRCode.getString("menuid");
						String descriptionString = resultFromQRCode.getString("description");
						String tableNoString = resultFromQRCode.getString("tableno");
						String tableStatuString = resultFromQRCode.getString("tablestatus");
						String orderIDString = resultFromQRCode.getString("orderid");
						restaurantItemEntity = new RestaurantItemEntity(urlString, nameString, addString, typeString, restIDString, menuIDString, descriptionString, phoneString);
						qrOrderEntity = new QROrderEntity(nameString, restIDString, tableNoString, tableStatuString, orderIDString, qrCodeString);
						isCodeCorrect = true;
					}else {
						Toast.makeText(MipcaActivityCapture.this, "您扫描的二维码不能获取到数据", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(MipcaActivityCapture.this, "您扫描的二维码不能获取到数据", Toast.LENGTH_LONG).show();
				}
				jumpToRestDetailActivity();
				
			}
			

			@Override
			public void onFailure(int statusCode, Throwable e,
					JSONObject errorResponse) {
				// TODO Auto-generated method stub
				//super.onFailure(statusCode, e, errorResponse);
				Log.i("cate", "qrfiled!!!!!!!!!!!"+errorResponse);
				jumpToRestDetailActivity();
			}

		});
		
	}
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}