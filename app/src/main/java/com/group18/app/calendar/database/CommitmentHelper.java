package com.group18.app.calendar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.group18.app.calendar.database.CommitmentSchema.CommitmentTable;

/**
 * Created by eddie on 2/28/18.
 * Edited by Brendan on 4/15
 */

public class CommitmentHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "commitmentBase.db";

    private static final String SQL_CREATE_ENTRIES = "create table "
            + CommitmentTable.NAME
            + "(" + CommitmentTable.Cols.ID
            + ", " + CommitmentTable.Cols.CNAME
            + ", " + CommitmentTable.Cols.PROFESSOR
            + ", " + CommitmentTable.Cols.ONTHESEDAYS
            + ", " + CommitmentTable.Cols.START
            + ", " + CommitmentTable.Cols.END
            + ", " + CommitmentTable.Cols.START_HOUR
            + ", " + CommitmentTable.Cols.START_MINUTE
            + ", " + CommitmentTable.Cols.END_HOUR
            + ", " + CommitmentTable.Cols.END_MINUTE
            + ", " + CommitmentTable.Cols.LAT
            + ", " + CommitmentTable.Cols.LONG+")";

    public CommitmentHelper(Context context){
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CommitmentTable.NAME);
        onCreate(db);
    }
}
