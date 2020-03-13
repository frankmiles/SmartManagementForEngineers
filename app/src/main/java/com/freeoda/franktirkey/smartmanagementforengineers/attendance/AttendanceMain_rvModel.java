package com.freeoda.franktirkey.smartmanagementforengineers.attendance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.freeoda.franktirkey.smartmanagementforengineers.attendance.localDBAttendance.Converters;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

@Entity(tableName = "Attendance")
public class AttendanceMain_rvModel {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String name;

    @ColumnInfo
    String proff;

    @ColumnInfo
    String Stringdays;

    @Ignore
    List<MaterialDayPicker.Weekday> weekdays = new ArrayList<>();

    @ColumnInfo
    int present;

    @ColumnInfo
    int absent;


    public AttendanceMain_rvModel() {
    }

    @Ignore
    public AttendanceMain_rvModel(String name, String proff, List<MaterialDayPicker.Weekday> weekdays, int present, int absent) {
        this.name = name;
        this.proff = proff;
        this.present = present;
        this.absent = absent;
        this.weekdays = weekdays;
        convertMaterialdaysToString(weekdays);
    }

    @Ignore
    private void convertMaterialdaysToString(List<MaterialDayPicker.Weekday> weekdays){
        ArrayList<String> day = new ArrayList<>();
        for(MaterialDayPicker.Weekday wd:weekdays){
            switch (wd){
                case SUNDAY:
                    day.add(MaterialDayPicker.Weekday.SUNDAY.toString());
                    break;
                case MONDAY:
                    day.add(MaterialDayPicker.Weekday.MONDAY.toString());
                    break;
                case TUESDAY:
                    day.add(MaterialDayPicker.Weekday.TUESDAY.toString());
                    break;
                case WEDNESDAY:
                    day.add(MaterialDayPicker.Weekday.WEDNESDAY.toString());
                    break;
                case THURSDAY:
                    day.add(MaterialDayPicker.Weekday.THURSDAY.toString());
                    break;
                case FRIDAY:
                    day.add(MaterialDayPicker.Weekday.FRIDAY.toString());
                    break;
                case SATURDAY:
                    day.add(MaterialDayPicker.Weekday.SATURDAY.toString());
                    break;
            }
        }
        this.Stringdays = Converters.fromArrayList(day);
    }

    @Ignore
    private void ConvertStringToMaterialdays(String day){

        ArrayList<String> weekdays = Converters.fromString(day);
        this.weekdays.clear();
        for(String sd:weekdays){
            switch (sd.toUpperCase()){
                case "SUNDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.SUNDAY);
                    break;
                case "MONDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.MONDAY);
                    break;
                case "TUESDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.TUESDAY);
                    break;
                case "WEDNESDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.WEDNESDAY);
                    break;
                case "THURSDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.THURSDAY);
                    break;
                case "FRIDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.FRIDAY);
                    break;
                case "SATURDAY":
                    this.weekdays.add(MaterialDayPicker.Weekday.SATURDAY);
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProff() {
        return proff;
    }

    public void setProff(String proff) {
        this.proff = proff;
    }

    public String getStringdays() {
        return Stringdays;
    }

    public void setStringdays(String stringdays) {
        Stringdays = stringdays;
    }

    public List<MaterialDayPicker.Weekday> getWeekdays() {
        ConvertStringToMaterialdays(this.Stringdays);
        return weekdays;
    }

    public void setWeekdays(List<MaterialDayPicker.Weekday> weekdays) {
        this.weekdays = weekdays;
        convertMaterialdaysToString(this.weekdays);
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }
}
