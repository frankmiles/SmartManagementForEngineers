package com.freeoda.franktirkey.smartmanagementforengineers.attendance.localDBAttendance;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.attendance.AttendanceMain_rvModel;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class getAllAttendenceFromDB extends AsyncTask<Void, Void,Void> {

    static List<AttendanceMain_rvModel> list;

    public getAllAttendenceFromDB() {
        list = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        list = BackendlessApplication.getAttendance_db().attendanceDAO().getAll();

        Log.d("msgDB","Fetched Attendance From DB = " + list.size());

        return null;
    }

    public static List<AttendanceMain_rvModel> getAttendanceList() {
        return list;
    }

}
