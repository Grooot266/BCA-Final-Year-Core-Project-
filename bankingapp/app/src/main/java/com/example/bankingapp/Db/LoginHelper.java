package com.example.bankingapp.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginHelper extends SQLiteOpenHelper {
    public static final String DBNAME="login.db";

    public LoginHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase UserDb) {
        UserDb.execSQL("create Table users(name TEXT , phone INT,mail TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase UserDb, int i, int i1) {
        UserDb.execSQL("drop Table if exists users ");
    }
    public Boolean insertData(String name,Integer phone,String mail, String password){
        SQLiteDatabase UserDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("mail", mail);
        cv.put("password", password);
        long res = UserDb.insert("users",null,cv);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkmail(String mail){
        SQLiteDatabase UserDb = this.getWritableDatabase();
        Cursor cr = UserDb.rawQuery("select * from users where mail = ?", new String[] {mail});
        if(cr.getCount()>0){
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkstrdetails(String mail,  String password){
        SQLiteDatabase UserDb = this.getWritableDatabase();
        Cursor cr = UserDb.rawQuery("select * from users where mail = ? and password = ? ", new String[]{mail,password});
        if(cr.getCount()>0){
            return true;
        }
        else
        {
            return false;
        }

    }
}
