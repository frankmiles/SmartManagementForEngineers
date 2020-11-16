package com.freeoda.franktirkey.smartmanagementforengineers.notice;

public class NoticeModel {
    String title,body,date,visit;

    public NoticeModel(String title, String body, String date, String visit) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.visit = visit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}
