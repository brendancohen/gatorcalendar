package com.group18.app.calendar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.group18.app.calendar.database.CommitmentSchema.CommitmentTable;

/**
 * Created by eddie on 2/28/18.
 */

public class CommitmentHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME= "commitmentBase.db";

    public CommitmentHelper(Context context){
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table " + CommitmentTable.NAME + "(" + CommitmentTable.Cols.ID );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
