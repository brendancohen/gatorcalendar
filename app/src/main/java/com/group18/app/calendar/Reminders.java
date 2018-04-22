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
    private int hour;
    private int min;
    private UUID primarykey;


    //we want to be able to uniquely identify this class

    public void setPrimarykey(String primarykey) {
        this.primarykey = UUID.fromString(primarykey);
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public int getMin() {
        return min;
    }

    public int getNotificationMinute() {
        return min - 5;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {

        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Date getDate() {
        Log.i("date Rem", "getDate: the date start = " + date);
        return date;
    }


    public void setDate(Date start) {
        date = start;
        Log.i("date Rem", "setDate: the date start = " + start);
    }

    public Reminders(String remName, String remNotes){
        name = remName;
        notes = remNotes;
        date = new Date();
        primarykey = UUID.randomUUID();

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



    protected Reminders(Parcel in) {
        name = in.readString();
        notes = in.readString();
        long tmpStart = in.readLong();
        date = tmpStart != -1 ? new Date(tmpStart) : null;
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
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeInt(hour);
        dest.writeInt(min);
        dest.writeValue(primarykey);
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
