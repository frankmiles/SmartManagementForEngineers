package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import static com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.LocalDb.db;

public class delLocalDB extends AsyncTask<Void,Void,Void> {
    Context context;
    public delLocalDB(Context context) {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        db.userDao().deleteAll();
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"delete Success!",Toast.LENGTH_LONG) .show();
    }
}
