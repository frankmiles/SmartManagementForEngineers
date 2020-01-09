package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plan")
public class planModel {



    @PrimaryKey(autoGenerate = true)
        int tid;

        @ColumnInfo(name = "task")
        String task;

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }


}
