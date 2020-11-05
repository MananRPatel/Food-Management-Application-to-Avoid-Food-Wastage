package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mananhirak.annadata.R;

import java.util.Objects;

public class UserHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);
        Objects.requireNonNull(getSupportActionBar()).hide();
   /*     CardView c1=findViewById(R.id.act_his_donate);
        CardView c2=findViewById(R.id.act_his_sell);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserHistory.this,HistoryDonate.class);
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserHistory.this,HistorySell.class);
                startActivity(intent);
            }
        });
*/
    }
}