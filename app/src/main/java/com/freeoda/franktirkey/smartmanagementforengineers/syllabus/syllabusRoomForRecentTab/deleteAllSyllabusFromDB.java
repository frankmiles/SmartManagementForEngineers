package com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab;

import android.os.AsyncTask;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class deleteAllSyllabusFromDB extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getSyllabus_db().syllabusDao().deleteAll();

        return null;
    }
}
