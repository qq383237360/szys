package com.beardedhen.androidbootstraptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class EnterActivity extends Activity{

	TextView T1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter);
		T1=(TextView) findViewById(R.id.textView1);
		final ScaleAnimation scale=new ScaleAnimation(0,3,0,3);
		scale.setDuration(2000);
		new CountDownTimer(3000,1000){
			
			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub
				T1.setAnimation(scale);
				scale.startNow();
				scale.setFillAfter(true);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(EnterActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}.start();
	}
}
