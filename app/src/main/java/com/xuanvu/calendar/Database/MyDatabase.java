package com.xuanvu.calendar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xuanvu.calendar.Model.Calendar;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Calendar_Manager";
    private static final String TABLE_NAME = "Calender";

    private static final String ID = "Calender_Id";
    private static final String TITLE = "Calender_Title";
    private static final String CONTENT = "Calender_Content";
    private static final String START_TIME = "Start_time";
    private static final String END_TIME = "End_time";

    private String[] columns = {"Calender_Id", "Calender_Title", "Calender_Content", "Start_time", "End_time"};

    public MyDatabase(@Nullable Context context) {
        super( context, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = " CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + TITLE + " TEXT, " + CONTENT + " TEXT, " + START_TIME + " TEXT ," + END_TIME + " TEXT)";
        db.execSQL( script );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public int addCalendar(Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( TITLE, calendar.getmTitle() );
        values.put( CONTENT, calendar.getmContent() );
        values.put( START_TIME, calendar.getmStartTime() );
        values.put( END_TIME, calendar.getmEndTime() );
        db.insert( TABLE_NAME, null, values );
        db = this.getReadableDatabase();
        String id = " SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery( id, null );
        if (cursor != null) cursor.moveToFirst();
        db.close();
        return Integer.parseInt( cursor.getString( 0 ) );
    }

    public boolean deleteCalendar(Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        int check = db.delete( TABLE_NAME, ID + " = " + calendar.getID(), null );
        if (check != 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;

        }

    }

    public boolean deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        int check = db.delete( TABLE_NAME, null, null );

        if (check != 0) {

            db.close();
            return true;
        } else {

            db.close();
            return false;

        }
    }

    public List<Calendar> getAllNotes() {
        List<Calendar> notesArrayList = new ArrayList<Calendar>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query( TABLE_NAME, columns, null, null, null, null, ID + " DESC", null );

        if (cursor.moveToFirst()) {
            do {
                Calendar note = new Calendar();
                note.setID( cursor.getInt( 0 ) );
                note.setmTitle( cursor.getString( 1 ) );
                note.setmContent( cursor.getString( 2 ) );
                note.setmStartTime( cursor.getString( 3 ) );
                note.setmEndTime( cursor.getString( 4 ) );
                notesArrayList.add( note );
            } while (cursor.moveToNext());
        }
        return notesArrayList;
    }

    public void updateCalendar(Calendar calendar, int idCalendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( TITLE, calendar.getmTitle() );
        contentValues.put( CONTENT, calendar.getmContent() );
        contentValues.put( START_TIME, calendar.getmTitle() );
        contentValues.put( END_TIME, calendar.getmContent() );
        db.update( TABLE_NAME, contentValues, ID + "=" + idCalendar, null );
    }

    public int getLastedIdInsert() {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
//        String id = " SELECT last_insert_rowid()",null;
//        Cursor cursor = db.rawQuery( id, null );
//        if (cursor != null) cursor.moveToFirst();
//        db.close();
//        return Integer.parseInt( cursor.getString( 0 ) );
        Cursor cursor = (Cursor) db.rawQuery( "SELECT MAX(Note_Id) FROM " + TABLE_NAME, null );
//        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            id = cursor.getInt( 0 );
        }
        Log.e( "autoSave_logID_database", String.valueOf( id ) );
        return id;
    }

}