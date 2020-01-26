package com.freeoda.franktirkey.smartmanagementforengineers.Attendance;

public class AttendenceMain_rvModel {
    int percentage;
    String subject,teacher;

    public AttendenceMain_rvModel() {
    }

    public AttendenceMain_rvModel(int percentage, String subject, String teacher) {
        this.percentage = percentage;
        this.subject = subject;
        this.teacher = teacher;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
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
