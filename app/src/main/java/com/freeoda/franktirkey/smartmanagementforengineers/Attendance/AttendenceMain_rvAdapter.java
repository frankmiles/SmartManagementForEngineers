package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class AttendenceMain_rvAdapter extends RecyclerView.Adapter<AttendenceMain_rvAdapter.cViewHolder> {

    private List<AttendenceMain_rvModel> list;
    private LayoutInflater mInflater;

    public AttendenceMain_rvAdapter(Context context, List<AttendenceMain_rvModel> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.attendance_main_rv_layout,parent,false);
        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        holder.setData(list.get(position).getPercentage(),list.get(position).getSubject(),list.get(position).getTeacher());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class cViewHolder extends RecyclerView.ViewHolder{

        TextView tv_percen_rv_attendance,tv_subj_rv_attendance,tv_teacher_rv_attendance;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_percen_rv_attendance = itemView.findViewById(R.id.tv_percen_rv_attendance);
            tv_subj_rv_attendance = itemView.findViewById(R.id.tv_subj_rv_attendance);
            tv_teacher_rv_attendance = itemView.findViewById(R.id.tv_teacher_rv_attendance);
        }

        public void setData(int percentage,String SubjectName,String teacher){
            tv_percen_rv_attendance.setText(percentage+"%");
            tv_subj_rv_attendance.setText(SubjectName);
            tv_teacher_rv_attendance.setText(teacher);
        }
    }
}
