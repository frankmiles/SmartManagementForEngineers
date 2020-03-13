package com.freeoda.franktirkey.smartmanagementforengineers.attendance.localDBAttendance;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.freeoda.franktirkey.smartmanagementforengineers.attendance.AttendanceMain_rvModel;

import java.util.List;

@Dao
public interface AttendanceDAO {

    @Query("SELECT * FROM Attendance")
    List<AttendanceMain_rvModel> getAll();

    @Insert(onConflict = 1)
    void insertAll(List<AttendanceMain_rvModel> list);

    @Query("DELETE FROM Attendance")
    void delateAll();
}
