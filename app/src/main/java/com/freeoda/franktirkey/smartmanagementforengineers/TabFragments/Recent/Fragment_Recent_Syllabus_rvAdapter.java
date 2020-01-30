package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Recent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class Fragment_Recent_Syllabus_rvAdapter extends RecyclerView.Adapter<Fragment_Recent_Syllabus_rvAdapter.cViewHolder>{

    List<Fragment_Recent_Syllabus_rvModelClass> list;
    private ItemClickListner mClickListener;

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

    void setClickListner(ItemClickListner itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListner{
        void onItemClick(View view,int position);
    }

    class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSyllabus);

            itemView.setOnClickListener(this);
        }

        private void setData(String string){
            textView.setText(string);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
