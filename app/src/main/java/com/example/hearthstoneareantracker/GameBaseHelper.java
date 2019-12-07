package com.example.hearthstoneareantracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "gameBase.db";

    public GameBaseHelper(Context context){
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + GameDbSchema.GameTable.NAME + "(" + " _id integer primary key autoincrement, " + GameDbSchema.GameTable.Cols.UUID
        + ", " + GameDbSchema.GameTable.Cols.TITLE + ", " + GameDbSchema.GameTable.Cols.DATE + ", " + GameDbSchema.GameTable.Cols.WINS + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
