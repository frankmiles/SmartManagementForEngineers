package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.localDbForPlan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.freeoda.franktirkey.smartmanagementforengineers.TabFragments.planModel;

import java.util.List;

@Dao
public interface planModelDao {

    String task = null;

    @Query("Select * from `plan`")
    List<planModel> getAll();

    @Insert(onConflict = 1)
    void insertAll(planModel... planmodel);

    @Delete
    void delete(planModel planmodel);

    @Query("DELETE FROM `plan`")
    void delAll();
}
