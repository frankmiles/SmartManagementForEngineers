package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class AttendenceMain_rvModel {
    MaterialDayPicker.Weekday day;
    int present,absent;
    String subject,teacher;

    public AttendenceMain_rvModel() {
    }

    public AttendenceMain_rvModel(String subject, String teacher,
                                  MaterialDayPicker.Weekday day, int present, int absent) {
        this.day = day;
        this.present = present;
        this.absent = absent;
        this.subject = subject;
        this.teacher = teacher;
    }

    public MaterialDayPicker.Weekday getDay() {
        return day;
    }

    public void setDay(MaterialDayPicker.Weekday day) {
        this.day = day;
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
