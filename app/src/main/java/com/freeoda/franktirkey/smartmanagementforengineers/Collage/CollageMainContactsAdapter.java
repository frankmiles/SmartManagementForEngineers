package com.freeoda.franktirkey.smartmanagementforengineers.Collage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.io.InterruptedIOException;
import java.util.List;

public class CollageMainContactsAdapter extends RecyclerView.Adapter<CollageMainContactsAdapter.cViewHolder> {

    List<String> list;
    Context context;
    String domain;

    public CollageMainContactsAdapter(List<String> list, Context context,String domain) {
        this.list = list;
        this.context = context;
        this.domain = domain;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collage_main_contact_rv_model_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder {

        TextView tv_contact;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_contact = itemView.findViewById(R.id.tv_contact);
            tv_contact.setOnClickListener(CollageMain.listener);
        }

        private void setData(String data){
            if(domain.equals("Faculty")){
                tv_contact.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }else if(domain.equals("Accommodation")){
                tv_contact.setTextColor(context.getResources().getColor(R.color.colorCardPrimary));
            }
            tv_contact.setText(data);
        }
    }
}
