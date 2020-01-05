package com.freeoda.franktirkey.smartmanagementforengineers;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class UserConfiguration {

    private String name,collage,sem,branch,regNo,email;
    private String BackendlessPassword;
    private boolean offlineLogin_Enabled = false;


    public UserConfiguration(@NonNull String name,@NonNull String collage,@NonNull String sem,
                             @NonNull String branch,@NonNull String regNo,@NonNull String email,@NonNull String backendlessPassword) {
        this.name = name;
        this.collage = collage;
        this.sem = sem;
        this.branch = branch;
        this.regNo = regNo;
        this.email = email;
        BackendlessPassword = backendlessPassword;
    }

    public UserConfiguration(boolean offlineLogin_Enabled) {
        this.offlineLogin_Enabled = offlineLogin_Enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBackendlessPassword() {
        return BackendlessPassword;
    }

    public void setBackendlessPassword(String backendlessPassword) {
        BackendlessPassword = backendlessPassword;
    }

    public boolean isOfflineLogin_Enabled() {
        return offlineLogin_Enabled;
    }

    public void setOfflineLogin_Enabled(boolean offlineLogin_Enabled) {
        this.offlineLogin_Enabled = offlineLogin_Enabled;
    }

    public void setValueToSharedPref(){


    }
}
