package com.freeoda.franktirkey.smartmanagementforengineers.subject;

public class SubjectMainModel {

    String subjName;
    int subjRes, subjectObjId;


    public SubjectMainModel(int subjectObjId, String subjName, int subjRes) {
        this.subjectObjId = subjectObjId;
        this.subjName = subjName;
        this.subjRes = subjRes;
    }

    public int getSubjectObjId() {
        return subjectObjId;
    }

    public void setSubjectObjId(int subjectObjId) {
        this.subjectObjId = subjectObjId;
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
