package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
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

    Button btn_SelectTime_Start,btn_SelectTime_End,btn_SaveEdit;
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

        btn_SelectTime_Start = findViewById(R.id.btn_SelectTime_Start);
        btn_SelectTime_End = findViewById(R.id.btn_SelectTime_End);
        btn_SaveEdit = findViewById(R.id.btn_SaveEdit);

        day_picker_edit = findViewById(R.id.day_picker_edit);
        day_picker_main = findViewById(R.id.day_picker_main);


        String branch = BackendlessApplication.getUser().getBranch();
        tv_atten_branch.setText(branch);

        String name = BackendlessApplication.getUser().getName();
        tv_atten_name.setText(name);

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

        btn_SelectTime_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTImePicker(view);
                getDays();
            }
        });

        btn_SelectTime_End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTImePicker(view);
                getDays();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_attendance.setLayoutManager(layoutManager);

        subjectList = fetchDataFromDB(); //Fetching Data from DB
        Log.d("msg","fetched Attendence for DB -> "+ subjectList.size());


//        materialDayPicker.setSelectionMode(SingleSelectionMode.create()); //TO make it single Selection mode

        setCurrentDay();
        totalPer();

        btn_SaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SubjName = et_subjName.getText().toString().trim();
                String SubjProff = et_subjProff.getText().toString().trim();
                List<MaterialDayPicker.Weekday> dayList = day_picker_edit.getSelectedDays();

                MaterialDayPicker.Weekday day = getWeekDay(dayList.get(0).toString());

                String msg = SubjName.concat(SubjProff).concat(" ").concat(String.valueOf(day))
                        .concat(" ").concat(String.valueOf(selectedHr_start)).concat(" ")
                        .concat(String.valueOf(selectedMin_start)).concat(" ").concat(String.valueOf(selectedHr_end))
                        .concat(" ").concat(String.valueOf(selectedMin_end));



                subjectList.add(new AttendanceMain_rvModel(SubjName,SubjProff,
                       day,10,0));

                fb_attendenceAddSubject.performClick();
                setCurrentDay();
                adapter.notifyDataSetChanged();
                Log.d("msgAtten","Added: "+msg);

            }
        });

        day_picker_main.setDaySelectionChangedListener(new MaterialDayPicker.DaySelectionChangedListener() {
            @Override
            public void onDaySelectionChanged(List<MaterialDayPicker.Weekday> list) {

                applyFilter(subjectList,list.get(0));
                Log.d("msg","DaySelectionChangedListener: "+list.get(0));

            }
        });

    }

    private  void applyFilter( List<AttendanceMain_rvModel> list,MaterialDayPicker.Weekday day){

        List<AttendanceMain_rvModel> tempList = new ArrayList<>();

        for(AttendanceMain_rvModel model: list){
            Log.d("msg", model.DayInString+ "->" + day.toString());
            if(model.DayInString.equalsIgnoreCase(day.toString())){
                tempList.add(model);
            }
        }
        adapter = new AttendenceMain_rvAdapter(
                AttendanceMain.this, tempList);
        rv_attendance.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private List<AttendanceMain_rvModel> fetchDataFromDB(){

        return BackendlessApplication.getAttendance_db().attendanceDAO().getAll();

    }

    private void showTImePicker(View view){

        if(view == btn_SelectTime_Start){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    btn_SelectTime_Start.setText(i + ":" + i1);
                    selectedHr_start = i;
                    selectedMin_start = i1;
                    flagStart = true;
                }
            },selectedHr_start,selectedMin_start,false);
            timePickerDialog.show();
        }else {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    btn_SelectTime_End.setText(i + ":" + i1);
                    selectedHr_end = i;
                    selectedMin_end = i1;
                    flagEnd = true;
                }
            },selectedHr_end,selectedMin_end,false);
            timePickerDialog.show();
        }
    }

    private void getDays(){
        selectedDays = day_picker_edit.getSelectedDays();
        Log.d("msg",selectedDays.toString()); //For testing purpose only
    }

    private void setCurrentDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d).toUpperCase();

        switch(dayOfTheWeek){

            case "MONDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.MONDAY);
                break;
            case "TUESDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.TUESDAY);
                break;
            case "WEDNESDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.WEDNESDAY);
                break;
            case "THURSDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.THURSDAY);
                break;
            case "FRIDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.FRIDAY);
                break;
            case "SATURDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.SATURDAY);
                break;
            case "SUNDAY":
                day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
                applyFilter(subjectList,MaterialDayPicker.Weekday.SUNDAY);
                break;

                default:
                    day_picker_edit.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
                    day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
                    applyFilter(subjectList,MaterialDayPicker.Weekday.MONDAY);
                    break;
        }
    }

    private MaterialDayPicker.Weekday getWeekDay(String dayOfTheWeek){

        switch(dayOfTheWeek.toUpperCase()){

            case "MONDAY":
                return MaterialDayPicker.Weekday.MONDAY;
            case "TUESDAY":
                return MaterialDayPicker.Weekday.TUESDAY;
            case "WEDNESDAY":
                return MaterialDayPicker.Weekday.WEDNESDAY;
            case "THURSDAY":
                return MaterialDayPicker.Weekday.THURSDAY;
            case "FRIDAY":
                return MaterialDayPicker.Weekday.FRIDAY;
            case "SATURDAY":
                return MaterialDayPicker.Weekday.SATURDAY;
            case "SUNDAY":
                return MaterialDayPicker.Weekday.SUNDAY;

            default:
                return MaterialDayPicker.Weekday.MONDAY;
        }
    }

    private void totalPer(){

        int Present=0,Absent=0;
        int overallPresent = 0;
        int overallAbsent = 0;
        int overallTotal=0;

        for(AttendanceMain_rvModel model : subjectList){

            Present = model.getPresent();
            Absent = model.getAbsent();
            overallPresent =+ Present;
            overallAbsent =+ Absent;
            overallTotal =+ Absent+Present;
        }

        float totalPer = 0;
        try {
            totalPer = (overallPresent * 100) / overallTotal;
        }catch (Exception e){
            e.printStackTrace();
            totalPer = 0;
        }


        tv_atten_percen.setText("Total Percent "+String.valueOf(totalPer).concat("%"));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        new setAllAttendanceFromDB(subjectList).execute();
    }
}
