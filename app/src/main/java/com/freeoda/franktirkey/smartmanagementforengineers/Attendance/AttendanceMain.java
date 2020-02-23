package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendanceMain extends AppCompatActivity {

    FloatingActionButton fb_attendenceAddSubject;

    CardView cv_ViewAtten, cv_attenAdd;

    RelativeLayout rl_attenMain;

    TextView tv_atten_percen;
    TextView tv_atten_name, tv_atten_branch;

    RecyclerView rv_attendance;
    static List<AttendanceMain_rvModel> subjectList =new ArrayList<>();

    EditText et_subjNameAdd, et_subjProffAdd;

    MaterialDayPicker day_picker_Add,day_picker_main;

    Button btn_SaveAdd;
    Button btn_atten_up,btn_atten_down;

    TimePicker tp_timeSelected;

    static int selectedHr_start, selectedMin_start, selectedHr_end, selectedMin_end;
    boolean flagStart = false,flagEnd = false;

    List<MaterialDayPicker.Weekday> selectedDays = new ArrayList<>();

    AttendenceMain_rvAdapter adapter;

    View.OnClickListener btnEdit_onclickListner;

    public static int getAdapterClickedPosition=0;

    public static boolean editBtnClicked = false;

    public static int editedSubjectPresent = 0,editedSubjectAbsent = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        cv_ViewAtten = findViewById(R.id.cv_ViewAtten);

        cv_attenAdd = findViewById(R.id.cv_attenAdd);
        rl_attenMain = findViewById(R.id.rl_attenMain);

        tv_atten_percen = findViewById(R.id.tv_atten_percen);
        tv_atten_name = findViewById(R.id.tv_atten_name);
        tv_atten_branch = findViewById(R.id.tv_atten_branch);

        et_subjNameAdd = findViewById(R.id.et_subjNameAdd);
        et_subjProffAdd = findViewById(R.id.et_subjProffAdd);

        rv_attendance = findViewById(R.id.rv_attendance);
        fb_attendenceAddSubject = findViewById(R.id.fb_attendenceAddSubject);

        btn_SaveAdd = findViewById(R.id.btn_SaveAdd);

        btn_atten_up = findViewById(R.id.btn_atten_up);
        btn_atten_down = findViewById(R.id.btn_atten_down);

        day_picker_Add = findViewById(R.id.day_picker_Add);
        day_picker_main = findViewById(R.id.day_picker_main);

        tv_atten_name.setText(BackendlessApplication.getUser().getName());
        tv_atten_branch.setText(BackendlessApplication.getUser().getBranch());
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
                    cv_attenAdd.setVisibility(View.VISIBLE);
                    fb_attendenceAddSubject.setImageDrawable(getResources().getDrawable(R.drawable.back));

                    et_subjNameAdd.setText("");
                    et_subjProffAdd.setText("");
                } else {
                    cv_ViewAtten.setVisibility(View.VISIBLE);
                    cv_attenAdd.setVisibility(View.GONE);
                    fb_attendenceAddSubject.setImageDrawable(getResources().getDrawable(R.drawable.add_icon));
                }

                adapter.setEt_subjNameAdd(et_subjNameAdd);
                adapter.setEt_subjProffAdd(et_subjProffAdd);
                adapter.setDay_picker_Add(day_picker_Add);
            }
        });

        final LinearLayoutManager linearLayout = new LinearLayoutManager(AttendanceMain.this);
        rv_attendance.setLayoutManager(linearLayout);

        //dayPicker_main.setSelectionMode(SingleSelectionMode.create());
        day_picker_main.selectDay(MaterialDayPicker.Weekday.TUESDAY);
        day_picker_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new AttendenceMain_rvAdapter(subjectList,tv_atten_percen, fb_attendenceAddSubject);
                rv_attendance.setAdapter(adapter);
                adapter.setDays(day_picker_main.getSelectedDays());
                adapter.getFilter().filter("");
            }
        });

        day_picker_main.performClick();
        day_picker_main.setDaySelectionChangedListener(new MaterialDayPicker.DaySelectionChangedListener() {
            @Override
            public void onDaySelectionChanged(List<MaterialDayPicker.Weekday> list) {
                adapter = new AttendenceMain_rvAdapter(subjectList,tv_atten_percen,fb_attendenceAddSubject);
                rv_attendance.setAdapter(adapter);
                adapter.setDays(list);
                adapter.getFilter().filter("");
            }
        });

        btn_SaveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String SubjName = et_subjNameAdd.getText().toString().trim();
                String SubjProff = et_subjProffAdd.getText().toString().trim();

                if(day_picker_Add.getSelectedDays().isEmpty()){
                    Snackbar.make(rl_attenMain,"choose days",Snackbar.LENGTH_LONG).show();
                }else if (et_subjNameAdd.getText().toString().trim().equals("") || et_subjProffAdd.getText().toString().trim().equals("")){
                    Snackbar.make(rl_attenMain,"Fill all Fields",Snackbar.LENGTH_LONG).show();
                }
                else{

                    if (editBtnClicked){
                        subjectList.add(new AttendanceMain_rvModel(SubjName,SubjProff,
                                day_picker_Add.getSelectedDays(),editedSubjectPresent,editedSubjectAbsent));
                        editBtnClicked = false;
                    }else {
                        subjectList.add(new AttendanceMain_rvModel(SubjName,SubjProff,
                                day_picker_Add.getSelectedDays(),0,0));
                    }

                    et_subjNameAdd.setText("");
                    et_subjProffAdd.setText("");

                    fb_attendenceAddSubject.performClick();
//                setCurrentDay();
                }

                adapter.notifyDataSetChanged();
            }
        });

        btnEdit_onclickListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fb_attendenceAddSubject.performClick();
            }
        };

        adapter.setBtn_SaveAdd(btn_SaveAdd);

    }

    private List<AttendanceMain_rvModel> fetchDataFromDB(){
        return BackendlessApplication.getAttendance_db().attendanceDAO().getAll();
    }

//    private void getDays(){
//        selectedDays = day_picker_Add.getSelectedDays();
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
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.TUESDAY);
//                break;
//            case "WEDNESDAY":
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.WEDNESDAY);
//                break;
//            case "THURSDAY":
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.THURSDAY);
//                break;
//            case "FRIDAY":
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.FRIDAY);
//                break;
//            case "SATURDAY":
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SATURDAY);
//                break;
//            case "SUNDAY":
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.SUNDAY);
//                break;
//
//            default:
//                day_picker_Add.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
//                day_picker_main.setSelectedDays(MaterialDayPicker.Weekday.MONDAY);
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BackendlessApplication.getAttendance_db().attendanceDAO().insertAll(subjectList);
        if(subjectList != null){
            if(subjectList.isEmpty()){
                BackendlessApplication.getAttendance_db().attendanceDAO().delateAll();
            }
        }
    }
}
