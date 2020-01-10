package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
