package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.cyclegroup.Food_Sell_Adapter;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;

import java.util.List;
import java.util.Objects;

public class FoodBuy extends AppCompatActivity {

    RecyclerView recyclerView;
    Food_Sell_Adapter foodSellAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_buy);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recyclerView=findViewById(R.id.fbrcycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        int USER_id =sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);
        Log.d("MANANANAN","USER ID IIS "+USER_id);

        RMYDB rmydb=new RMYDB(this);
        List<RDETAIL> list=rmydb.R_FOOD_SELL_GET_DATA(String.valueOf(USER_id));
        foodSellAdapter=new Food_Sell_Adapter(this,list,USER_id);
        recyclerView.setAdapter(foodSellAdapter);



    }
}