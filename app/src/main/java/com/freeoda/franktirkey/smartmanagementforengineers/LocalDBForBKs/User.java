package com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "userDetails")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "ownerId")
    private String ownerId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "semester")
    private String semester;

    @ColumnInfo(name = "collageId")
    private String collageId;

    @ColumnInfo(name = "branch")
    private String branch;

    @ColumnInfo(name = "regNo")
    private String regNo;

    public User() {}

    @Ignore
    public User(int uid, String ownerId, String name, String email,
                String semester, String collageId, String branch,
                String regNo) {
        this.uid = uid;
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
        this.semester = semester;
        this.collageId = collageId;
        this.branch = branch;
        this.regNo = regNo;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCollageId() {
        return collageId;
    }

    public void setCollageId(String collageId) {
        this.collageId = collageId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

}
