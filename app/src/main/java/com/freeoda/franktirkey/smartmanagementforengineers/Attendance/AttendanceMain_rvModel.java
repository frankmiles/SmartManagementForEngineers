package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import ca.antonious.materialdaypicker.MaterialDayPicker;

@Entity(tableName = "Attendance")
public class AttendanceMain_rvModel {

    @PrimaryKey(autoGenerate = true)
    int id;

    @Ignore
    MaterialDayPicker.Weekday day;

    @ColumnInfo
    String DayInString;

    @ColumnInfo
    int present;

    @ColumnInfo
    int absent;

    @ColumnInfo
    String subject;

    @ColumnInfo
    String teacher;

    public AttendanceMain_rvModel() {
    }

    @Ignore
    public AttendanceMain_rvModel(String subject, String teacher,
                                  MaterialDayPicker.Weekday day, int present, int absent) {
        this.day = day;
        this.present = present;
        this.absent = absent;
        this.subject = subject;
        this.teacher = teacher;
        DayInString = day.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MaterialDayPicker.Weekday getDay() {
        return day;
    }

    public void setDay(MaterialDayPicker.Weekday day) {
        this.day = day;
    }

    public String getDayInString() {
        return DayInString;
    }

    public void setDayInString(String dayInString) {
        DayInString = dayInString;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
