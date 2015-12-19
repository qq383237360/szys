package com.beardedhen.androidbootstraptest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.animation.ScaleAnimation;



public class no extends Activity {
	Bundle bundle=new Bundle();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no);
		final Bundle bundle= this.getIntent().getExtras();
		final ScaleAnimation scale=new ScaleAnimation(0,3,0,3);
		new CountDownTimer(1000,1000){
			
			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub
				scale.startNow();
				scale.setFillAfter(true);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(no.this, op.class);
				String type = bundle.getString("type");
				bundle.putInt("max", bundle.getInt("max"));
				bundle.putInt("right", bundle.getInt("right"));
				bundle.putInt("wrong", bundle.getInt("wrong"));
				bundle.putInt("time", bundle.getInt("time"));
				bundle.putString("type", type);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		}.start();

	}
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	Intent intent=new Intent();
			intent.setClass(no.this, op.class);
			String type = bundle.getString("type");
			System.out.print(type);
			bundle.putString("type", type);
			bundle.putInt("max", bundle.getInt("max"));
			bundle.putInt("right", bundle.getInt("riht"));
			bundle.putInt("wrong", bundle.getInt("wrong"));
			bundle.putInt("time", bundle.getInt("time"));
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
  
        }
		return false;  
    }
}
