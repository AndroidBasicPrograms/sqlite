package com.example.jayhind.lecture_app.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jay Hind on 4/16/2018.
 */

public class sqliteOpenHelper extends SQLiteOpenHelper {
    public sqliteOpenHelper(Context context) {
        super(context, DB.DB_NAME, null, DB.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(DB.CREATE_CUST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
