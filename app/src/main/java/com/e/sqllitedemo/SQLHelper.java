package com.e.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String dbName="MySubject.db";
    private static final String tableName="subject";
    private static final String id_column="ID";
    private static final String title_column="TITLE";
    private static final String description_column="DESCRIPTION";


    public SQLHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+tableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+tableName);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String title,String description){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(title_column,title);
        contentValues.put(description_column,description);

        long success=database.insert(tableName,null,contentValues);
        if (success==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase database=this.getWritableDatabase();

        Cursor cursor=database.rawQuery("select * from "+tableName,null);
        return cursor;
    }
    public Integer deleteData(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(tableName,"ID = ?",new String[]{id});
    }
}
