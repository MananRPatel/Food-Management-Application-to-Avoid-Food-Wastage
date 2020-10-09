package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.BMYDB;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;
import com.mananhirak.annadata.databases.SMYDB;

import java.util.Calendar;
import java.util.Objects;


public class SellConform extends AppCompatActivity {

    EditText ed1,ed2;
    SMYDB smydb;
    BMYDB bmydb;
    RMYDB rmydb;
    RDETAIL rdetail;
    int USER_CURRENT;
    int USER_SELLER_ALL_FOOD_ID;
    boolean Time_Flag;
    String Food_time;
    int Hour,Minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_conform);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent get_intent=getIntent();
        USER_CURRENT=get_intent.getIntExtra("USER_WHICH_BUY_THIS",0);
        USER_SELLER_ALL_FOOD_ID=get_intent.getIntExtra("USER_WHICH_SELL_THIS_FOOD_ID",0);

        rmydb=new RMYDB(this);
        smydb=new SMYDB(this);

        TextView t1,t2,t3,t4,t5,t6,t7;

        t1=findViewById(R.id.scusername);
        t2=findViewById(R.id.scfoodname);
        t3=findViewById(R.id.scfoodvalue);
        t4=findViewById(R.id.scfoodweight);
        t5=findViewById(R.id.scfoodtime);
        t6=findViewById(R.id.scfooddate);
        t7=findViewById(R.id.scfoodleft);

        ed1=findViewById(R.id.sccurrentaddress);
        ed2=findViewById(R.id.scnumberoforder);

        rdetail=rmydb.R_USER_ALL_INFORMATION(String.valueOf(USER_SELLER_ALL_FOOD_ID));
        String s;
        s="User : "+smydb.USER_NAME_GETTER(String.valueOf(rdetail.getUser_id()));
        t1.setText(s);
        s="Food Name : "+rdetail.getFood_name();
        t2.setText(s);
        if(rdetail.getFood_price()==0){
            s="Food For Donation Purpose";
        }else{
            s="Food Price : "+rdetail.getFood_price();
        }
        t3.setText(s);
        s="Food Weight : "+rdetail.getFood_weight();
        t4.setText(s);
        s="Food Time : "+rdetail.getFood_time();
        t5.setText(s);
        s="Food Date : "+rdetail.getFood_date();
        t6.setText(s);
        s="Order Left : "+rdetail.getFood_sold();
        t7.setText(s);

        bmydb =new BMYDB(SellConform.this);

        Calendar calendar=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();

        Hour=calendar.get(Calendar.AM_PM)==Calendar.PM?calendar.get(Calendar.HOUR)+12:calendar.get(Calendar.HOUR);
        Minute=calendar.get(Calendar.MINUTE);

        calendar2.set(Calendar.HOUR,Hour%24);
        calendar2.set(Calendar.MINUTE,Minute);

        CharSequence charSequence= DateFormat.format("hh:mm a",calendar2);
        Food_time=(String)charSequence;
        Time_Flag=false;

        Button button1=findViewById(R.id.scordertime);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time_Create();
                Toast.makeText(SellConform.this,Food_time,Toast.LENGTH_SHORT).show();
            }
        });


        Button button=findViewById(R.id.scconformbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1,s2;
                s1=ed1.getText().toString();
                s2=ed2.getText().toString();
                if(s1.isEmpty()){
                    ed1.setError("Please Put Address..");
                    ed1.requestFocus();
                }else if(s2.isEmpty()){
                  ed2.setError("Please Put Number Of Order");
                  ed2.requestFocus();
                }else if((rdetail.getFood_sold()-Integer.parseInt(s2))<0){
                    ed2.setError("Sorry Not Much Order  ");
                    ed2.requestFocus();
                }else if(Time_Flag){
                    Time_Create();
                    Toast.makeText(SellConform.this,"Old Time "+Food_time,Toast.LENGTH_SHORT).show();

                }else{
                    rmydb.R_USER_ORDER_SOLD(String.valueOf(USER_SELLER_ALL_FOOD_ID),rdetail.getFood_sold()-Integer.parseInt(s2));

                    BDETAIL bdetail=new BDETAIL();
                    bdetail.setBuyer_address(s1);
                    bdetail.setBuyer_id(USER_CURRENT);
                    bdetail.setSeller_id(USER_SELLER_ALL_FOOD_ID);
                    bdetail.setBuyer_order(Integer.parseInt(s2));
                    bdetail.setDelivery_boy(rmydb.R_USER_ALL_INFORMATION(String.valueOf(USER_SELLER_ALL_FOOD_ID)).getUser_id());
                    bdetail.setDelivery_time(Food_time);
                    bmydb.ADD_DETAIL(bdetail);
                    Toast.makeText(SellConform.this,Food_time,Toast.LENGTH_SHORT).show();

                    Toast.makeText(SellConform.this,"Thank You For Buying Food "+smydb.USER_NAME_GETTER(String.valueOf(USER_CURRENT)),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SellConform.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void Time_Create(){

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Time_Flag=(i<Hour)||(i==Hour&&i1<Minute);
                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR,i);
                calendar1.set(Calendar.MINUTE,i1);

                CharSequence charSequence= DateFormat.format("hh:mm a",calendar1);
                Food_time=(String)charSequence;

            }
        },Hour,Minute,true);
        timePickerDialog.show();
    }

}