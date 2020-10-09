package com.mananhirak.annadata.cyclegroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.activitys.SellConform;
import com.mananhirak.annadata.databases.RDETAIL;

import java.util.List;

public class Food_Sell_Adapter extends RecyclerView.Adapter<Food_Sell_Adapter.ViewHolder> {

    public List<RDETAIL> list;
    public int USER_CURRENT;
    public Context context;

    public Food_Sell_Adapter(Context context,List<RDETAIL> list, int  USER_CURRENT) {
        this.context = context;
        this.list = list;
        this.USER_CURRENT = USER_CURRENT;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_sell_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s;
        RDETAIL rdetail =list.get(position);
        s="Food Name : "+rdetail.getFood_name();
        holder.t1.setText(s);
        if((rdetail.getFood_price())==0)
            s="For Donate Purpose";
        else
            s="Food price : "+rdetail.getFood_price();

        holder.t2.setText(s);
        s="Food Time : "+rdetail.getFood_time();
        holder.t3.setText(s);
        s="Food Date : "+rdetail.getFood_date();
        holder.t4.setText(s);
        s="Weight : "+rdetail.getFood_weight();
        holder.t5.setText(s);
        s="Order Left : "+rdetail.getFood_sold();
        holder.t6.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView t1,t2,t3,t4,t5,t6;

        public ViewHolder(View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.cartnamesell);
            t2=itemView.findViewById(R.id.cartpricesell);
            t3=itemView.findViewById(R.id.carttimesell);
            t4=itemView.findViewById(R.id.cartfooddate);
            t5=itemView.findViewById(R.id.cartweightsell);
            t6=itemView.findViewById(R.id.carttotalfoodleft);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            Log.d("MANANANAN","ID 12 IS "+list.get(getAdapterPosition()).getId());
            Log.d("MANANANAN","ID 121 IS "+list.get(getAdapterPosition()).getUser_id());
           Intent intent=new Intent(context, SellConform.class);
           intent.putExtra("USER_WHICH_BUY_THIS",USER_CURRENT);
           intent.putExtra("USER_WHICH_SELL_THIS_FOOD_ID",list.get(getAdapterPosition()).getId());
           context.startActivity(intent);
            ((Activity)context).finish();
        }
    }
}
