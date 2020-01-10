package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class setLocalDB extends AsyncTask<Void,Void,Void> {

    Context context;
    public setLocalDB(Context context){
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        BackendlessApplication.getDb().userDao().insertAll(BackendlessApplication.getUser());
        Log.println(Log.VERBOSE,"user",BackendlessApplication.getUser().getName().toString());//TODO for testing Purpose only
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"Inserted Success!",Toast.LENGTH_LONG) .show();
    }
}
