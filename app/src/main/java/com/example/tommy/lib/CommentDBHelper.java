package com.example.tommy.lib;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class CommentDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "comment.db";
    public static final String TABLE_NAME = "comment";
    public static final String Title = "title";
    public static final String Author = "author";
    public static final String CONTENT = "content";
    public static final String TIME = "time";
    public CommentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override     public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + CONTENT + " TEXT NOT NULL" + ")");
        System.out.println("database created! ------------------------------------------>");
    }    @Override    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        System.out.println("database updated! ------------------------------------------>");    }}
