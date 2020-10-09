package com.mananhirak.annadata.cyclegroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mananhirak.annadata.R;
import com.mananhirak.annadata.activitys.DeliveryConform;
import com.mananhirak.annadata.databases.BDETAIL;
import com.mananhirak.annadata.databases.RDETAIL;
import com.mananhirak.annadata.databases.RMYDB;
import com.mananhirak.annadata.databases.SMYDB;

import java.util.List;

public class Food_Delivery_Adapter extends RecyclerView.Adapter<Food_Delivery_Adapter.ViewHolder> {

    Context context;
    List<BDETAIL> list;
    int USER_CURRENT;

    public Food_Delivery_Adapter(Context context, List<BDETAIL> list, int USER_CURRENT) {
        this.context = context;
        this.list = list;
        this.USER_CURRENT = USER_CURRENT;
    }


    @NonNull
    @Override
    public Food_Delivery_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_delivery_card,parent,false);
        return new Food_Delivery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Food_Delivery_Adapter.ViewHolder holder, int position) {
        String s;
        BDETAIL bdetail=list.get(position);
        RMYDB rmydb=new RMYDB(context);
        SMYDB smydb=new SMYDB(context);
        RDETAIL rdetail=rmydb.R_USER_ALL_INFORMATION(String.valueOf(bdetail.getSeller_id()));

        s="Food Name : "+rdetail.getFood_name();
        holder.t1.setText(s);
        s="Buyer : "+smydb.USER_NAME_GETTER(String.valueOf(bdetail.getBuyer_id()));
        holder.t2.setText(s);
        s="TO : "+bdetail.getBuyer_address();
        holder.t3.setText(s);
        s="Order Took : "+bdetail.getBuyer_order();
        holder.t4.setText(s);
        s="Order Time : "+bdetail.getDelivery_time();
        holder.t5.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView t1,t2,t3,t4,t5;
        public ViewHolder(View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.fdcfoodname);
            t2=itemView.findViewById(R.id.fdcbuyername);
            t3=itemView.findViewById(R.id.fdcbuyeraddress);
            t4=itemView.findViewById(R.id.fdcordertake);
            t5=itemView.findViewById(R.id.fdcordertime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent =new Intent(context, DeliveryConform.class);
            intent.putExtra("DELIVER_SECTION_ID",list.get(getAdapterPosition()).getId());
            intent.putExtra("DELIVERY_BOY",USER_CURRENT);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }
}
