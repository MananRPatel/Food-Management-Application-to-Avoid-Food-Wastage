package com.mananhirak.annadata.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.SDETAIL;
import com.mananhirak.annadata.databases.SMYDB;

import java.util.List;
import java.util.Objects;

public class Signinact extends AppCompatActivity {

     int ACTIVITY_VALUE;
     EditText ed1,ed2,ed3,ed4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinact);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent get_intent=getIntent();
        ACTIVITY_VALUE=get_intent.getIntExtra("MAIN_ACTIVITY_VALUE",0);

        Log.d("MANANANAN","ACTIVITY VALUE "+ACTIVITY_VALUE);

        TextView textView=findViewById(R.id.signlogtext);
        ed1=findViewById(R.id.signuser);
        ed2=findViewById(R.id.signemail);
        ed3=findViewById(R.id.signpassword);
        ed4=findViewById(R.id.signuseraddress);


        Button button=findViewById(R.id.signsignbutton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1=ed1.getText().toString();
                String s2=ed2.getText().toString();
                String s3=ed3.getText().toString();
                String s4=ed4.getText().toString();

                if(s1.isEmpty()){
                    ed1.setError("Please Enter User Name..");
                    ed1.requestFocus();
                }else if(s2.isEmpty()){
                    ed2.setError("Please Enter Email..");
                    ed2.requestFocus();
                }else if(s3.isEmpty()){
                    ed3.setError("Please Enter Password..");
                    ed3.requestFocus();
                }else if(s4.isEmpty()){
                    ed4.setError("Please Enter Address..");
                    ed4.requestFocus();
                }else{
                    SDETAIL sdetail=new SDETAIL();
                    sdetail.setName(s1);
                    sdetail.setEmail(s2);
                    sdetail.setPassword(s3);
                    sdetail.setAddress(s4);

                    SMYDB db=new SMYDB(Signinact.this);
                    List<SDETAIL> list=db.S_GET_SECURITY();
                    boolean flag=true,email_flag=true;

                    for(SDETAIL sd:list){
                        if(s1.equals(sd.getName())) {
                            flag = false;
                            break;
                        }if(s2.equals(sd.getEmail())) {
                            email_flag = false;
                            break;
                        }
                    }

                    if(flag&&email_flag) {
                        db.S_ADD_SECURITY(sdetail);
                        USER_ID_PUTTER(db.USER_ID_GETTER(s1));


                        if (ACTIVITY_VALUE == 1) {
                            Intent intent = new Intent(Signinact.this, Profile.class);
                            startActivity(intent);
                            finish();
                        } else if (ACTIVITY_VALUE == 2) {
                            Intent intent = new Intent(Signinact.this, FoodRetail.class);
                            startActivity(intent);
                            finish();
                        } else if (ACTIVITY_VALUE == 3) {
                            Intent intent = new Intent(Signinact.this, FoodBuy.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Signinact.this, FoodDelivery.class);
                            startActivity(intent);
                            finish();
                        }
                    }else if(!flag){
                        ed1.setError("This User Name Already Used..");
                        ed1.requestFocus();
                    }else{
                        ed2.setError("This E-mail Already Used..");
                        ed2.requestFocus();
                    }
                }
            }
        });



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signinact.this,Loginact.class);
                intent.putExtra("MAIN_ACTIVITY_VALUE",ACTIVITY_VALUE);
                startActivity(intent);
                finish();
            }
        });
    }

    public void USER_ID_PUTTER(int id){
        SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("USER_ID_FOR_SECURITY",id);
        editor.apply();
    }

}