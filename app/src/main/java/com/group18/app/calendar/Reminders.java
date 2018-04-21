package com.group18.app.calendar;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;


public class Reminders implements Parcelable {

    private String name;
    private String notes;
    private Date date;
    private int hour;
    private int min;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Reminders(String remName, String remNotes, Date userdate){
        name = remName;
        notes = remNotes;
        date = userdate;
    }

    protected Reminders(Parcel in) {
        name = in.readString();
        notes = in.readString();
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        hour = in.readInt();
        min = in.readInt();
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
