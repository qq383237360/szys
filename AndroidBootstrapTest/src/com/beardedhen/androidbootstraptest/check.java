package com.beardedhen.androidbootstraptest;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class check extends Activity {
	private ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		lv=(ListView)findViewById(R.id.listView1);
		//creattable();
		//Insert("xiaoming",120);
		query();
	}

	// �������ݿ�ķ���

		public void creattable(){
			// ����StuDBHelper����
			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// �õ�һ���ɶ���SQLiteDatabase����
			SQLiteDatabase db = dbHelper.getReadableDatabase();
		}


	public void Insert(String name,int utime){
		StuDBHelper dbHelper = new StuDBHelper(check.this,
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

	// ��ѯ���ݵķ���
	public void query(){

			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// �õ�һ����д�����ݿ�
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			// ����1������
			// ����2��Ҫ����ʾ����
			// ����3��where�Ӿ�
			// ����4��where�Ӿ��Ӧ������ֵ
			// ����5�����鷽ʽ
			// ����6��having����
			// ����7������ʽ
			Cursor cursor=db.rawQuery("select * from ulog order by utime desc", null);
			ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*�������д������*/
			cursor.moveToFirst();
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				int utime = cursor.getInt(cursor.getColumnIndex("utime"));
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("name", name);  
		            map.put("utime", utime);  
		            listItem.add(map);  
			}

				SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,//��Ҫ�󶨵�����                
				R.layout.item,//ÿһ�еĲ���
				//��̬�����е�����Դ�ļ���Ӧ�����岼�ֵ�View��
				new String[] {"name", "utime"},new int[] {R.id.name,R.id.utime});
				lv.setAdapter(mSimpleAdapter);//ΪListView��������

			// �ر����ݿ�
			db.close();
		}
	   public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	         	Intent intent=new Intent();
            	intent.setClass(check.this, MainActivity.class);
				startActivity(intent);
                finish();  
	  
	        }  
	          
	        return false;  
	          
	    } 
	// ɾ�����ݵķ���
	public void deleted(String name,int utime){
			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// �õ�һ����д�����ݿ�
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			// ����delete������ɾ������
			db.delete("ulog", null, null);
		}
	}