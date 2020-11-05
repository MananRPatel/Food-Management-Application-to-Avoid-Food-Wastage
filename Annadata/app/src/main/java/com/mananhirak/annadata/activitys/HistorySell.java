package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.mananhirak.annadata.R;
import com.mananhirak.annadata.cyclegroup.Food_Delivery_Adapter;
import com.mananhirak.annadata.cyclegroup.History_Sell_Adapter;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.BMYDB;
import com.mananhirak.annadata.databases.RMYDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistorySell extends AppCompatActivity {

    RecyclerView recyclerView;
    History_Sell_Adapter historySellAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_sell);
        Objects.requireNonNull(getSupportActionBar()).hide();

      /*  SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        int USER_id=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);

        recyclerView=findViewById(R.id.act_his_sell_cycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BMYDB bmydb=new BMYDB(this);

        List<BDETAIL> l=bmydb.GET_HISTORY_DETAIL(String.valueOf(USER_id));

        RMYDB rmydb=new RMYDB(this);
        List<BDETAIL> final_list=rmydb.R_USER_PRICE_GETTER(String.valueOf(USER_id),l);




        historySellAdapter=new History_Sell_Adapter(final_list,this);
        recyclerView.setAdapter(historySellAdapter);
*/
    }
}