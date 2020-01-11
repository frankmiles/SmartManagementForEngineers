package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Fragment_Home_Main_rvAdapter;

import java.util.List;

public class SyllabusMainAdaper extends RecyclerView.Adapter<SyllabusMainAdaper.cViewHolder>{

    List<SyllabusMainModel> list;
    interface_RecyclerViewClickListner_Syllabus irvcl;

    public SyllabusMainAdaper(List<SyllabusMainModel> list,interface_RecyclerViewClickListner_Syllabus irvcl) {
        this.list = list;
        this.irvcl = irvcl;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabus_main_rv_item_layout,parent,false);
        return new cViewHolder(view,irvcl);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        holder.setData(list.get(position).subjName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv;
        interface_RecyclerViewClickListner_Syllabus ocl;
        public cViewHolder(@NonNull View itemView,interface_RecyclerViewClickListner_Syllabus ocl) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_rv_item_syllabus);
            itemView.setOnClickListener(this);
            this.ocl = ocl;
        }

        public void setData(String str){
            tv.setText(str);
        }

        @Override
        public void onClick(View view) {
            ocl.onClick(view,getAdapterPosition());
        }
    }
}
