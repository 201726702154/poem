package com.example.tommy.lib;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tommy.Data;

import java.util.ArrayList;
import java.util.List;
public class DBCOMUTIL {
    private Context mContext;
    private CommentDBHelper commentDBHelper;
    private SQLiteDatabase db;
    public DBCOMUTIL(Context mContext) {
        this.mContext = mContext;
    }
    public void createDB() {
        commentDBHelper = new CommentDBHelper(mContext);
        System.out.println("database created! -   ---------comment--------------------------------------");
    }
    public void add(String content) {
        db = commentDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("content", content);
        db.insert(CommentDBHelper.TABLE_NAME, null, cv);
        db.close();
    }
    public List<String> select() {
        db = commentDBHelper.getReadableDatabase();
        Cursor cursor = db.query(CommentDBHelper.TABLE_NAME, null, null, null, null, null, null);
        List<String> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String str = cursor.getString(cursor.getColumnIndex(CommentDBHelper.CONTENT));
            dataList.add(str);
        }
        db.close();
        return dataList;
    }
    public void dropTableData() {
        db = commentDBHelper.getWritableDatabase();
        db.execSQL("delete from " + CommentDBHelper.TABLE_NAME);
        db.close();
    }
}
