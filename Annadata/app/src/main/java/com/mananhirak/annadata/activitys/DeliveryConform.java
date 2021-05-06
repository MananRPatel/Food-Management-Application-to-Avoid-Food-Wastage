package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.BMYDB;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;
import com.mananhirak.annadata.databases.SMYDB;

import java.util.Objects;

public class DeliveryConform extends AppCompatActivity {

    int CURRENT_USER_ID;
    int DELIVERY_ALL_INFO_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_conform);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent=getIntent();
        CURRENT_USER_ID=intent.getIntExtra("DELIVERY_BOY",0);
        DELIVERY_ALL_INFO_ID=intent.getIntExtra("DELIVER_SECTION_ID",0);

        TextView t1,t2,t3,t4,t5,t6,t7;

        t1=findViewById(R.id.adcfoodname);
        t2=findViewById(R.id.adcfoodweight);
        t3=findViewById(R.id.adctotalorder);
        t4=findViewById(R.id.adcsellerfoodinfo);
        t5=findViewById(R.id.adcdelivertime);
        t6=findViewById(R.id.adcbuyername);
        t7=findViewById(R.id.adcbuyeraddress);


        BMYDB bmydb=new BMYDB(this);
        BDETAIL bdetail=bmydb.B_DELIVERY_DETAIL(String.valueOf(DELIVERY_ALL_INFO_ID));

        RMYDB rmydb=new RMYDB(this);

        RDETAIL rdetail=rmydb.R_USER_ALL_INFORMATION(String.valueOf(bdetail.getSeller_id()));
        String s;
        s="Food : "+rdetail.getFood_name();
        t1.setText(s);

        s="Food Weight : "+rdetail.getFood_weight();
        t2.setText(s);

        s="No. Order :"+bdetail.getBuyer_order();
        t3.setText(s);

        s="Food Information : "+rdetail.getFood_all_info();
        t4.setText(s);

        s="Delivery Time : "+bdetail.getDelivery_time();
        t5.setText(s);

        SMYDB smydb=new SMYDB(this);
        s="Buyer : "+smydb.USER_NAME_GETTER(String.valueOf(bdetail.getBuyer_id()));
        t6.setText(s);

        s="To : "+bdetail.getBuyer_address();
        t7.setText(s);

        Button button=findViewById(R.id.adcconformbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMYDB db=new SMYDB(DeliveryConform.this);
                BMYDB bdb=new BMYDB(DeliveryConform.this);
                bdb.B_CONFORM_DELIVERY(String.valueOf(DELIVERY_ALL_INFO_ID));
                Toast.makeText(DeliveryConform.this,"Thank you "+db.USER_NAME_GETTER(String.valueOf(CURRENT_USER_ID)),Toast.LENGTH_LONG).show();
            }
        });

    }
}