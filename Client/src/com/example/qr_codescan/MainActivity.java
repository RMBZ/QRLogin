package com.example.qr_codescan;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	private TextView mTextView ;
	private ImageView mImageView;
	

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//主线程启动HTTP请求
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		mTextView = (TextView) findViewById(R.id.result); 
		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
		
		Button mButton = (Button) findViewById(R.id.button1);
		Button mLoginOK = (Button) findViewById(R.id.button2);
		mButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
		mLoginOK.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				String qrid = LoginOK.grid;
				String session = LoginOK.session;
				String token = LoginOK.token;
				String qr_Login_Url = "http://192.168.1.109:7080/Web_Spring_Test/btnLogin/"+qrid+"/"+token+"/"+session;
				System.out.println(qr_Login_Url);
				System.out.println("qrid:"+qrid);
				System.out.println("token:"+token);
				System.out.println("session:"+session);
				String html = CheckQRId.executeHttpGet(qr_Login_Url);
				mTextView.setText(html);
			}
		});
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				//扫描后执行
				//显示QRID
				mTextView.setText(bundle.getString("result"));
				String session = CheckQRId.login(bundle.getString("result"));
				mTextView.setText(session);
				//LoginOK.grid = bundle.getString("result");
				//LoginOK.session = session;
				//LoginOK.token = "fb57b174f668ae42e116db4cb1eef1b3";
			}
			break;
		}
    }	

}
