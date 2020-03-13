package com.freeoda.franktirkey.smartmanagementforengineers.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class Chat_rvAdapter extends RecyclerView.Adapter<Chat_rvAdapter.cViewaHolder> {

    private List<Chat_rvModel> chat_text;
    private interface_RecyclerViewClickListener_Chat irvcl;

    public Chat_rvAdapter(List<Chat_rvModel> chat_text,interface_RecyclerViewClickListener_Chat irvcl) {
        this.chat_text = chat_text;
        this.irvcl = irvcl;
    }

    @NonNull
    @Override
    public cViewaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_rv_layout,parent,false);

        return new cViewaHolder(view,irvcl);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewaHolder holder, int position) {

        String s = chat_text.get(position).getString();
        holder.setData(s);
    }

    @Override
    public int getItemCount() {
        return chat_text.size();
    }

    class cViewaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        private interface_RecyclerViewClickListener_Chat ocl;

        public cViewaHolder(@NonNull View itemView,interface_RecyclerViewClickListener_Chat ocl) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_rv_chat);
            itemView.setOnClickListener(this);
            this.ocl = ocl;
        }

        private void setData(String string){
            textView.setText(string);
        }


        @Override
        public void onClick(View view) {
            ocl.onClick(view,getAdapterPosition());
        }
    }
}
