package com.makoval.androidphonebook.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "PHONEBOOK", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " type NUMBER, phoneNumber NUMBER, address TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE developers (id INTEGER REFERENCES users (id) ON DELETE CASCADE," +
                " name TEXT, surname TEXT, position TEXT, programmingLanguages TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE managers (id INTEGER REFERENCES users (id) ON DELETE CASCADE," +
                " name TEXT, surname TEXT, position TEXT);");
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS developers");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS managers");
        onCreate(sqLiteDatabase);
    }
}
