package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.syllabusRoomForRecentTab;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab.Subject;

import java.util.List;

@Dao
public interface SyllabusDao {

    @Query("Select * from Syllabus")
    List<Syllabus> getAll();

    @Insert(onConflict = 1)
    void insertAll(List<Syllabus> subjects);

    @Query("DELETE FROM Syllabus")
    void deleteAll();
}
