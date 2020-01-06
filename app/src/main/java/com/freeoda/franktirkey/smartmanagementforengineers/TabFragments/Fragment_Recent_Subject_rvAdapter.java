package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class Fragment_Recent_Subject_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Subject_rvAdapter.cViewHolder> {

    private List<Fragment_Recent_Subject_rvModelClass> list;
    private ItemClickListener mClickListener;

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

    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView vhTextView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            vhTextView = itemView.findViewById(R.id.tvSubject);
            itemView.setOnClickListener(this);
        }

        private void setData(String string){
            vhTextView.setText(string);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) mClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
