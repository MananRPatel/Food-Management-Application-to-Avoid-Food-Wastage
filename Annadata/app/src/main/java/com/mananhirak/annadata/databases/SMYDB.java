package com.mananhirak.annadata.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SMYDB extends SQLiteOpenHelper {
    public SMYDB(Context context) {
        super(context,SPARA.DB_NAME,null,SPARA.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      String create="CREATE TABLE "+SPARA.TABLE_NAME+"( "+
              SPARA.TABLE_ID+" INTEGER PRIMARY KEY,"+
              SPARA.TABLE_USER+" TEXT,"+
              SPARA.TABLE_EMAIL+" TEXT,"+
              SPARA.TABLE_PASSWORD+" TEXT,"+
              SPARA.TABLE_ADDRESS+" TEXT )";
      sqLiteDatabase.execSQL(create);
        Log.d("MANANAN",create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void S_ADD_SECURITY(SDETAIL sdetail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(SPARA.TABLE_USER,sdetail.getName());
        contentValues.put(SPARA.TABLE_EMAIL,sdetail.getEmail());
        contentValues.put(SPARA.TABLE_PASSWORD,sdetail.getPassword());
        contentValues.put(SPARA.TABLE_ADDRESS,sdetail.getAddress());

        db.insert(SPARA.TABLE_NAME,null,contentValues);
        db.close();

    }

    public List<SDETAIL> S_GET_SECURITY(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<SDETAIL> list=new ArrayList<>();
        String select="SELECT * FROM "+SPARA.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                SDETAIL sdetail=new SDETAIL();
                sdetail.setId(cursor.getInt(0));
                sdetail.setName(cursor.getString(1));
                sdetail.setEmail(cursor.getString(2));
                sdetail.setPassword(cursor.getString(3));
                sdetail.setAddress(cursor.getString(4));
                list.add(sdetail);

            }while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public int USER_ID_GETTER(String name){
        int user_ID;
        SQLiteDatabase db=this.getReadableDatabase();
        String select = "SELECT * FROM " + SPARA.TABLE_NAME + " WHERE " + SPARA.TABLE_USER + "='" + name + "'";
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        user_ID = cursor.getInt(0);
        cursor.close();
        db.close();
        return user_ID;
    }

    public String USER_NAME_GETTER(String id){
        String user_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        String select = "SELECT * FROM " + SPARA.TABLE_NAME + " WHERE " + SPARA.TABLE_ID + "=" + id;
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        user_NAME = cursor.getString(1);
        cursor.close();
        db.close();
        return user_NAME;
    }

}
