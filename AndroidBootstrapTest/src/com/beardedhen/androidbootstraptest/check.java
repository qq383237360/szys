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

	// 创建数据库的方法

		public void creattable(){
			// 创建StuDBHelper对象
			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// 得到一个可读的SQLiteDatabase对象
			SQLiteDatabase db = dbHelper.getReadableDatabase();
		}


	public void Insert(String name,int utime){
		StuDBHelper dbHelper = new StuDBHelper(check.this,
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

	// 查询数据的方法
	public void query(){

			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// 得到一个可写的数据库
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			// 参数1：表名
			// 参数2：要想显示的列
			// 参数3：where子句
			// 参数4：where子句对应的条件值
			// 参数5：分组方式
			// 参数6：having条件
			// 参数7：排序方式
			Cursor cursor=db.rawQuery("select * from ulog order by utime desc", null);
			ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
			cursor.moveToFirst();
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				int utime = cursor.getInt(cursor.getColumnIndex("utime"));
		            HashMap<String, Object> map = new HashMap<String, Object>();  
		            map.put("name", name);  
		            map.put("utime", utime);  
		            listItem.add(map);  
			}

				SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,//需要绑定的数据                
				R.layout.item,//每一行的布局
				//动态数组中的数据源的键对应到定义布局的View中
				new String[] {"name", "utime"},new int[] {R.id.name,R.id.utime});
				lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器

			// 关闭数据库
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
	// 删除数据的方法
	public void deleted(String name,int utime){
			StuDBHelper dbHelper = new StuDBHelper(check.this,
					"my_db", null, 1);
			// 得到一个可写的数据库
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			// 调用delete方法，删除数据
			db.delete("ulog", null, null);
		}
	}