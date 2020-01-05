package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.Random;

public class Fragment_Plan_main_rvAdapter extends RecyclerView.Adapter<Fragment_Plan_main_rvAdapter.cViewHolder>{

    private LayoutInflater layoutInflater;
    private ArrayList<Fragment_Plan_Main_rvModelClass> list;

    public Fragment_Plan_main_rvAdapter(Context context, ArrayList<Fragment_Plan_Main_rvModelClass> list){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());
    }

    public void restoreItem(Fragment_Plan_Main_rvModelClass model, int position) {
        list.add(position,model);
        // notify item added by position
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_plan_main_rv_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.tvPlan_rvLayout.setTextColor(color);

        holder.tvPlan_rvLayout.setText(list.get(position).getString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class cViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlan_rvLayout;


        public cViewHolder(View itemView) {
            super(itemView);

            tvPlan_rvLayout = (TextView) itemView.findViewById(R.id.tvPlan_rvLayout);

        }

    }
}
