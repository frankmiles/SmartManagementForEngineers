package com.freeoda.franktirkey.smartmanagementforengineers.Subject.subjectRoomForRecentTab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Subject {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    int selectedSem;

    @ColumnInfo
    int selectedBranch;

    public Subject() {}

    @Ignore
    public Subject(int selectedSem, int selectedBranch) {
        this.selectedSem = selectedSem;
        this.selectedBranch = selectedBranch;
    }

    public int getSelectedSem() {
        return selectedSem;
    }

    public void setSelectedSem(int selectedSem) {
        this.selectedSem = selectedSem;
    }

    public int getSelectedBranch() {
        return selectedBranch;
    }

    public void setSelectedBranch(int selectedBranch) {
        this.selectedBranch = selectedBranch;
    }
}
