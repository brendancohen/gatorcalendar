package com.group18.app.calendar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by eddie on 2/19/18.
 */

public class Commitments implements Parcelable{
    public String professor;
    private String cname;
    private String onTheseDays;
    private Date start;
    private Date end;

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
        professor = in.readString();
        cname = in.readString();
        onTheseDays = in.readString();
        long tmpStart = in.readLong();
        start = tmpStart != -1 ? new Date(tmpStart) : null;
        long tmpEnd = in.readLong();
        end = tmpEnd != -1 ? new Date(tmpEnd) : null;
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
