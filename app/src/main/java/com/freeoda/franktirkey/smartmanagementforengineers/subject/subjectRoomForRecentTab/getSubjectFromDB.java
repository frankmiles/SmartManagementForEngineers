package com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class getSubjectFromDB extends AsyncTask<Void,Void,Void> {

    static List<Subject> list;

    public getSubjectFromDB() {
        list = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        list = BackendlessApplication.getSubject_db().subjectDao().getAll();

        Log.d("msgDB","Fetched Subject From DB = " + list.size());

        return null;
    }

    public static List<Subject> getList() {
        return list;
    }
}
