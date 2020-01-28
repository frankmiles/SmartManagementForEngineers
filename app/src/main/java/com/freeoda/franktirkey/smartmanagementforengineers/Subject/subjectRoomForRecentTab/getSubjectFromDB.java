package com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.AppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.SubjectMain;

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

        Log.d("msgDB","Fetched Subject From DB = " + list.toString());

        return null;
    }

    public static List<Subject> getList() {
        return list;
    }
}
