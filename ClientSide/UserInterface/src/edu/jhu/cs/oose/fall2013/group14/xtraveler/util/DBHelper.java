package edu.jhu.cs.oose.fall2013.group14.xtraveler.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**

 * Helper for creating a database to keep local variable
 * Abandoned. Not used for this app
 * @author angeld
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final String TAG = "DBDemo_DBHelper";

	/**
	 * @param context
	 *            应用程序上下文
	 * @param name
	 *            数据库的名字
	 * @param factory
	 *            查询数据库的游标工厂 一般情况下 用sdk默认的
	 * @param version
	 *            数据库的版本 版本号必须不小1
	 */
	public DBHelper(Context context) {
		super(context, "usernameDB", null, 1);
		Log.v(TAG, "Initialize");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS t_user ("
				+ "_id INTEGER PRIMARY KEY autoincrement," + "name TEXT" + ");");
		Log.v(TAG, "Create Table t_user ok");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
