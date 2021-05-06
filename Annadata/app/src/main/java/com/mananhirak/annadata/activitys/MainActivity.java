package com.mananhirak.annadata.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

     CardView c1,c2,c3,c4;
     int MAIN_CHECK_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();



        /*RMYDB db=new RMYDB(this);
        List<RDETAIL> list=db.R_GET_FOOD();

        for(RDETAIL rd:list){
            Log.d("MANANANAN","Food name is "+rd.getFood_name());
            Log.d("MANANANAN","user id is "+rd.getUser_id());
            Log.d("MANANANAN","food id is "+rd.getId());
            Log.d("MANANANAN","Food weight "+rd.getFood_weight());
            Log.d("MANANANAN","Food Point is "+rd.getFood_point());
            Log.d("MANANANAN","Food Price is "+rd.getFood_price());
            Log.d("MANANANAN","Food Information is "+rd.getFood_all_info());
            Log.d("MANANANAN","Food Sold is "+rd.getFood_sold());
            Log.d("MANANANAN","Food total is "+rd.getFood_total());
            Log.d("MANANANAN","Food time is "+rd.getFood_time());
        }*/


        c1=findViewById(R.id.mainprofile);
        c2=findViewById(R.id.mainseller);
        c3=findViewById(R.id.mainbuyer);
        c4=findViewById(R.id.maindelivery);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
                MAIN_CHECK_ID=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);

                Log.d("XYYYY"," "+MAIN_CHECK_ID);
                if(MAIN_CHECK_ID!=-1){
                    Intent intent=new Intent(MainActivity.this,Profile.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, Signinact.class);
                    intent.putExtra("MAIN_ACTIVITY_VALUE", 1);
                    startActivity(intent);
                }

            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
                MAIN_CHECK_ID=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);

                if(MAIN_CHECK_ID!=-1){
                    Log.d("XYYYY"," "+MAIN_CHECK_ID);
                    Intent intent=new Intent(MainActivity.this,FoodRetail.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, Signinact.class);
                    intent.putExtra("MAIN_ACTIVITY_VALUE", 2);
                    startActivity(intent);
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
                MAIN_CHECK_ID=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);

                Log.d("XYYYY"," "+MAIN_CHECK_ID);
                if(MAIN_CHECK_ID!=-1){
                    Intent intent=new Intent(MainActivity.this,FoodBuy.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, Signinact.class);
                    intent.putExtra("MAIN_ACTIVITY_VALUE", 3);
                    startActivity(intent);
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
                MAIN_CHECK_ID=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);

                Log.d("XYYYY"," "+MAIN_CHECK_ID);
                if (MAIN_CHECK_ID != -1) {
                    Intent intent = new Intent(MainActivity.this, FoodDelivery.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, Signinact.class);
                    intent.putExtra("MAIN_ACTIVITY_VALUE", 4);
                    startActivity(intent);
                }
            }
        });

    }
}