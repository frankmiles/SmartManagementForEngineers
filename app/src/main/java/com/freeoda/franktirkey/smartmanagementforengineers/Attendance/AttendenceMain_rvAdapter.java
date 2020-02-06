package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance.AttendanceAppDB;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendenceMain_rvAdapter extends RecyclerView.Adapter<AttendenceMain_rvAdapter.cViewHolder> {

    private List<AttendanceMain_rvModel> tempList;
    private LayoutInflater mInflater;

    public AttendenceMain_rvAdapter(Context context, List<AttendanceMain_rvModel> list) {
        mInflater = LayoutInflater.from(context);
        this.tempList = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.attendance_main_rv_layout,parent,false);
        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {

        String SubjectName, teacher;
        int persent, absent;
        MaterialDayPicker.Weekday Day;

        SubjectName = tempList.get(position).getSubject();
        teacher = tempList.get(position).getTeacher();
        persent = tempList.get(position).getPresent();
        absent = tempList.get(position).getAbsent();
        Day = tempList.get(position).getDay();

        holder.setData(SubjectName, teacher, persent, absent, Day);
    }

    @Override
    public int getItemCount() {
        return tempList.size();
    }

    public class cViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_percen_rv_attendance,tv_subj_rv_attendance,tv_teacher_rv_attendance,
                tv_present,tv_absent;
        Button btn_atten_up,btn_atten_down;

        String SubjectName, teacher;
        int persent, absent;
        MaterialDayPicker.Weekday Day;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_percen_rv_attendance = itemView.findViewById(R.id.tv_percen_rv_attendance);
            this.tv_subj_rv_attendance = itemView.findViewById(R.id.tv_subj_rv_attendance);
            this.tv_teacher_rv_attendance = itemView.findViewById(R.id.tv_teacher_rv_attendance);
            this.tv_present = itemView.findViewById(R.id.tv_present);
            this.tv_absent = itemView.findViewById(R.id.tv_absent);
            this.btn_atten_up = itemView.findViewById(R.id.btn_atten_up);
            this.btn_atten_down = itemView.findViewById(R.id.btn_atten_down);

            btn_atten_up.setOnClickListener(this);
            btn_atten_down.setOnClickListener(this);
        }

        public void setData(String SubjectName, String teacher, int persent, int absent,
                            MaterialDayPicker.Weekday Day){

            this.SubjectName = SubjectName;
            this.teacher = teacher;
            this.persent = persent;
            this.absent = absent;
            this.Day = Day;

            int total = persent+absent;
            int per = 0;
            try {
                per = (persent*100)/total;
            }catch (Exception e){
                e.printStackTrace();
            }

            tv_percen_rv_attendance.setText(String.valueOf(per)+"%");
            tv_subj_rv_attendance.setText(SubjectName);
            tv_teacher_rv_attendance.setText(teacher);
            tv_present.setText(String.valueOf(persent));
            tv_absent.setText(String.valueOf(absent));

        }

        @Override
        public void onClick(View view) {
            Log.d("msg","got Posotion: "+getLayoutPosition());

            if(view == btn_atten_up){
                tempList.get(getLayoutPosition()).setPresent(persent + 1);
                AttendanceMain.subjectList.get(getLayoutPosition()).setPresent(persent+1);
            }
            else{
                tempList.get(getLayoutPosition()).setAbsent(absent + 1);
                AttendanceMain.subjectList.get(getLayoutPosition()).setPresent(absent+1);
            }

            notifyDataSetChanged();
        }
    }
}