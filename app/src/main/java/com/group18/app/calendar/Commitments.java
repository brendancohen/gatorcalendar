package com.group18.app.calendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eddie on 2/19/18.
 */

//implements parcelable, allows a Commitment object to be put into the extra of an intent


public class Commitments implements Parcelable {
    private String professor, cname, onTheseDays;
    private Date start, end;
    private int startHour, endHour, startMinute, endMinute;

    //we want to be able to uniquely identify this class
    private UUID primarykey;

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getStartMinute() {

        return startMinute;
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


    protected Commitments(Parcel in) {
        primarykey = (UUID) in.readValue(UUID.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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