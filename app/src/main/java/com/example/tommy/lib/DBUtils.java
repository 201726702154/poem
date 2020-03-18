package com.example.tommy.lib;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tommy.Data;

import java.util.ArrayList;
import java.util.List;
public class DBUtils {
    private static int count = 0;
    private Context mContext;
    private CollectionDBHelper collectionDBHelper;
    private SQLiteDatabase db;
    public DBUtils(Context mContext) {
        this.mContext = mContext;
    }
    public void createDB() {
        collectionDBHelper = new CollectionDBHelper(mContext);
        db = collectionDBHelper.getWritableDatabase();
        Toast.makeText(mContext, "database created!", Toast.LENGTH_SHORT);
        System.out.println("database created! ------------------------------------------------");
    }
    public void update() {
    }
    public List<Poem> select() {
        Cursor cursor = db.query(CollectionDBHelper.TABLE_NAME, null, null, null, null, null, null);
        List<Poem> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Poem data = new Poem();
            data.title = cursor.getString(cursor.getColumnIndex(CollectionDBHelper.Title));
            data.author = cursor.getString(cursor.getColumnIndex(CollectionDBHelper.Author));
            data.url = cursor.getString(cursor.getColumnIndex(CollectionDBHelper.URL));
            dataList.add(data);
        }
        return dataList;
    }
    public void selectAll() {
    }
    public void delete(Data data) {
        String whereClause = CollectionDBHelper.Title + "=?";
        String[] whereArgs = {data.poem_title};
        db.delete(CollectionDBHelper.TABLE_NAME, whereClause, whereArgs);
    }
    public void add(Data data) {
        ContentValues cv = new ContentValues();
        cv.put("title", data.poem_title);
        cv.put("author", data.poem_author);
        cv.put("url", data.poem_url);
        db.insert(CollectionDBHelper.TABLE_NAME, null, cv);
    }
    public void dropTableData() {
        db.execSQL("delete from " + CollectionDBHelper.TABLE_NAME);
    }
}
