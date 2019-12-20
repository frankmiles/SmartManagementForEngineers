package com.freeoda.franktirkey.smartmanagementforengineers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_Recent_Subject_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Subject_rvAdapter.cViewHolder> {

    private List<Fragment_Recent_Subject_rvModelClass> list;

    public Fragment_Recent_Subject_rvAdapter(List<Fragment_Recent_Subject_rvModelClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_subject_rv_layout,parent,false);


        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String string = list.get(position).getText();

        holder.setData(string);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class cViewHolder extends RecyclerView.ViewHolder{

        private TextView vhTextView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            vhTextView = itemView.findViewById(R.id.tvSubject);
        }

        private void setData(String string){
            vhTextView.setText(string);
        }
    }
}
