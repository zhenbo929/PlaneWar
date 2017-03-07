package com.lizhenbo.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

	public MySQLiteOpenHelper(Context context) {
		super(context,"score.db", null, 1);
	}
	/**创建数据库时执行，且只执行一次,一般会在
	 * 此方法中创建一些表*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table score_table(_id integer primary key autoincrement,name text,score long not null)";
		db.execSQL(sql);
	}
	/**数据库的版本升级时，此方法会自动执行*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	/**
	 * 判断是否加入数据库，分数大于第十名，则加入
	 */
	public boolean isInsert(int score){
		Cursor cursor =getNum10();
		if(cursor.getCount()>=10){
			cursor.moveToPosition(9);
			long score_num10 = cursor.getLong(2);
			if(score<=score_num10){
				return false;
			}
		}
		return true;
	}

	/**
	 * 查询所有记录
	 */
	public Cursor getNum10(){
		SQLiteDatabase database = getReadableDatabase();
		String sql="select * from score_table order by score desc";
		return database.rawQuery(sql, null);
	}
	/**
	 * 加入到数据库
	 */
	public void insert(String name,int score){
		if(TextUtils.isEmpty(name)){
			name="无名氏";
		}
		SQLiteDatabase database = getWritableDatabase();
		String sql_insert="insert into score_table values(null,?,?)";
		database.execSQL(sql_insert, new String[]{name,score+""});
	}
	
	
	
}
