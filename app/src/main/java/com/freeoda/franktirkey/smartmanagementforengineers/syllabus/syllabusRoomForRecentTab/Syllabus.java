package com.freeoda.franktirkey.smartmanagementforengineers.syllabus.syllabusRoomForRecentTab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Syllabus {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String Name;

    @ColumnInfo
    private String Url;

    public Syllabus() {}

    @Ignore
    public Syllabus(String name, String url) {
        this.Name = name;
        Url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
