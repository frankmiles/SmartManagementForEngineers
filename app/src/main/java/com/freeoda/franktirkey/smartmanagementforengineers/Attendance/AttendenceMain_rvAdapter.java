package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freeoda.franktirkey.smartmanagementforengineers.MainActivity;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendenceMain_rvAdapter extends RecyclerView.Adapter<AttendenceMain_rvAdapter.cViewHolder> implements Filterable {

    List<AttendanceMain_rvModel> list;
    List<AttendanceMain_rvModel> listFiltered = new ArrayList<>();
    List<MaterialDayPicker.Weekday> days;

    TextView totalPercentageView;

    FloatingActionButton fb_attendenceAddSubject;

    EditText et_subjNameAdd, et_subjProffAdd;
    MaterialDayPicker day_picker_Add;
    Button btn_SaveAdd;

    public void setDays(List<MaterialDayPicker.Weekday> days) {
        this.days = days;
    }

    public AttendenceMain_rvAdapter(List<AttendanceMain_rvModel> list,TextView view,FloatingActionButton fb_attendenceAddSubject) {
        this.totalPercentageView = view;
        this.list = list;
        this.fb_attendenceAddSubject =fb_attendenceAddSubject;
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
        try {
            totalPercentage = (totalPresent * 100)/totalClass;
        }catch (Exception e){
            totalPercentage = 0;
            e.printStackTrace();
        }

        totalPercentageView.setText(String.valueOf(totalPercentage).concat("%"));
    }

    public void setEt_subjNameAdd(EditText et_subjNameAdd) {
        this.et_subjNameAdd = et_subjNameAdd;
    }

    public void setEt_subjProffAdd(EditText et_subjProffAdd) {
        this.et_subjProffAdd = et_subjProffAdd;
    }

    public void setDay_picker_Add(MaterialDayPicker day_picker_Add) {
        this.day_picker_Add = day_picker_Add;
    }

    public void setBtn_SaveAdd(Button btn_SaveAdd) {
        this.btn_SaveAdd = btn_SaveAdd;
    }

    public List<AttendanceMain_rvModel> getListFiltered() {
        return listFiltered;
    }

    class cViewHolder extends RecyclerView.ViewHolder {

        //Model Layout
        TextView tv_Perce, tv_subj, tv_teacher, tv_present, tv_absent;
        Button btn_atten_up, btn_atten_down,btn_edit_attendance_subject,btn_rv_delete;
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
            btn_edit_attendance_subject = itemView.findViewById(R.id.btn_edit_attendance_subject);
            btn_rv_delete = itemView.findViewById(R.id.btn_rv_delete);
            iv_present_minus = itemView.findViewById(R.id.iv_present_minus);
            iv_absent_minus = itemView.findViewById(R.id.iv_absent_minus);

            btn_rv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(listFiltered.get(getAdapterPosition()));
                    listFiltered.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            btn_edit_attendance_subject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fb_attendenceAddSubject.performClick();

                    Log.d("msg",String.valueOf(listFiltered.get(getAdapterPosition()).getName()));

                    setEditFields();
                    deleteFromClickedPosition();
                    AttendanceMain.getAdapterClickedPosition = getAdapterPosition();
                    AttendanceMain.editBtnClicked = true;
                    notifyDataSetChanged();
                }
            });

            btn_atten_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String FilterName = listFiltered.get(getAdapterPosition()).getName();
                    String FilterProff = listFiltered.get(getAdapterPosition()).getProff();
                    if(list.contains(listFiltered.get(getAdapterPosition()))){
                        for(int i = 0;i<list.size();i++){
                            if(list.get(i).getName().equals(FilterName)  && list.get(i).getProff().equals(FilterProff)){
                                list.get(i).setPresent(list.get(i).getPresent()+1);
                                tv_present.setText(String.valueOf(list.get(i).getPresent()));
                                tv_Perce.setText(String.valueOf(calculatePerce(list.get(i).getPresent(),list.get(i).getAbsent())));
                                setTotalPercentage();
                            }
                        }
                    }
                }
            });

            btn_atten_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String FilterName = listFiltered.get(getAdapterPosition()).getName();
                    String FilterProff = listFiltered.get(getAdapterPosition()).getProff();
                    if(list.contains(listFiltered.get(getAdapterPosition()))){
                        for(int i = 0;i<list.size();i++){
                            if(list.get(i).getName().equals(FilterName)  && list.get(i).getProff().equals(FilterProff)){
                                list.get(i).setAbsent(list.get(i).getAbsent()+1);
                                tv_absent.setText(String.valueOf(list.get(i).getAbsent()));
                                tv_Perce.setText(String.valueOf(calculatePerce(list.get(i).getPresent(),list.get(i).getAbsent())));
                                setTotalPercentage();
                            }
                        }
                    }

                }
            });

            iv_present_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String FilterName = listFiltered.get(getAdapterPosition()).getName();
                    String FilterProff = listFiltered.get(getAdapterPosition()).getProff();
                    if(list.contains(listFiltered.get(getAdapterPosition()))){
                        for(int i = 0;i<list.size();i++){
                            if(list.get(i).getName().equals(FilterName)  && list.get(i).getProff().equals(FilterProff)){
                                if(!(list.get(i).getPresent() == 0)){
                                    list.get(i).setPresent(list.get(i).getPresent()-1);
                                    tv_present.setText(String.valueOf(list.get(i).getPresent()));
                                    tv_Perce.setText(String.valueOf(calculatePerce(list.get(i).getPresent(),list.get(i).getAbsent())));
                                    setTotalPercentage();
                                }
                            }
                        }
                    }

                }
            });

            iv_absent_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String FilterName = listFiltered.get(getAdapterPosition()).getName();
                    String FilterProff = listFiltered.get(getAdapterPosition()).getProff();
                    if(list.contains(listFiltered.get(getAdapterPosition()))){
                        for(int i = 0;i<list.size();i++){
                            if(list.get(i).getName().equals(FilterName)  && list.get(i).getProff().equals(FilterProff)){
                                if(!(list.get(i).getAbsent() == 0)){
                                    list.get(i).setAbsent(list.get(i).getAbsent()-1);
                                    tv_absent.setText(String.valueOf(list.get(i).getAbsent()));
                                    tv_Perce.setText(String.valueOf(calculatePerce(list.get(i).getPresent(),list.get(i).getAbsent())));
                                    setTotalPercentage();
                                }
                            }
                        }
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

        public void deleteFromClickedPosition(){
            int savePresent = listFiltered.get(getAdapterPosition()).getPresent();
            int saveAbsent = listFiltered.get(getAdapterPosition()).getAbsent();
            AttendanceMain.editedSubjectPresent = savePresent;
            AttendanceMain.editedSubjectAbsent = saveAbsent;

            list.remove(listFiltered.get(getAdapterPosition()));
            listFiltered.remove(getAdapterPosition());
        }

        public void setEditFields(){
            et_subjNameAdd.setText(listFiltered.get(getAdapterPosition()).getName());
            et_subjProffAdd.setText(listFiltered.get(getAdapterPosition()).getProff());
            day_picker_Add.setSelectedDays(listFiltered.get(getAdapterPosition()).getWeekdays());
        }
    }
}
