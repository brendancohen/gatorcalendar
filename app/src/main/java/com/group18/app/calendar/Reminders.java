package com.group18.app.calendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

public class Reminders implements Parcelable {
    private String name;
    private String notes;
    private Date date;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private String onTheseDays;

    //we want to be able to uniquely identify this class
    private UUID primarykey;

    public void setPrimarykey(String primarykey) {
        this.primarykey = UUID.fromString(primarykey);
    }


    public int getMinute() {
        return min;
    }

    public int getNotificationMinute() {
        return min - 5;
    }

    public void setMinute(int min) {
        this.min = min;
    }

    public int getHour() {

        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public Date getStart() {
        return date;
    }


    public void setStart(Date start) {
        this.date = start;
        Log.i("date Rem", "the date start = " + start);
    }

    public Reminders(String remName, String remNotes, Date userDate){
        name = remName;
        notes = remNotes;
        date = userDate;
        primarykey = UUID.randomUUID();
        date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOnTheseDays() {
        return onTheseDays;
    }

    public void setOnTheseDays(String onTheseDays) {
        this.onTheseDays = onTheseDays;
    }

    //ISSUE: startHour is being overwritten by startMinute, endHour is being overwritten by endMinute
    protected Reminders(Parcel in) {
        name = in.readString();
        notes = in.readString();
//        onTheseDays = in.readString();
        long tmpStart = in.readLong();
//        start = tmpStart != -1 ? new Date(tmpStart) : null;
        long tmpEnd = in.readLong();
        date = tmpEnd != -1 ? new Date(tmpEnd) : null;
        hour = in.readInt();
        min = in.readInt();
        primarykey = (UUID) in.readValue(UUID.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(notes);
//        dest.writeString(onTheseDays);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeInt(hour);
        dest.writeInt(min);
//        dest.writeValue(primarykey);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Reminders> CREATOR = new Parcelable.Creator<Reminders>() {
        @Override
        public Reminders createFromParcel(Parcel in) {
            return new Reminders(in);
        }

        @Override
        public Reminders[] newArray(int size) {
            return new Reminders[size];
        }
    };
}
