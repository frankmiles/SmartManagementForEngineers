package com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Subject.class}, version = 1, exportSchema = false)
public abstract class SubjectAppDatabase extends RoomDatabase {
    public abstract SubjectDao subjectDao();
}