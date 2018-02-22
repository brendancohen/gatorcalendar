package com.group18.app.calendar;

import java.util.Date;

/**
 * Created by eddie on 2/19/18.
 */

public class UFClass {
    private String professor;
    private String cname;
    private String onTheseDays;

    public UFClass(String uprofessor, String classname, String days){
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
}
