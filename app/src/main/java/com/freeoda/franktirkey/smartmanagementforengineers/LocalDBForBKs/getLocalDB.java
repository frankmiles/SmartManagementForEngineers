package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class getLocalDB extends AsyncTask<Void,Void,Void> {

    static String name,branch,collage,regNo,email,sem,ownerId;

    Context context;
    public getLocalDB(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        BackendlessApplication.setUserFromDB(BackendlessApplication.getDb().userDao().getAll());
        if(BackendlessApplication.getUserFromDB().isEmpty()){
            name = "null";
            email = "null";
            branch = "null";
            sem = "null";
            regNo = "null";
            collage = "null";
            ownerId = "null";
            return null;
        }

        name = BackendlessApplication.getUserFromDB().get(0).getName();
        email = BackendlessApplication.getUserFromDB().get(0).getEmail();
        branch = BackendlessApplication.getUserFromDB().get(0).getBranch();
        sem = BackendlessApplication.getUserFromDB().get(0).getSemester();
        regNo = BackendlessApplication.getUserFromDB().get(0).getRegNo();
        collage = BackendlessApplication.getUserFromDB().get(0).getCollageId();
        ownerId = BackendlessApplication.getUserFromDB().get(0).getOwnerId();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("msg","display Success!");
    }

    /*Getter and Setter*/

    public static String getName() {
        return name;
    }

    public static String getBranch() {
        return branch;
    }

    public static String getCollage() {
        return collage;
    }

    public static String getRegNo() {
        return regNo;
    }

    public static String getEmail() {
        return email;
    }

    public static String getSem() {
        return sem;
    }

    public static String getOwnerId() {
        return ownerId;
    }
}
