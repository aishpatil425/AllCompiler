package com.example.AllCompiler;

import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JCGSQLiteRegister extends SQLiteOpenHelper {

	

	private static final String[] COLUMNS = { "id", "Name","Email", "Pass" };

	
	public JCGSQLiteRegister(Context context) {
		super(context,"RealDB", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_User_TABLE = "CREATE TABLE RegUser ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,Pass TEXT)";
		db.execSQL(CREATE_User_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop books table if already exists
		db.execSQL("DROP TABLE IF EXISTS RegUser");
		this.onCreate(db);
	}

	public void createUser(RegisterUser Ru) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", Ru.getName());
		values.put("Email", Ru.getemail());
		values.put("Pass", Ru.getPass());
		db.insert("RegUser", null, values);
		db.close();
	}

	public RegisterUser readBook(int id) {

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query("RegUser", // a. table
				COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		RegisterUser Ru = new RegisterUser();
		Ru.setId(Integer.parseInt(cursor.getString(0)));
		Ru.setName(cursor.getString(1));
		Ru.setemail(cursor.getString(2));
		Ru.setPass(cursor.getString(3));
		return Ru;
	}

}
