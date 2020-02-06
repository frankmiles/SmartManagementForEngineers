package com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.freeoda.franktirkey.smartmanagementforengineers.Attendance.AttendanceMain_rvModel;

@Database(entities = {AttendanceMain_rvModel.class}, version = 1, exportSchema = false)
public abstract class AttendanceAppDB extends RoomDatabase {
    public abstract AttendanceDAO attendanceDAO();
}
