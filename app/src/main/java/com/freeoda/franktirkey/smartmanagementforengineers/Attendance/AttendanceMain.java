package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMain extends AppCompatActivity {

    RecyclerView rv_attendance;
    List<AttendenceMain_rvModel> list =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_main);

        rv_attendance = findViewById(R.id.rv_attendance);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_attendance.setLayoutManager(layoutManager);

        list.add(new AttendenceMain_rvModel(95,"DBMS","Tarini Sir"));
        list.add(new AttendenceMain_rvModel(25,"MATH","VIJAY Sir"));
        list.add(new AttendenceMain_rvModel(15,"PHYSICS","SAKTI Sir"));

        AttendenceMain_rvAdapter adapter = new AttendenceMain_rvAdapter(AttendanceMain.this,list);
        rv_attendance.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
