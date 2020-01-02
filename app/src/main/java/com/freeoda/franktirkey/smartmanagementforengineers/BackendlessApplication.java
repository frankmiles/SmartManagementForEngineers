package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class BackendlessApplication extends Application {

    static BackendlessUser backendlessUser;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( this,"", "");
    }

    public void distroyBackendlessUser(){
        backendlessUser = null;
    }
}
