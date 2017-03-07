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
	/**�������ݿ�ʱִ�У���ִֻ��һ��,һ�����
	 * �˷����д���һЩ��*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table score_table(_id integer primary key autoincrement,name text,score long not null)";
		db.execSQL(sql);
	}
	/**���ݿ�İ汾����ʱ���˷������Զ�ִ��*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	/**
	 * �ж��Ƿ�������ݿ⣬�������ڵ�ʮ���������
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
	 * ��ѯ���м�¼
	 */
	public Cursor getNum10(){
		SQLiteDatabase database = getReadableDatabase();
		String sql="select * from score_table order by score desc";
		return database.rawQuery(sql, null);
	}
	
	/**
	 * ���뵽���ݿ�
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
