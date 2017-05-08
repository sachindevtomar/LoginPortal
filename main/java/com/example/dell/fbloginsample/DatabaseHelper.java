package com.example.dell.fbloginsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 07-05-2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Login.db";
    public static final String TABLE_NAME= "Login_table";
    public static final String COL_1= "EMAIL";
    public static final String COL_2= "NAME";
    public static final String COL_3= "PASSWORD";
    public static final String COL_4= "MOBILE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (EMAIL TEXT PRIMARY KEY, NAME TEXT, PASSWORD TEXT, MOBILE TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String email, String name, String pass, String mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,email);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,pass);
        contentValues.put(COL_4,mobile);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where EMAIL='"+email+"'",null);
        return res;
    }
    public boolean updateData(String id,String name, String surname, String marks )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME,contentValues,"id = ?",new String[] {id});
        return true;
    }
}
