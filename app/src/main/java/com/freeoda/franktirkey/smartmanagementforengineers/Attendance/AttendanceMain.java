package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance.setAllAttendanceFromDB;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendanceMain extends AppCompatActivity {

    FloatingActionButton fb_attendenceAddSubject;

    CardView cv_ViewAtten,cv_attenEdit;

    RelativeLayout rl_attenMain;

    TextView tv_atten_percen;
    TextView tv_atten_name, tv_atten_branch;

    RecyclerView rv_attendance;
    static List<AttendanceMain_rvModel> subjectList =new ArrayList<>();

    TextView et_subjName,et_subjProff;

    MaterialDayPicker day_picker_edit,day_picker_main;

    Button btn_SaveEdit;
    Button btn_atten_up,btn_atten_down;

    TimePicker tp_timeSelected;

    static int selectedHr_start, selectedMin_start, selectedHr_end, selectedMin_end;
    boolean flagStart = false,flagEnd = false;

    List<MaterialDayPicker.Weekday> selectedDays = new ArrayList<>();

    AttendenceMain_rvAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        cv_ViewAtten = findViewById(R.id.cv_ViewAtten);

        cv_attenEdit = findViewById(R.id.cv_attenEdit);
        rl_attenMain = findViewById(R.id.rl_attenMain);

        tv_atten_percen = findViewById(R.id.tv_atten_percen);
        tv_atten_name = findViewById(R.id.tv_atten_name);
        tv_atten_branch = findViewById(R.id.tv_atten_branch);

        et_subjName = findViewById(R.id.et_subjName);
        et_subjProff = findViewById(R.id.et_subjProff);

        rv_attendance = findViewById(R.id.rv_attendance);
        fb_attendenceAddSubject = findViewById(R.id.fb_attendenceAddSubject);

        btn_SaveEdit = findViewById(R.id.btn_SaveEdit);

        btn_atten_up = findViewById(R.id.btn_atten_up);
        btn_atten_down = findViewById(R.id.btn_atten_down);

        day_picker_edit = findViewById(R.id.day_picker_edit);
        day_picker_main = findViewById(R.id.day_picker_main);

//        setCurrentDay();

        subjectList = fetchDataFromDB();
//        ArrayList<MaterialDayPicker.Weekday> tempList =  new ArrayList<>();
//        tempList.add(MaterialDayPicker.Weekday.MONDAY);
//        subjectList.add(new AttendanceMain_rvModel("DBMS","oom",tempList,0,0));

        fb_attendenceAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AttendanceMain.this,"Floating Btn Clicked!",Toast.LENGTH_SHORT).show();
                if(cv_ViewAtten.getVisibility() == View.VISIBLE){
                    cv_ViewAtten.setVisibility(View.GONE);
                    cv_attenEdit.setVisibility(View.VISIBLE);
                    fb_attendenceAddSubject.setImageDrawable(getResources().getDrawable(R.drawable.back));
                }else {
                    cv_ViewAtten.setVisibility(View.VISIBLE);
                    cv_attenEdit.setVisibility(View.GONE);
                    fb_attendenceAddSubject.setImageDrawable(getResources().getDrawable(R.drawable.add_icon));
                }
            }
        });

        LinearLayoutManager linearLayout = new LinearLayoutManager(AttendanceMain.this);
        rv_attendance.setLayoutManager(linearLayout);

        //dayPicker_main.setSelectionMode(SingleSelectionMode.create());
        day_picker_main.selectDay(MaterialDayPicker.Weekday.TUESDAY);
        day_picker_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new AttendenceMain_rvAdapter(subjectList,tv_atten_percen);
                rv_attendance.setAdapter(adapter);
                adapter.setDays(day_picker_main.getSelectedDays());
                adapter.getFilter().filter("");
            }
        });

        day_picker_main.performClick();
        day_picker_main.setDaySelectionChangedListener(new MaterialDayPicker.DaySelectionChangedListener() {
            @Override
            public void onDaySelectionChanged(List<MaterialDayPicker.Weekday> list) {
                adapter = new AttendenceMain_rvAdapter(subjectList,tv_atten_percen);
                rv_attendance.setAdapter(adapter);
                adapter.setDays(list);
                adapter.getFilter().filter("");
            }
        });

        btn_SaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SubjName = et_subjName.getText().toString().trim();
                String SubjProff = et_subjProff.getText().toString().trim();

                subjectList.add(new AttendanceMain_rvModel(SubjName,SubjProff,
                       day_picker_edit.getSelectedDays(),0,0));

                et_subjName.setText(null);
                et_subjProff.setText(null);

                fb_attendenceAddSubject.performClick();
//                setCurrentDay();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private List<AttendanceMain_rvModel> fetchDataFromDB(){
        return BackendlessApplication.getAttendance_db().attendanceDAO().getAll();
    }

//    private void getDays(){
//        selectedDays = day_picker_edit.getSelectedDays();
//        Log.d("msg",selectedDays.toString()); //For testing purpose only
//    }
//
//    private void showTImePicker(View view){
//
//        if(view == btn_SelectTime_Start){
//
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//                @Override
//                public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                    btn_SelectTime_Start.setText(i + ":" + i1);
//                    selectedHr_start = i;
//                    selectedMin_start = i1;
//                    flagStart = true;
//                }
//            },selectedHr_start,selectedMin_start,false);
//            timePickerDialog.show();
//        }else {
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//                @Override
//                public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                    btn_SelectTime_End.setText(i + ":" + i1);
//                    selectedHr_end = i;
//                    selectedMin_end = i1;
//                    flagEnd = true;
//                }
//            },selectedHr_end,selectedMin_end,false);
//            timePickerDialog.show();
//        }
//    }

//    private void setCurrentDay(){
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
//        Date d = new Date();
//        String dayOfTheWeek = sdf.format(d).toUpperCase();
//
//        switch(dayOfTheWeek){
//
//            case "TUESDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
//                break;
//            case "WEDNESDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
//                break;
//            case "THURSDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
//                break;
//            case "FRIDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
//                break;
//            case "SATURDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
//                break;
//            case "SUNDAY":
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
//                break;
//
//            default:
//                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BackendlessApplication.getAttendance_db().attendanceDAO().insertAll(subjectList);
    }
}
