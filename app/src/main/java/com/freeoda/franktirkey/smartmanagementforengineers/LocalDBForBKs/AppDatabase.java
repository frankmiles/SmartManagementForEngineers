package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.RoomDatabase;

import java.io.Serializable;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
