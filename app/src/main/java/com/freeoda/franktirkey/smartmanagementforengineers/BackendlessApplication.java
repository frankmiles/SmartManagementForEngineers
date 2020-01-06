package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class BackendlessApplication extends Application {

    static BackendlessUser backendlessUser;
    static String USER_EMAIl;  //TODO this fix and make it live
    static String USER_ID;
    static String USER_PASSWORD;
    static String USER_NAME;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( this,"A32C8534-709A-4E2C-829B-0614A16E5DF3", "D5A68302-4647-45AC-82F2-33906138BFB0");
    }

    public void distroyBackendlessUser(){
        backendlessUser = null;
    }

    public static String getUSER_EMAIl() {
        return backendlessUser.getEmail();
    }

    public static String getUserId() {
        return backendlessUser.getUserId();
    }

    public static String getUserPassword() {
        return backendlessUser.getPassword();
    }

    public static String getUserName() {
        return backendlessUser.getProperty("name").toString();
    }
}
