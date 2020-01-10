package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;

import androidx.room.Room;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.AppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.Fragment_Plan;

import java.util.ArrayList;
import java.util.List;

public class BackendlessApplication extends Application {

    static BackendlessUser backendlessUser;
    static User user = new User();
    static AppDatabase db;
    static List<User> userFromDB;

    static List<String> plan = new ArrayList<String>();

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( this,"A32C8534-709A-4E2C-829B-0614A16E5DF3", "D5A68302-4647-45AC-82F2-33906138BFB0");
        db = Room.databaseBuilder(BackendlessApplication.this,
                AppDatabase.class,"User")
                .fallbackToDestructiveMigration()
                .build();
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


    public static List<String> getPlan() {
        return plan;
    }

    public static void setPlan(List<String> plan) {
        BackendlessApplication.plan = plan;
    }
}
