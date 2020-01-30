package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Plan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;
import java.util.Random;

public class Fragment_Plan_main_rvAdapter extends RecyclerView.Adapter<Fragment_Plan_main_rvAdapter.cViewHolder>{

    private LayoutInflater layoutInflater;
    private List<String> list;

    public Fragment_Plan_main_rvAdapter(Context context,@NonNull List<String> list){
        layoutInflater = LayoutInflater.from(context);
        if(list == null){

        }
        else{
            this.list = list;
        }
    }

    public void removeItem(final int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String model, int position) {
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

        holder.tvPlan_rvLayout.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    class cViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlan_rvLayout;


        public cViewHolder(View itemView) {
            super(itemView);

            tvPlan_rvLayout = (TextView) itemView.findViewById(R.id.tvPlan_rvLayout);

        }

    }
}
