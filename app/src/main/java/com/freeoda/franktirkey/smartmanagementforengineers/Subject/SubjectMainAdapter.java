package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class SubjectMainAdapter extends RecyclerView.Adapter<SubjectMainAdapter.cViewModel>{
    List<SubjectMainModel> list;

    public SubjectMainAdapter(List<SubjectMainModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public cViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_main_rv_layout,parent,false);

        return new cViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewModel holder, int position) {

        holder.tv_subject_main_rv_layout.setText(list.get(position).subjName);
        holder.iv_subject_main_rv_layout.setImageResource(list.get(position).subjRes);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class cViewModel extends RecyclerView.ViewHolder {

        TextView tv_subject_main_rv_layout;
        ImageView iv_subject_main_rv_layout;


        public cViewModel(@NonNull View itemView) {
            super(itemView);
            tv_subject_main_rv_layout = itemView.findViewById(R.id.tv_subject_main_rv_layout);
            iv_subject_main_rv_layout = itemView.findViewById(R.id.iv_subject_main_rv_layout);

        }

        public void saveData(String str,int res){
            tv_subject_main_rv_layout.setText(str);
            iv_subject_main_rv_layout.setImageResource(res);
        }
    }
}
