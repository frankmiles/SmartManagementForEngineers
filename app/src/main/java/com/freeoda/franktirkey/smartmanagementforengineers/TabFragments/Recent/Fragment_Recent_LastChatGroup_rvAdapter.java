package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Recent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class Fragment_Recent_LastChatGroup_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_LastChatGroup_rvAdapter.cViewHolder> {

    private List<Fragment_Recent_LastChatGroup_rvModelClass> list;
    private ItemClickListener mClickListener;

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

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvLastChatGroup;
        private cViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLastChatGroup = itemView.findViewById(R.id.tvLastgroupchat);

            itemView.setOnClickListener(this);
        }

        private void setData(String string){
            tvLastChatGroup.setText(string);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
