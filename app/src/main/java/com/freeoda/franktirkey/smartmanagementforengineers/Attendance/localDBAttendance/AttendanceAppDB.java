package com.freeoda.franktirkey.smartmanagementforengineers.Attendance.localDBAttendance;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.freeoda.franktirkey.smartmanagementforengineers.Attendance.AttendanceMain_rvModel;

@Database(entities = {AttendanceMain_rvModel.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AttendanceAppDB extends RoomDatabase {
    public abstract AttendanceDAO attendanceDAO();
}
