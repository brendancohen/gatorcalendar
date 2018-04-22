package com.group18.app.calendar.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.group18.app.calendar.database.CommitmentSchema.ReminderTable;

public class ReminderHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "reminderBase.db";

    private static final String SQL_CREATE_ENTRIES = "create table "
            + ReminderTable.NAME
            + "(" + ReminderTable.Cols.EVENT
            + ", " + ReminderTable.Cols.NOTES
            + ", " + ReminderTable.Cols.HOUR
            + ", " + ReminderTable.Cols.MIN
            + ", " + ReminderTable.Cols.DATE
            + ", " + ReminderTable.Cols.ID
            + ")";



    public ReminderHelper(Context context){
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ReminderTable.NAME);
        onCreate(db);
    }
}
