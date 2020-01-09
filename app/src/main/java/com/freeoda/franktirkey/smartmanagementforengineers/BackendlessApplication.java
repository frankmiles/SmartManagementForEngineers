package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;

import androidx.room.Room;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.AppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.User;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan.getLocalDbPlan;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan.planModelDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan.setLocalDbPlan;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.planModel;

import java.util.List;

public class BackendlessApplication extends Application {

    static BackendlessUser backendlessUser;
    static User user = new User();
    static AppDatabase db;
    static List<User> userFromDB;

    //static planModel plan = new planModel("tom");
    static planModel plan = new planModel();
    static planModelDatabase planDB;
    static List<planModel> planFromDBList;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( this,"A32C8534-709A-4E2C-829B-0614A16E5DF3", "D5A68302-4647-45AC-82F2-33906138BFB0");
        db = Room.databaseBuilder(BackendlessApplication.this,
                AppDatabase.class,"User")
                .fallbackToDestructiveMigration()
                .build();

        planDB =Room.databaseBuilder(this,planModelDatabase.class,"plan")
        .fallbackToDestructiveMigration()
        .build();
        //new setLocalDbPlan().execute();
        new getLocalDbPlan().execute();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BackendlessApplication.user = user;
    }

    public static AppDatabase getDb() {
        return db;
    }

    public static void setDb(AppDatabase db) {
        BackendlessApplication.db = db;
    }

    public static List<User> getUserFromDB() {
        return userFromDB;
    }

    public static void setUserFromDB(List<User> userFromDB) {
        BackendlessApplication.userFromDB = userFromDB;
    }


    public static planModel getPlan() {
        return plan;
    }


    public static void setPlan(planModel plan) {
        BackendlessApplication.plan = plan;
    }

    public static planModelDatabase getPlanDB() {
        return planDB;
    }

    public static void setPlanDB(planModelDatabase planDB) {
        BackendlessApplication.planDB = planDB;
    }

    public static List<planModel> getPlanFromDBList() {
        return planFromDBList;
    }

    public static void setPlanFromDBList(List<planModel> planFromDBList) {
        BackendlessApplication.planFromDBList = planFromDBList;
    }
}
