package com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;

@Database(entities = {Subject.class}, version = 1, exportSchema = false)
public abstract class SubjectAppDatabase extends RoomDatabase {
    public abstract SubjectDao subjectDao();
}