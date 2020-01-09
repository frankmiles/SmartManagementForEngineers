package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan;

import android.os.AsyncTask;
import android.util.Log;

import androidx.room.RoomDatabase;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class setLocalDbPlan extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {

        BackendlessApplication.getPlanDB().planModelDao().insertAll(BackendlessApplication.getPlan());
        Log.println(Log.VERBOSE,"msgSet",BackendlessApplication.getPlan().getTask()+"");//TODO for testing Purpose only
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.println(Log.VERBOSE,"msgSet","Success To set Plan");
    }
}
