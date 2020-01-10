package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class delLocalDB extends AsyncTask<Void,Void,Void> {
    Context context;
    public delLocalDB(Context context) {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        BackendlessApplication.getDb().userDao().deleteAll();
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"delete Success!",Toast.LENGTH_LONG) .show();
    }
}
