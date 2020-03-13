package com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab;

import android.os.AsyncTask;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import java.util.ArrayList;
import java.util.List;

public class setSyllabusFromDB extends AsyncTask<Void,Void,Void> {

    private List<Syllabus> list = new ArrayList<>();

    public setSyllabusFromDB(List<Syllabus> list) {
        this.list = list;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getSyllabus_db().syllabusDao().insertAll(list);

        return null;
    }
}
