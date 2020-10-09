package com.mananhirak.annadata.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class RMYDB extends SQLiteOpenHelper {
    public RMYDB(Context context) {
        super(context,RPARA.DB_NAME,null,RPARA.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create="CREATE TABLE "+RPARA.TABLE_NAME+" ( "+
                RPARA.TABLE_ID+" INTEGER PRIMARY KEY,"+
                RPARA.TABLE_USER+" INTEGER,"+
                RPARA.TABLE_FOOD_SOLD+" INTEGER DEFAULT 0,"+
                RPARA.TABLE_TOTAL_FOOD+" INTEGER DEFAULT 1,"+
                RPARA.TABLE_FOOD+" TEXT,"+
                RPARA.TABLE_POINT+" INTEGER DEFAULT 0,"+
                RPARA.TABLE_PRICE+" INTEGER DEFAULT 0,"+
                RPARA.TABLE_WEIGHT+" INTEGER,"+
                RPARA.TABLE_TIME+" TEXT,"+
                RPARA.TABLE_DATE+" TEXT,"+
                RPARA.TABLE_FOOD_INFO+" TEXT )";

        sqLiteDatabase.execSQL(create);
        Log.d("MANANANAN",create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void R_ADD_FOOD(RDETAIL rdetail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(RPARA.TABLE_USER,rdetail.getUser_id());
        contentValues.put(RPARA.TABLE_FOOD,rdetail.getFood_name());
        contentValues.put(RPARA.TABLE_WEIGHT,rdetail.getFood_weight());
        contentValues.put(RPARA.TABLE_TOTAL_FOOD,rdetail.getFood_total());
        contentValues.put(RPARA.TABLE_FOOD_SOLD,rdetail.getFood_sold());
        contentValues.put(RPARA.TABLE_TIME,rdetail.getFood_time());
        contentValues.put(RPARA.TABLE_DATE,rdetail.getFood_date());
        contentValues.put(RPARA.TABLE_FOOD_INFO,rdetail.getFood_all_info());
        contentValues.put(RPARA.TABLE_POINT,rdetail.getFood_point());
        contentValues.put(RPARA.TABLE_PRICE,rdetail.getFood_price());
        Log.d("MANANANAN","PUTTED");
        db.insert(RPARA.TABLE_NAME,null,contentValues);
        db.close();
    }

    public List<RDETAIL> R_GET_FOOD(){

        SQLiteDatabase db=this.getReadableDatabase();
        List<RDETAIL> list=new ArrayList<>();
        String select="SELECT * FROM "+RPARA.TABLE_NAME;//+" WHERE "+RPARA.TABLE_USER+" = "+USER_ID;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                RDETAIL rdetail=new RDETAIL();
                rdetail.setId(cursor.getInt(0));
                rdetail.setUser_id(cursor.getInt(1));
                rdetail.setFood_sold(cursor.getInt(2));
                rdetail.setFood_total(cursor.getInt(3));
                rdetail.setFood_name(cursor.getString(4));
                rdetail.setFood_point(cursor.getInt(5));
                rdetail.setFood_price(cursor.getInt(6));
                rdetail.setFood_weight(cursor.getInt(7));
                rdetail.setFood_time(cursor.getString(8));
                rdetail.setFood_date(cursor.getString(9));
                rdetail.setFood_all_info(cursor.getString(10));
                list.add(rdetail);

                Log.d("MANANANAN","TAKED");


            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public int R_FOOD_PRICE(String USER_ID){
        int price_value;
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT SUM("+RPARA.TABLE_PRICE+"*("+RPARA.TABLE_TOTAL_FOOD+" - "+RPARA.TABLE_FOOD_SOLD+") "+") FROM "+RPARA.TABLE_NAME+" WHERE "+RPARA.TABLE_USER+" = "+USER_ID;
        Log.d("MANANANAN",select);
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst()){
           price_value=cursor.getInt(0);
        }else
            price_value=-1;

        cursor.close();
        db.close();

        return price_value;
    }

    public int R_FOOD_POINT(String USER_ID){
        int point_value;
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT SUM("+RPARA.TABLE_POINT+") FROM "+RPARA.TABLE_NAME+" WHERE "+RPARA.TABLE_USER+" = "+USER_ID;
        Cursor cursor=db.rawQuery(select,null);
        if(cursor.moveToFirst())
            point_value=cursor.getInt(0);
        else
            point_value=-1;

        cursor.close();
        db.close();

        return point_value;
    }

    public List<RDETAIL> R_FOOD_SELL_GET_DATA(String USER_ID){

        SQLiteDatabase db=this.getReadableDatabase();
        List<RDETAIL> list=new ArrayList<>();
        String select="SELECT * FROM "+RPARA.TABLE_NAME+" WHERE "+RPARA.TABLE_USER+" != "+USER_ID+" AND "+RPARA.TABLE_FOOD_SOLD+" != 0";
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                RDETAIL rdetail=new RDETAIL();
                rdetail.setId(cursor.getInt(0));
                rdetail.setUser_id(cursor.getInt(1));
                rdetail.setFood_sold(cursor.getInt(2));
                rdetail.setFood_total(cursor.getInt(3));
                rdetail.setFood_name(cursor.getString(4));
                rdetail.setFood_point(cursor.getInt(5));
                rdetail.setFood_price(cursor.getInt(6));
                rdetail.setFood_weight(cursor.getInt(7));
                rdetail.setFood_time(cursor.getString(8));
                rdetail.setFood_date(cursor.getString(9));
                rdetail.setFood_all_info(cursor.getString(10));
                list.add(rdetail);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public RDETAIL R_USER_ALL_INFORMATION(String FOOD_ID){
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+RPARA.TABLE_NAME+" WHERE "+RPARA.TABLE_ID+" = "+FOOD_ID;
        Cursor cursor=db.rawQuery(select,null);
        RDETAIL rdetail=new RDETAIL();
        if(cursor.moveToFirst()){
            rdetail.setId(cursor.getInt(0));
            rdetail.setUser_id(cursor.getInt(1));
            rdetail.setFood_sold(cursor.getInt(2));
            rdetail.setFood_total(cursor.getInt(3));
            rdetail.setFood_name(cursor.getString(4));
            rdetail.setFood_point(cursor.getInt(5));
            rdetail.setFood_price(cursor.getInt(6));
            rdetail.setFood_weight(cursor.getInt(7));
            rdetail.setFood_time(cursor.getString(8));
            rdetail.setFood_date(cursor.getString(9));
            rdetail.setFood_all_info(cursor.getString(10));
        }

        cursor.close();
        db.close();
        return  rdetail;
    }

    public void R_USER_ORDER_SOLD(String FOOD_id,int order){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(RPARA.TABLE_FOOD_SOLD,order);
        db.update(RPARA.TABLE_NAME,contentValues,RPARA.TABLE_ID+"=?",new String[]{FOOD_id});
        db.close();

    }

}
