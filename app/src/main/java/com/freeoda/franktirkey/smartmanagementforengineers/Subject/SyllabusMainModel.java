package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

public class SyllabusMainModel {

    String subjName;
    int subjID;

    public SyllabusMainModel(int subjID, String subjName) {
        this.subjName = subjName;
        this.subjID = subjID;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public int getSubjID() {
        return subjID;
    }

    public void setSubjID(int subjID) {
        this.subjID = subjID;
    }
}
