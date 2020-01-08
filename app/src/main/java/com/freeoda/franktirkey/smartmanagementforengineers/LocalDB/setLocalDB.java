package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

import static com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.LocalDb.db;

public class setLocalDB extends AsyncTask<Void,Void,Void> {

    Context context;
    public setLocalDB(Context context){
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        db.userDao().insertAll(BackendlessApplication.getUser());
        Log.println(Log.VERBOSE,"user",BackendlessApplication.getUser().getName().toString());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"Inserted Success!",Toast.LENGTH_LONG) .show();
    }
}
