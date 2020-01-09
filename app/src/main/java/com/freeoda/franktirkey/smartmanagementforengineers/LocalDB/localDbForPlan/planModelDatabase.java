package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.planModel;

@Database(entities = {planModel.class},version = 1,exportSchema = false)
public abstract class planModelDatabase extends RoomDatabase {
    public abstract planModelDao planModelDao();
}
