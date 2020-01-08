package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import static com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.LocalDb.db;
import static com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.LocalDb.userFromDB;

public class getLocalDB extends AsyncTask<Void,Void,Void> {

    static String name,branch,collage,regNo,email,sem;

    Context context;
    public getLocalDB(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        userFromDB = db.userDao().getAll();
        if(userFromDB.isEmpty()){
            name = "null";
            email = "null";
            branch = "null";
            sem = "null";
            regNo = "null";
            collage = "null";
            return null;
        }

        name = userFromDB.get(0).getName();
        email = userFromDB.get(0).getEmail();
        branch = userFromDB.get(0).getBranch();
        sem = userFromDB.get(0).getSemester();
        regNo = userFromDB.get(0).getRegNo();
        collage = userFromDB.get(0).getCollage();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context,"display Success!",Toast.LENGTH_LONG) .show();
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
}
