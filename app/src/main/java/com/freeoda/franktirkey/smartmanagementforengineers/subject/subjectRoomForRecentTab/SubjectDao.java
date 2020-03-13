package com.freeoda.franktirkey.smartmanagementforengineers.subject.subjectRoomForRecentTab;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDao {

    @Query("Select * from Subject")
    List<Subject> getAll();

    @Insert(onConflict = 1)
    void insertAll(List<Subject> subjects);

    @Query("DELETE FROM Subject")
    void deleteAll();
}
