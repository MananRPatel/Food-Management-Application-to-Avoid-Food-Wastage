package com.mananhirak.annadata.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.RMYDB;
import com.mananhirak.annadata.databases.SMYDB;
import java.util.Objects;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SharedPreferences sharedPreferences=getSharedPreferences("SECURITY",MODE_PRIVATE);
        int USER_id=sharedPreferences.getInt("USER_ID_FOR_SECURITY",-1);
        Log.d("MANANANAN","USER ID IIS "+USER_id);



        TextView t1=findViewById(R.id.procartmoney);
        TextView t2=findViewById(R.id.prodonate);
        TextView t3=findViewById(R.id.prouser);
        ImageView imageView=findViewById(R.id.prohistorybuttton);
        RMYDB db=new RMYDB(this);
        String s1="₹"+ db.R_FOOD_PRICE(String.valueOf(USER_id));
        String s2=String.valueOf(db.R_FOOD_POINT(String.valueOf(USER_id)));
        t1.setText(s1);
        t2.setText(s2);


        SMYDB smydb=new SMYDB(this);
        t3.setText(smydb.USER_NAME_GETTER(String.valueOf(USER_id)));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Profile.this,UserHistory.class);
                startActivity(intent);
            }
        });

        Button b=findViewById(R.id.prologoutb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreference_1=getSharedPreferences("SECURITY",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedpreference_1.edit();
                editor.remove("USER_ID_FOR_SECURITY");
                editor.apply();
                Intent intent =new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}