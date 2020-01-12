package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import android.graphics.drawable.Drawable;

public class SubjectMainModel {

    String subjName;
    int subjRes,subjID;


    public SubjectMainModel(int subjID, String subjName, int subjRes) {
        this.subjID = subjID;
        this.subjName = subjName;
        this.subjRes = subjRes;
    }

    public int getSubjID() {
        return subjID;
    }

    public void setSubjID(int subjID) {
        this.subjID = subjID;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public int getSubjRes() {
        return subjRes;
    }

    public void setSubjRes(int subjRes) {
        this.subjRes = subjRes;
    }
}
