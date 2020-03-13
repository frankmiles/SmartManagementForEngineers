package com.freeoda.franktirkey.smartmanagementforengineers.syllabus;

public class SyllabusMainModel {

//    String subjName;
//    int subjID;
//
//    public SyllabusMainModel(int subjID, String subjName) {
//        this.subjName = subjName;
//        this.subjID = subjID;
//    }
//
//    public String getSubjName() {
//        return subjName;
//    }
//
//    public void setSubjName(String subjName) {
//        this.subjName = subjName;
//    }
//
//    public int getSubjID() {
//        return subjID;
//    }
//
//    public void setSubjID(int subjID) {
//        this.subjID = subjID;
//    }

    String module,topic,detail,url,youtube;

    public SyllabusMainModel() {
    }

    public SyllabusMainModel(String module, String topic, String detail, String url, String youtube) {
        this.module = module;
        this.topic = topic;
        this.detail = detail;
        this.url = url;
        this.youtube = youtube;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
