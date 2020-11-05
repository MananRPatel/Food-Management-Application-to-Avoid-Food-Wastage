package com.mananhirak.annadata.cyclegroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mananhirak.annadata.R;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;
import com.mananhirak.annadata.databases.SDETAIL;
import com.mananhirak.annadata.databases.SMYDB;

import java.util.List;

public class History_Sell_Adapter extends RecyclerView.Adapter<History_Sell_Adapter.ViewHolder> {

    List<BDETAIL> list;
    Context context;

    public History_Sell_Adapter(List<BDETAIL> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.historycardsell,parent,false);
        return new History_Sell_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BDETAIL bdetail=list.get(position);

        SDETAIL sdetail=new SDETAIL();

        SMYDB smydb=new SMYDB(context);
        RMYDB rmydb=new RMYDB(context);
        RDETAIL rdetail=rmydb.R_USER_ALL_INFORMATION(String.valueOf(bdetail.getSeller_id()));

        holder.t1.setText(("Buyer : "+smydb.USER_NAME_GETTER(String.valueOf(bdetail.getBuyer_id()))));
        holder.t2.setText(("Price : "+rdetail.getFood_price()));
        holder.t3.setText(("Order : "+bdetail.getBuyer_order()));
        holder.t4.setText(("Food Name :"+rdetail.getFood_name()));
        holder.t5.setText(("Food Info : "+rdetail.getFood_all_info()));
        holder.t6.setText(("Food Time : "+bdetail.getDelivery_time()));


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView t1,t2,t3,t4,t5,t6;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.card_sell_buyer);
            t2=itemView.findViewById(R.id.card_sell_price);
            t3=itemView.findViewById(R.id.card_sell_order);
            t4=itemView.findViewById(R.id.card_sell_foodname);
            t5=itemView.findViewById(R.id.card_sell_info);
            t6=itemView.findViewById(R.id.card_sell_time);

        }
    }
}
