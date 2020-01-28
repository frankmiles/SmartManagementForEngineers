package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.room.Room;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.persistence.DataQueryBuilder;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.AppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab.SubjectAppDatabase;

import java.util.List;

public class BackendlessApplication extends Application {

    public static String APPLICATION_ID = "A32C8534-709A-4E2C-829B-0614A16E5DF3";
    public static String API_KEY = "D5A68302-4647-45AC-82F2-33906138BFB0";
    public static final String SERVER_URL = "https://api.backendless.com";

    static BackendlessUser backendlessUser;
    static User user = new User();
    public static AppDatabase db;
    static List<User> userFromDB;

    public static SubjectAppDatabase subject_db;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp( this,APPLICATION_ID, API_KEY);
        db = Room.databaseBuilder(BackendlessApplication.this,
                AppDatabase.class,"User")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() //TODO tESTING PURPOSE ONLY
                .build();

        subject_db = Room.databaseBuilder(BackendlessApplication.this,
                SubjectAppDatabase.class,"Subject")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() //TODO tESTING PURPOSE ONLY
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

    public static BackendlessUser getBackendlessUser() {
        return backendlessUser;
    }

    public static void setBackendlessUser(BackendlessUser backendlessUser) {
        BackendlessApplication.backendlessUser = backendlessUser;
    }

    /*Subject DataBase Getter AND Setters*/

    public static SubjectAppDatabase getSubject_db() {
        return subject_db;
    }

    public static void setSubject_db(SubjectAppDatabase subject_db) {
        BackendlessApplication.subject_db = subject_db;
    }
}
