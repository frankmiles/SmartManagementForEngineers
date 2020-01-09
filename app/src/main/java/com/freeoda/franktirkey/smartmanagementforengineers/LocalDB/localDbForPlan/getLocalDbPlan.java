package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Fragment_Plan;

public class getLocalDbPlan extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        BackendlessApplication.setPlanFromDBList(BackendlessApplication.getPlanDB().planModelDao().getAll());
        Log.println(Log.VERBOSE,"msgGet",BackendlessApplication.getPlanFromDBList().toString());//TODO for testing Purpose only
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(!Fragment_Plan.list.isEmpty()){
            Fragment_Plan.list = BackendlessApplication.getPlanFromDBList();
            Fragment_Plan.mainAdapter.notifyDataSetChanged();
        }

    }
}
