package com.beardedhen.androidbootstraptest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class choise extends Activity {
	private Button easy,mid,hard;
	Bundle bundle=new Bundle();
	OnClickListener listener=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choise);
		easy =(Button)findViewById(R.id.easy);
		mid =(Button)findViewById(R.id.mid);
		hard =(Button)findViewById(R.id.hard);
		listener =new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(v.getId())
				{
					case R.id.easy:
						bundle.putString("type", "简单");
						break;
					case R.id.mid:
						bundle.putString("type", "中等");
						break;
					case R.id.hard:
						bundle.putString("type", "困难");
						break;
					default:
						break;
				}	
				intent.putExtras(bundle);
				bundle.putInt("time", 0);
				bundle.putInt("max", 0);
				bundle.putInt("wrong", 0);
				bundle.putInt("right", 0);
				intent.setClass(choise.this, op.class);
				startActivity(intent);
				finish();
			}
		};
		easy.setOnClickListener(listener);
		mid.setOnClickListener(listener);
		hard.setOnClickListener(listener);
	}
	

}
