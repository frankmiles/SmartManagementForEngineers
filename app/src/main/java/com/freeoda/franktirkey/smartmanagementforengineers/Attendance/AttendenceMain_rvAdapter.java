package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendenceMain_rvAdapter extends RecyclerView.Adapter<AttendenceMain_rvAdapter.cViewHolder> implements Filterable {

    List<AttendanceMain_rvModel> list;
    List<AttendanceMain_rvModel> listFiltered = new ArrayList<>();
    List<MaterialDayPicker.Weekday> days;

    TextView totalPercentageView;

    public void setDays(List<MaterialDayPicker.Weekday> days) {
        this.days = days;
    }

    public AttendenceMain_rvAdapter(List<AttendanceMain_rvModel> list,TextView view) {
        this.totalPercentageView = view;
        this.list = list;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_main_rv_layout,parent,false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cViewHolder holder, int position) {
        String name = listFiltered.get(position).getName();
        String proff = listFiltered.get(position).getProff();
        int present = listFiltered.get(position).getPresent();
        int absent = listFiltered.get(position).getAbsent();

        holder.setData(name,proff,present,absent);
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                // String charString = charSequence.toString().trim();
                listFiltered.clear();
                if(days.isEmpty()){
                    listFiltered = list;
                }else {
                    List<AttendanceMain_rvModel> filteredList = new ArrayList<>();
                    for(AttendanceMain_rvModel model:list){//per list item
                        for(MaterialDayPicker.Weekday wd:model.getWeekdays()){ //per day of model
                            for(MaterialDayPicker.Weekday dd: days){ //per day of querry
                                if(wd.equals(dd)) //days in model ?have? querry day
                                    filteredList.add(model); //add model to filtered list
                            }
                        }

                    }
                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (List<AttendanceMain_rvModel>) filterResults.values;
                notifyDataSetChanged();
                setTotalPercentage();
            }
        };
    }

    private void setTotalPercentage(){
        int totalPresent = 0;
        int totalAbsent = 0;
        float totalPercentage = 0;
        for(AttendanceMain_rvModel model:list){
            totalPresent = totalPresent + model.getPresent();
            totalAbsent = totalAbsent + model.getAbsent();
        }
        int totalClass = totalAbsent + totalPresent;
        totalPercentage = (totalPresent * 100)/totalClass;

        totalPercentageView.setText(String.valueOf(totalPercentage).concat("%"));
    }

    class cViewHolder extends RecyclerView.ViewHolder {

        //Model Layout
        TextView tv_Perce, tv_subj, tv_teacher, tv_present, tv_absent;
        Button btn_atten_up, btn_atten_down;
        ImageView iv_present_minus,iv_absent_minus;

        public cViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Perce = itemView.findViewById(R.id.tv_percen_rv_attendance);
            tv_subj = itemView.findViewById(R.id.tv_subj_rv_attendance);
            tv_teacher = itemView.findViewById(R.id.tv_teacher_rv_attendance);
            tv_present = itemView.findViewById(R.id.tv_present);
            tv_absent = itemView.findViewById(R.id.tv_absent);
            btn_atten_up = itemView.findViewById(R.id.btn_atten_up);
            btn_atten_down = itemView.findViewById(R.id.btn_atten_down);
            iv_present_minus = itemView.findViewById(R.id.iv_present_minus);
            iv_absent_minus = itemView.findViewById(R.id.iv_absent_minus);

            btn_atten_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pres = list.get(getAdapterPosition()).getPresent(); //TODO notWorking well
                    pres = pres + 1;
                    list.get(getAdapterPosition()).setPresent(pres);
                    tv_present.setText(String.valueOf(list.get(getAdapterPosition()).getPresent()));
                    tv_Perce.setText(String.valueOf(
                            calculatePerce(
                                    list.get(getAdapterPosition()).getPresent(),
                                    listFiltered.get(getAdapterPosition()).getAbsent()))
                            .concat("%"));
                    setTotalPercentage();
                    Log.d("msg",String.valueOf(list.get(getAdapterPosition()).getName())
                            .concat("present->")
                            .concat(String.valueOf(list.get(getAdapterPosition()).getPresent())));
                }
            });

            btn_atten_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int absens = list.get(getAdapterPosition()).getAbsent(); //TODO notWorking well
                    absens = absens + 1;
                    list.get(getAdapterPosition()).setAbsent(absens);
                    tv_absent.setText(String.valueOf(list.get(getAdapterPosition()).getAbsent()));
                    tv_Perce.setText(String.valueOf(
                            calculatePerce(
                                    list.get(getAdapterPosition()).getPresent(),
                                    listFiltered.get(getAdapterPosition()).getAbsent()))
                            .concat("%"));
                    setTotalPercentage();
                    Log.d("msg",String.valueOf(list.get(getAdapterPosition()).getProff())
                            .concat("absent->")
                            .concat(String.valueOf(list.get(getAdapterPosition()).getAbsent())));
                }
            });

            iv_present_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pres = list.get(getAdapterPosition()).getPresent();
                    if(!(pres<=0)){
                        pres = pres - 1;
                        list.get(getAdapterPosition()).setPresent(pres);
                        tv_present.setText(String.valueOf(list.get(getAdapterPosition()).getPresent()));
                        tv_Perce.setText(String.valueOf(
                                calculatePerce(
                                        list.get(getAdapterPosition()).getPresent(),
                                        listFiltered.get(getAdapterPosition()).getAbsent()))
                                .concat("%"));
                    }
                }
            });

            iv_absent_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int absens = list.get(getAdapterPosition()).getAbsent();
                    if(!(absens<=0)){
                        absens = absens - 1;
                        list.get(getAdapterPosition()).setAbsent(absens);
                        tv_absent.setText(String.valueOf(list.get(getAdapterPosition()).getAbsent()));
                        tv_Perce.setText(String.valueOf(
                                calculatePerce(
                                        list.get(getAdapterPosition()).getPresent(),
                                        listFiltered.get(getAdapterPosition()).getAbsent()))
                                .concat("%"));
                    }
                }
            });
        }

        public void setData(String Subject,String preff,int present,int absent){

            tv_subj.setText(Subject);
            tv_teacher.setText(preff);
            tv_present.setText(String.valueOf(present));
            tv_absent.setText(String.valueOf(absent));
            tv_Perce.setText(String.valueOf((int)calculatePerce(present,absent)).concat("%"));

        }

        private int calculatePerce(int present,int absent){
            int total = present + absent;
            try{
                return ((present*100)/total);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }
    }
}
