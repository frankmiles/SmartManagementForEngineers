package com.freeoda.franktirkey.smartmanagementforengineers;

import android.app.Application;

import androidx.room.Room;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.freeoda.franktirkey.smartmanagementforengineers.attendance.localDBAttendance.AttendanceAppDB;
import com.freeoda.franktirkey.smartmanagementforengineers.localDBForBKs.AppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.localDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab.SubjectAppDatabase;
import com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab.SyllabusAppDatabase;

import java.util.List;

public class BackendlessApplication extends Application {

    public static final String APPLICATION_ID = "A32C8534-709A-4E2C-829B-0614A16E5DF3";
    public static final String API_KEY = "1E3F4FC2-C645-4116-B67A-4DBC62A75D61";
    public static final String SERVER_URL = "https://api.backendless.com";

    static BackendlessUser backendlessUser;
    static User user = new User();
    public static AppDatabase db;
    static List<User> userFromDB;

    public static SubjectAppDatabase subject_db;
    public static SyllabusAppDatabase syllabus_db;
    public static AttendanceAppDB attendance_db;

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

        syllabus_db = Room.databaseBuilder(BackendlessApplication.this,
                SyllabusAppDatabase.class,"Syllabus")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries() //TODO tESTING PURPOSE ONLY
                .build();

        attendance_db = Room.databaseBuilder(BackendlessApplication.this,
                AttendanceAppDB.class,"Attendance")
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

    public static SyllabusAppDatabase getSyllabus_db() {
        return syllabus_db;
    }

    public static void setSyllabus_db(SyllabusAppDatabase syllabus_db) {
        BackendlessApplication.syllabus_db = syllabus_db;
    }

    public static AttendanceAppDB getAttendance_db() {
        return attendance_db;
    }

    public static void setAttendance_db(AttendanceAppDB attendance_db) {
        BackendlessApplication.attendance_db = attendance_db;
    }
}
