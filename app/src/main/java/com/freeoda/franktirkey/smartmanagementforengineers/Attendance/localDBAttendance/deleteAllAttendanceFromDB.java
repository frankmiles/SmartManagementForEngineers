package com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class deleteAllAttendanceFromDB extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getAttendance_db().attendanceDAO().delateAll();

        Log.d("msgDB","Deleted Attendance from the database");

        return null;
    }
}
