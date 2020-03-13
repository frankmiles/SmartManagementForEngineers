package com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Syllabus.class},version = 1,exportSchema = false)
public abstract class SyllabusAppDatabase extends RoomDatabase {
    public abstract SyllabusDao syllabusDao();
}
