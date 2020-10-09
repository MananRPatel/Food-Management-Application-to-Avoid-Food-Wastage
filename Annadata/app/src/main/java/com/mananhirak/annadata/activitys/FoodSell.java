package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;

import java.util.Calendar;
import java.util.Objects;

public class FoodSell extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button button,button1,button2;
    Calendar calendar;
    int USER_id;
    int Hour,Minute;
    String Food_Time;
    boolean Time_Flag;
    int Day,Month,Year;
    String Food_Date;
    boolean Date_Flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_sell);

        Objects.requireNonNull(getSupportActionBar()).hide();
        SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        USER_id =sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);
        Log.d("MANANANAN","USER ID IIS "+USER_id);


        ed1=findViewById(R.id.act_f_s_foodnamename);
        ed2=findViewById(R.id.act_f_s_foodweightname);
        ed3=findViewById(R.id.act_f_s_foodpricename);
        ed4=findViewById(R.id.act_f_s_total_foodname);
        ed5=findViewById(R.id.act_f_s_foodallinfoname);

        button=findViewById(R.id.act_f_s_submit);
        button1=findViewById(R.id.act_f_s_foodtimename);
        button2=findViewById(R.id.act_f_s_fooddatename);

        Calendar calendar2=Calendar.getInstance();

        calendar=Calendar.getInstance();
        Hour=calendar.get(Calendar.AM_PM)==Calendar.PM?calendar.get(Calendar.HOUR)+12:calendar.get(Calendar.HOUR);
        Minute=calendar.get(Calendar.MINUTE);

        calendar2.set(Calendar.HOUR,Hour%24);
        calendar2.set(Calendar.MINUTE,Minute);

        CharSequence charSequence= DateFormat.format("hh:mm a",calendar2);
        Food_Time=(String)charSequence;

        Day=calendar.get(Calendar.DATE);
        Month=calendar.get(Calendar.MONTH);
        Year=calendar.get(Calendar.YEAR);


        calendar2.set(Calendar.YEAR,Year);
        calendar2.set(Calendar.MONTH,Month);
        calendar2.set(Calendar.DATE,Day);
        CharSequence charSequence1= DateFormat.format("EEE, dd MMM  yyyy",calendar2);
        Food_Date=(String)charSequence1;

        Time_Flag=false;
        Date_Flag=true;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time_Create();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date_Create();
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=ed1.getText().toString();
                String s2=ed2.getText().toString();
                String s3=ed3.getText().toString();
                String s4=ed4.getText().toString();
                String s5=ed5.getText().toString();


                if(s1.isEmpty()){
                    ed1.setError("Please Enter Food Name..");
                    ed1.requestFocus();
                }else if(s2.isEmpty()){
                    ed2.setError("Please Enter Food Weight..");
                    ed2.requestFocus();
                }else if(s3.isEmpty()){
                    ed3.setError("Please Enter Food Value..");
                    ed3.requestFocus();
                }else if(s4.isEmpty()){
                    ed4.setError("Please Put Number Of Order");
                    ed4.requestFocus();
                } else if(s5.isEmpty()){
                    ed5.setError("Please Enter Food Information..");
                    ed5.requestFocus();
                }else if(Time_Flag&&Date_Flag){
                    Time_Create();
                    Toast.makeText(FoodSell.this,"Future Time"+Food_Time+"  "+Food_Date,Toast.LENGTH_SHORT).show();
                }else{

                    RDETAIL rdetail=new RDETAIL();
                    rdetail.setUser_id(USER_id);
                    rdetail.setFood_name(s1);
                    rdetail.setFood_weight(Integer.parseInt(s2));
                    rdetail.setFood_price(Integer.parseInt(s3));
                    rdetail.setFood_point(0);
                    rdetail.setFood_total(Integer.parseInt(s4));
                    rdetail.setFood_sold(Integer.parseInt(s4));
                    rdetail.setFood_all_info(s5);
                    rdetail.setFood_time(Food_Time);
                    rdetail.setFood_date(Food_Date);
                    Toast.makeText(FoodSell.this,Food_Date+"  "+Food_Time,Toast.LENGTH_SHORT).show();
                    RMYDB db=new RMYDB(FoodSell.this);
                    db.R_ADD_FOOD(rdetail);

                    Intent intent =new Intent(FoodSell.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });


    }

    public void Time_Create(){
        TimePickerDialog timePickerDialog=new TimePickerDialog(FoodSell.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Time_Flag= (Hour < i) || (Hour == i && Minute < i1);

                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.HOUR,i);
                calendar1.set(Calendar.MINUTE,i1);

                CharSequence charSequence= DateFormat.format("hh:mm a",calendar1);
                Food_Time=(String)charSequence;

            }
        },Hour,Minute,true);
        timePickerDialog.show();
    }

    public void Date_Create(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                Date_Flag=!(Day>i2);

                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.YEAR,i);
                calendar1.set(Calendar.MONTH,i1);
                calendar1.set(Calendar.DATE,i2);
                CharSequence charSequence= DateFormat.format("EEE, dd MMM  yyyy",calendar1);
                Food_Date=(String)charSequence;

            }
        },Year,Month,Day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000*60*60*24);
        datePickerDialog.show();

    }


}