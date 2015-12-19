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
	//----���մ�������-------------
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
        if(type.equals("С��Ϸ") && Count ==0)
        {
        	gameending();
        }
        else if(dmax>9 && !type.equals("С��Ϸ"))
        {
        	ending();
        }
        String gongshi;  
        if(type.equals("��"))
        	gongshi = op.creatadd();
        else if(type.equals("��"))
        	gongshi = op.creatjian();
        else if(type.equals("��"))
        	gongshi = op.creatcheng();
        else if(type.equals("��"))
        	gongshi = op.creatchu(20);
        else if(type.equals("��"))
        	gongshi = op.createasy();
        else if(type.equals("�е�"))
        	gongshi = op.creatMid();
        else if(type.equals("����"))
        	gongshi = op.creatHard();
        else if(type.equals("С��Ϸ"))
        	gongshi = op.creatHard();
        else
        	gongshi = op.createasy();
        //��ʼ�� ���
        tv=(TextView)findViewById(R.id.gongshi);
        time=(TextView)findViewById(R.id.time);
        d1=(Button)findViewById(R.id.daan1);
        d2=(Button)findViewById(R.id.daan2);
        d3=(Button)findViewById(R.id.daan3);
        d4=(Button)findViewById(R.id.daan4);
        Button da[]={d1,d2,d3,d4}; //�������� ��������������õ� 
        if(dmax<=9 || type.equals("С��Ϸ"))
        	timer.schedule(task, 1, 1000);
        double result = op.computeWithVector(gongshi); //���㹫ʽ����ȷ���
        tv.setText(gongshi+"=?"); //���ɴ𰸵�textview �� 
    	Random random = new Random();
        String abcd[]={"A��","B��","C��","D��"};
         right=random.nextInt(4);//������ȷ���� ABCD���λ����
        double res;
        for(int i=0;i<4;i++)
        {	
        	da[i].setText(abcd[i]);
        	res=result+(random.nextInt(2)*-1)*random.nextInt(20);//���ɴ�����20�������
        	while(res==result)
        	{
        		res=result+(random.nextInt(2)*-1)*random.nextInt(20);
        	}
        	if(i!=right)
        	{
        		da[i].setText(da[i].getText()+String.valueOf(res));//д���д𰸵�button��
        	}
        }
        da[right].setText(da[right].getText()+String.valueOf(result));//��ȷ��
        
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
            // �����˳��Ի���  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // ���öԻ������  
            isExit.setTitle("ϵͳ��ʾ");  
            // ���öԻ�����Ϣ  
            isExit.setMessage("ȷ��Ҫ�˳���ϰ ���ص���������");  
            // ���ѡ��ť��ע�����  
            isExit.setButton("ȷ��", listener);  
            isExit.setButton2("ȡ��", listener);  
            // ��ʾ�Ի���  
            isExit.show();  
  
        }  
          
        return false;  
          
    } 
    /**�����Ի��������button����¼�*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
        	
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
            	Intent intent=new Intent();
            	intent.setClass(op.this, MainActivity.class);
				startActivity(intent);
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���  
                break;  
            default:  
                break;  
            }  
        }  
    };   
    /**�����Ի��������button����¼�*/  
    DialogInterface.OnClickListener listener2 = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
        	
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
            		input();
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���  
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
        // �����˳��Ի���  
        AlertDialog isExit = new AlertDialog.Builder(this).create();  
        // ���öԻ������  
        isExit.setTitle("��ʾ");  
        isExit.setCancelable(false);
        // ���öԻ�����Ϣ  
        isExit.setMessage("�������ܹ������"+dright+"�⣬�����"+dwrong+"�⣬����ʱ��"+Count+"��");  
        // ���ѡ��ť��ע�����  
        isExit.setButton("ȷ��", listener);  
        // ��ʾ�Ի���  
        isExit.show();  
    }
    public void input(){
      	 final EditText inputServer = new EditText(this);
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("��ʾ\r\n�����������û���").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                 .setNegativeButton("ȡ��", null);
         builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

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
        // �����˳��Ի���  
        AlertDialog isExit = new AlertDialog.Builder(this).create();  
        // ���öԻ������  
        isExit.setTitle("��ʾ");  
        isExit.setCancelable(false);
        // ���öԻ�����Ϣ  
        isExit.setMessage("�������ܹ������"+dright+"�⣬�����"+dwrong+"�⣬����ʱ��"+Count+"��");  
        // ���ѡ��ť��ע�����  
        isExit.setButton("ȷ��", listener2);  
        // ��ʾ�Ի���  
        isExit.show();  
 
    }
    
    public void Insert(String name,int utime){
		StuDBHelper dbHelper = new StuDBHelper(op.this,
				"my_db", null, 1);
		// �õ�һ����д�����ݿ�
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// ����ContentValues���� //key:������value:������ֵ
		ContentValues cv = new ContentValues();
		// ��ContentValues���������ݣ���-ֵ��ģʽ
		cv.put("name", name);
		cv.put("utime",utime);
		// ����insert�����������ݲ������ݿ�
		db.insert("ulog", null, cv);
		// �ر����ݿ�
		db.close();
	}
  //---------------------------����ʱ---------------------------------
    TimerTask task = new TimerTask() {  
        @Override  
        public void run() {  
            runOnUiThread(new Runnable() {      // UI thread  
                @Override  
                public void run() {  
                	if(type.equals("С��Ϸ")){
                		if(Count ==0){
                			timer.cancel(); 
                			gameending();
                			}
                		else if(Count>0)
                			Count--;  
                        time.setText("�㻹ʣ�£�"+Count+"��");
                	}else{
                	Count++;  
                    time.setText("�����õ�ʱ��Ϊ��"+Count+"��");
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




