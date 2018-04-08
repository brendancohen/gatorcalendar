package com.group18.app.calendar;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eddie on 2/19/18.
 */

//implements parcelable, allows a Commitment object to be put into the extra of an intent


public class Commitments implements Parcelable {

    private String professor;
    private String cname;
    private String onTheseDays;
    private Date start;
    private Date end;
    private int startHour;
    private int endHour;
    private int startMinute;
    private int endMinute;

    //we want to be able to uniquely identify this class
    private UUID primarykey;

    public void setPrimarykey(String primarykey) {
        this.primarykey = UUID.fromString(primarykey);
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getStartMinute() {

        return startMinute;
    }

    public int getNotificationMinute() {
        return startMinute - 5;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {

        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartHour() {

        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }


    public void setStart(Date start) {
        this.start = start;
    }

    public Commitments(String uprofessor, String classname, String days){
        professor = uprofessor;
        cname = classname;
        onTheseDays = days;
        primarykey = UUID.randomUUID();
        start = new Date();
        end = new Date();
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getOnTheseDays() {
        return onTheseDays;
    }

    public void setOnTheseDays(String onTheseDays) {
        this.onTheseDays = onTheseDays;
    }

    //ISSUE: startHour is being overwritten by startMinute, endHour is being overwritten by endMinute
    protected Commitments(Parcel in) {
        professor = in.readString();
        cname = in.readString();
        onTheseDays = in.readString();
        long tmpStart = in.readLong();
        start = tmpStart != -1 ? new Date(tmpStart) : null;
        long tmpEnd = in.readLong();
        end = tmpEnd != -1 ? new Date(tmpEnd) : null;
        startHour = in.readInt();
        endHour = in.readInt();
        startMinute = in.readInt();
        endMinute = in.readInt();
        primarykey = (UUID) in.readValue(UUID.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(professor);
        dest.writeString(cname);
        dest.writeString(onTheseDays);
        dest.writeLong(start != null ? start.getTime() : -1L);
        dest.writeLong(end != null ? end.getTime() : -1L);
        dest.writeInt(startHour);
        dest.writeInt(endHour);
        dest.writeInt(startMinute);
        dest.writeInt(endMinute);
        dest.writeValue(primarykey);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Commitments> CREATOR = new Parcelable.Creator<Commitments>() {
        @Override
        public Commitments createFromParcel(Parcel in) {
            return new Commitments(in);
        }

        @Override
        public Commitments[] newArray(int size) {
            return new Commitments[size];
        }
    };
}