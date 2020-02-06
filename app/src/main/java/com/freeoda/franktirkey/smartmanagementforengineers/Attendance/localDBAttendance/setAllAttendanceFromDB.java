package com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.Attendance.AttendanceMain_rvModel;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class setAllAttendanceFromDB extends AsyncTask<Void, Void, Void> {

    List<AttendanceMain_rvModel> list;

    public setAllAttendanceFromDB(List<AttendanceMain_rvModel> list) {
        this.list = list;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getAttendance_db().attendanceDAO().insertAll(list);
        Log.d("msgDB","Inserted Attendance to the database! count: "+list.size());

        return null;
    }
}
