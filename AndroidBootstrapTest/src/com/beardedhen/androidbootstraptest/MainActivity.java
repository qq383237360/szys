package com.beardedhen.androidbootstraptest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.FontAwesomeText;

public class MainActivity extends Activity {
	private Button jia,jian,cheng,chu,zonghe;
	Bundle bundle=new Bundle();
	OnClickListener listener=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		com.beardedhen.androidbootstrap.BootstrapButton jia = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnjia);
		com.beardedhen.androidbootstrap.BootstrapButton jian = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnjian);
		com.beardedhen.androidbootstrap.BootstrapButton cheng = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btncheng);
		com.beardedhen.androidbootstrap.BootstrapButton chu = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.btnchu);
		com.beardedhen.androidbootstrap.BootstrapButton zonghe = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.zonghe);
		com.beardedhen.androidbootstrap.BootstrapButton game = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.game);
		com.beardedhen.androidbootstrap.BootstrapButton rand = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.rand);
		listener =new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				// TODO Auto-generated method stub
				switch(v.getId())
				{
					case R.id.btnjian:
						bundle.putString("type", "减");
						break;
					case R.id.btnjia:
						bundle.putString("type", "加");
						break;
					case R.id.btncheng:
						bundle.putString("type", "乘");
						break;
					case R.id.btnchu:
						bundle.putString("type", "除");
						break;
					case R.id.game:
						bundle.putString("type", "小游戏");
						break;
					default:
						break;
				}
				if(v.getId()==R.id.game)
					bundle.putInt("time", 60);
				else
					bundle.putInt("time", 0);
				bundle.putInt("max", 0);
				bundle.putInt("wrong", 0);
				bundle.putInt("right", 0);
				intent.putExtras(bundle);
				intent.setClass(MainActivity.this, op.class);
				startActivity(intent);
				finish();
			}
		};
		jia.setOnClickListener(listener);
		jian.setOnClickListener(listener);
		cheng.setOnClickListener(listener);
		chu.setOnClickListener(listener);
		game.setOnClickListener(listener);
		zonghe.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, choise.class);
				startActivity(intent);
				finish();
			}});
		rand.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, check.class);
				startActivity(intent);
				finish();
			}});

	}
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("系统提示");  
            // 设置对话框消息  
            isExit.setMessage("确定要退出吗");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", exitlistener);  
            isExit.setButton2("取消", exitlistener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    } 
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener exitlistener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };  
}
