package com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class deleteAllSubjectFromDB extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getSubject_db().subjectDao().deleteAll();

        Log.d("msgDB","Deleted Subject from the database");

        return null;
    }
}
