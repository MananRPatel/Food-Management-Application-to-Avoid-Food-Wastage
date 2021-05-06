package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.mananhirak.annadata.R;
import com.mananhirak.annadata.cyclegroup.Food_Delivery_Adapter;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.BMYDB;

import java.util.List;
import java.util.Objects;

public class FoodDelivery extends AppCompatActivity {

    RecyclerView recyclerView;
    Food_Delivery_Adapter food_delivery_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_delivery);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        int USER_id =sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);
        Log.d("MANANANAN","USER ID IIS "+USER_id);

        recyclerView=findViewById(R.id.fdrcycledelivery);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BMYDB bmydb=new BMYDB(this);
        List<BDETAIL> list=bmydb.GET_DETAIL(String.valueOf(USER_id));
        food_delivery_adapter=new Food_Delivery_Adapter(this,list,USER_id);
        recyclerView.setAdapter(food_delivery_adapter);

    }
}