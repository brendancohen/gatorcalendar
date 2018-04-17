package com.group18.app.calendar.database;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eddie on 2/27/18.
 */

public class CommitmentSchema {

    public static final class CommitmentTable{
        public static final String NAME = "commitments";

        public static final class Cols {
            public static final String PROFESSOR = "professor";
            public static final String CNAME = "classname";
            public static final String ONTHESEDAYS = "TheseDays";
            public static final String START = "DateStart";
            public static final String END = "DateEnd";
            public static final String ID ="ID";
            public static final String START_HOUR = "HourStart";
            public static final String END_HOUR = "HourEnd";
            public static final String START_MINUTE = "MinuteStart";
            public static final String END_MINUTE = "MinuteEnd";

        }
    }

    public static final class ReminderTable {
        public static final String NAME = "reminders";

        public static final class Cols{
            public static final String EVENT = "event";
            public static final String NOTES = "notes";
            public static final String TIME = "time";
            public static final String DATE = "date";
        }
    }
}
