package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan;

import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.planModel;


public class delLocalDbPlan extends AsyncTask<Void,Void,Void> {
    planModel planModel;

    public delLocalDbPlan(planModel planModel) {
        this.planModel = planModel;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //BackendlessApplication.getPlanDB().planModelDao().delete(planModel);
        BackendlessApplication.getPlanDB().planModelDao().delAll();
        Log.println(Log.VERBOSE,"msgDel","deleted");
        return null;
    }
}
