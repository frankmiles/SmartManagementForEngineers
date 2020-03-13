package com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class getSyllabusFromDB extends AsyncTask<Void,Void,Void> {

    private static List<Syllabus> list = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {

        list = BackendlessApplication.getSyllabus_db().syllabusDao().getAll();
        Log.d("msgDB","Fetched Syllabus From DB = " + list.size());

        return null;
    }

    public static List<Syllabus> getList() {
        return list;
    }
}
