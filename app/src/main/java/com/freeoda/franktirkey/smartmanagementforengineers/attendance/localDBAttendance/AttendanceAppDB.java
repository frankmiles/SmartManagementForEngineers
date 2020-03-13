package com.freeoda.franktirkey.smartmanagementforengineers.attendance.localDBAttendance;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.freeoda.franktirkey.smartmanagementforengineers.attendance.AttendanceMain_rvModel;

@Database(entities = {AttendanceMain_rvModel.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AttendanceAppDB extends RoomDatabase {
    public abstract AttendanceDAO attendanceDAO();
}
