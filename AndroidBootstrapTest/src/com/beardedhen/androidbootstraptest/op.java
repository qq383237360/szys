package com.beardedhen.androidbootstraptest;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class op extends Activity {
	private TextView tv,time;
	private Button d1,d2,d3,d4;
	private static Handler handler;
	private Timer timer = new Timer(); 
	private int right;
	//----接收传递数据-------------
	private int Count;
	private int dmax;
	private int dright;
	private int dwrong;
	private String type;
	//----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.op);
        Core op = new Core();  
        Bundle bundle = this.getIntent().getExtras();
        dmax=bundle.getInt("max");
        dright=bundle.getInt("right");
        dwrong=bundle.getInt("wrong");
        Count=bundle.getInt("time");
        type = bundle.getString("type");
        if(type.equals("小游戏") && Count ==0)
        {
        	gameending();
        }
        else if(dmax>9 && !type.equals("小游戏"))
        {
        	ending();
        }
        String gongshi;  
        if(type.equals("加"))
        	gongshi = op.creatadd();
        else if(type.equals("减"))
        	gongshi = op.creatjian();
        else if(type.equals("乘"))
        	gongshi = op.creatcheng();
        else if(type.equals("除"))
        	gongshi = op.creatchu(20);
        else if(type.equals("简单"))
        	gongshi = op.createasy();
        else if(type.equals("中等"))
        	gongshi = op.creatMid();
        else if(type.equals("困难"))
        	gongshi = op.creatHard();
        else if(type.equals("小游戏"))
        	gongshi = op.creatHard();
        else
        	gongshi = op.createasy();
        //初始化 组件
        tv=(TextView)findViewById(R.id.gongshi);
        time=(TextView)findViewById(R.id.time);
        d1=(Button)findViewById(R.id.daan1);
        d2=(Button)findViewById(R.id.daan2);
        d3=(Button)findViewById(R.id.daan3);
        d4=(Button)findViewById(R.id.daan4);
        Button da[]={d1,d2,d3,d4}; //加入数组 下面生成随机答案用到 
        if(dmax<=9 || type.equals("小游戏"))
        	timer.schedule(task, 1, 1000);
        double result = op.computeWithVector(gongshi); //计算公式的正确结果
        tv.setText(gongshi+"=?"); //生成答案到textview 中 
    	Random random = new Random();
        String abcd[]={"A、","B、","C、","D、"};
         right=random.nextInt(4);//生成正确答案在 ABCD随机位置中
        double res;
        for(int i=0;i<4;i++)
        {	
        	da[i].setText(abcd[i]);
        	res=result+(random.nextInt(2)*-1)*random.nextInt(20);//生成答案正负20的随机数
        	while(res==result)
        	{
        		res=result+(random.nextInt(2)*-1)*random.nextInt(20);
        	}
        	if(i!=right)
        	{
        		da[i].setText(da[i].getText()+String.valueOf(res));//写所有答案到button中
        	}
        }
        da[right].setText(da[right].getText()+String.valueOf(result));//正确答案
        
        da[0].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkResult(0);
			}
		
		});
        da[1].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkResult(1);
			}
		
		});
        da[2].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkResult(2);
			}
		
		});
        da[3].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				checkResult(3);
			}
		
		});
        
       

    }
 
    
    @SuppressWarnings("deprecation")
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
            isExit.setMessage("确定要退出练习 返回到主界面吗");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    } 
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
        	
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            	Intent intent=new Intent();
            	intent.setClass(op.this, MainActivity.class);
				startActivity(intent);
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };   
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener2 = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
        	
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            		input();
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    }; 
    public void checkResult(int res)
    {
    	Intent intent=new Intent();
    	Bundle bundle=new Bundle();
    	if(res==right)
    	{
			intent.setClass(op.this, yes.class);
			bundle.putInt("right", dright+1);
			bundle.putInt("wrong", dwrong);
    	}
    	else
    	{
			intent.setClass(op.this, no.class);
			bundle.putInt("wrong", dwrong+1);
			bundle.putInt("right", dright);
    	}
		bundle.putString("type", type);
		bundle.putInt("max", dmax+1);
		bundle.putInt("time", Count);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
    }
    public void ending(){
        // 创建退出对话框  
        AlertDialog isExit = new AlertDialog.Builder(this).create();  
        // 设置对话框标题  
        isExit.setTitle("提示");  
        isExit.setCancelable(false);
        // 设置对话框消息  
        isExit.setMessage("您本次总共答对了"+dright+"题，答错了"+dwrong+"题，共耗时："+Count+"秒");  
        // 添加选择按钮并注册监听  
        isExit.setButton("确定", listener);  
        // 显示对话框  
        isExit.show();  
    }
    public void input(){
      	 final EditText inputServer = new EditText(this);
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("提示\r\n请输入您的用户名").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                 .setNegativeButton("取消", null);
         builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

             public void onClick(DialogInterface dialog, int which) {
                
                Insert(inputServer.getText().toString(),dright);
                Intent intent=new Intent();
            	intent.setClass(op.this, MainActivity.class);
				startActivity(intent);
                finish();  
              }
         });
         builder.show();
    }
    public void gameending(){
        // 创建退出对话框  
        AlertDialog isExit = new AlertDialog.Builder(this).create();  
        // 设置对话框标题  
        isExit.setTitle("提示");  
        isExit.setCancelable(false);
        // 设置对话框消息  
        isExit.setMessage("您本次总共答对了"+dright+"题，答错了"+dwrong+"题，共耗时："+Count+"秒");  
        // 添加选择按钮并注册监听  
        isExit.setButton("确定", listener2);  
        // 显示对话框  
        isExit.show();  
 
    }
    
    public void Insert(String name,int utime){
		StuDBHelper dbHelper = new StuDBHelper(op.this,
				"my_db", null, 1);
		// 得到一个可写的数据库
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 生成ContentValues对象 //key:列名，value:想插入的值
		ContentValues cv = new ContentValues();
		// 往ContentValues对象存放数据，键-值对模式
		cv.put("name", name);
		cv.put("utime",utime);
		// 调用insert方法，将数据插入数据库
		db.insert("ulog", null, cv);
		// 关闭数据库
		db.close();
	}
  //---------------------------倒计时---------------------------------
    TimerTask task = new TimerTask() {  
        @Override  
        public void run() {  
            runOnUiThread(new Runnable() {      // UI thread  
                @Override  
                public void run() {  
                	if(type.equals("小游戏")){
                		if(Count ==0){
                			timer.cancel(); 
                			gameending();
                			}
                		else if(Count>0)
                			Count--;  
                        time.setText("你还剩下："+Count+"秒");
                	}else{
                	Count++;  
                    time.setText("你所用的时间为："+Count+"秒");
                	}
                }  
            });  
        }  
    };  
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();  
	
		
	}


}




