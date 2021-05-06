package com.mananhirak.annadata.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class BMYDB extends SQLiteOpenHelper {
    public BMYDB(Context context) {
        super(context,BPARA.DB_NAME,null,BPARA.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create="CREATE TABLE "+BPARA.TABLE_NAME+" ( "+
                BPARA.TABLE_ID+" INTEGER PRIMARY KEY, "+
                BPARA.TABLE_SELLER_ID+" INTEGER, "+
                BPARA.TABLE_BUYER_ID+" INTEGER, "+
                BPARA.TABLE_BUYER_ORDER+" INTEGER DEFAULT 0,"+
                BPARA.TABLE_BUYER_ADDRESS+" TEXT, "+
                BPARA.TABLE_DELIVERY_BOY+" INTEGER DEFAULT 0, "+
                BPARA.TABLE_DELIVERY_TIME+" TEXT, "+
                BPARA.TABLE_CONFORM_ID+" INTEGER DEFAULT 0 )";
        sqLiteDatabase.execSQL(create);
        Log.d("MANANANANA",create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void ADD_DETAIL(BDETAIL bdetail){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(BPARA.TABLE_SELLER_ID,bdetail.getSeller_id());
        contentValues.put(BPARA.TABLE_BUYER_ID,bdetail.getBuyer_id());
        contentValues.put(BPARA.TABLE_BUYER_ORDER,bdetail.getBuyer_order());
        contentValues.put(BPARA.TABLE_BUYER_ADDRESS,bdetail.getBuyer_address());
        contentValues.put(BPARA.TABLE_DELIVERY_BOY,bdetail.getDelivery_boy());
        contentValues.put(BPARA.TABLE_DELIVERY_TIME,bdetail.getDelivery_time());
        db.insert(BPARA.TABLE_NAME,null,contentValues);
        db.close();
    }

    public List<BDETAIL> GET_DETAIL(String User_ID){
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+BPARA.TABLE_NAME+" WHERE "+BPARA.TABLE_CONFORM_ID+"=0"+" AND "+BPARA.TABLE_DELIVERY_BOY+"="+User_ID;
        Cursor cursor=db.rawQuery(select,null);
        List<BDETAIL> list=new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                BDETAIL bdetail=new BDETAIL();
                bdetail.setId(cursor.getInt(0));
                bdetail.setSeller_id(cursor.getInt(1));
                bdetail.setBuyer_id(cursor.getInt(2));
                bdetail.setBuyer_order(cursor.getInt(3));
                bdetail.setBuyer_address(cursor.getString(4));
                bdetail.setDelivery_boy(cursor.getInt(5));
                bdetail.setDelivery_time(cursor.getString(6));
                bdetail.setConform_order(cursor.getInt(7));
                list.add(bdetail);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public BDETAIL B_DELIVERY_DETAIL(String Delivery_key){
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+BPARA.TABLE_NAME+" WHERE "+BPARA.TABLE_ID+" = "+Delivery_key;
        Cursor cursor=db.rawQuery(select,null);
        BDETAIL bdetail=new BDETAIL();
        if(cursor.moveToFirst()){
            bdetail.setId(cursor.getInt(0));
            bdetail.setSeller_id(cursor.getInt(1));
            bdetail.setBuyer_id(cursor.getInt(2));
            bdetail.setBuyer_order(cursor.getInt(3));
            bdetail.setBuyer_address(cursor.getString(4));
            bdetail.setDelivery_boy(cursor.getInt(5));
            bdetail.setDelivery_time(cursor.getString(6));
            bdetail.setConform_order(cursor.getInt(7));
        }
        cursor.close();
        db.close();
        return bdetail;
    }





    public void B_CONFORM_DELIVERY(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(BPARA.TABLE_CONFORM_ID,1);
        db.update(BPARA.TABLE_NAME,contentValues,BPARA.TABLE_ID+"=?",new String[]{ID});
        db.close();
    }


}
