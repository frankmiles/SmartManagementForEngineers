package com.freeoda.franktirkey.smartmanagementforengineers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_Recent_Syllabus_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Syllabus_rvAdapter.cViewHolder>{

    List<Fragment_Recent_Syllabus_rvModelClass> list;

    public Fragment_Recent_Syllabus_rvAdapter(List<Fragment_Recent_Syllabus_rvModelClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_syllabus_rv_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String string = list.get(position).getString();

        holder.setData(string);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSyllabus);
        }

        private void setData(String string){
            textView.setText(string);
        }
    }
}
