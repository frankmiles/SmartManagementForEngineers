package com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class setSubjectFromDB extends AsyncTask<Void,Void,Void> {

    List<Subject> list = new ArrayList<>();

    public setSubjectFromDB(List<Subject> list) {
        this.list = list;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getSubject_db().subjectDao().insertAll(list);
        Log.d("msgDB","Inserted Subjects to the database!");

        return null;
    }
}
