package com.freeoda.franktirkey.smartmanagementforengineers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_Recent_LastChatGroup_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_LastChatGroup_rvAdapter.cViewHolder> {

    private List<Fragment_Recent_LastChatGroup_rvModelClass> list;

    public Fragment_Recent_LastChatGroup_rvAdapter(List<Fragment_Recent_LastChatGroup_rvModelClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_lastchatgroup_rv_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String string_LastChatGroup = list.get(position).getString();

        holder.setData(string_LastChatGroup);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class cViewHolder extends RecyclerView.ViewHolder {

        TextView tvLastChatGroup;
        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLastChatGroup = itemView.findViewById(R.id.tvLastgroupchat);
        }

        private void setData(String string){
            tvLastChatGroup.setText(string);
        }
    }
}
