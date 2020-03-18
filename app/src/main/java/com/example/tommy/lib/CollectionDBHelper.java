package com.example.tommy.lib;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class CollectionDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "poem.db";
    public static final String TABLE_NAME = "poem";
    public static final String Title = "title";
    public static final String Author = "author";
    public static final String URL = "url";
    public CollectionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + Title + " VARCHAR(40) PRIMARY KEY,"
                + Author + " TEXT NOT NULL,"
                + URL + " TEXT NOT NULL" + ")");
        System.out.println("database created! ------------------------------------------>");
    }    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("database updated! ------------------------------------------>");
    }}
