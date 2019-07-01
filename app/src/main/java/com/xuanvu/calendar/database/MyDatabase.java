package com.xuanvu.calendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xuanvu.calendar.model.Event;

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
    private static final String START_DATE = "Start_date";
    private static final String END_DATE = "End_date";

    private String[] columns = {"Calender_Id", "Calender_Title", "Calender_Content", "Start_time", "End_time", "Start_date", "End_date"};

    public MyDatabase(@Nullable Context context) {
        super( context, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = " CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + TITLE + " TEXT, " + CONTENT + " TEXT, " + START_TIME + " TEXT, " + END_TIME + " TEXT, " + START_DATE + " TEXT ," + END_DATE + " TEXT )";
        db.execSQL( script );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public int addCalendar(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( TITLE, event.getmTitle() );
        values.put( CONTENT, event.getmContent() );
        values.put( START_TIME, event.getmStartTime() );
        values.put( END_TIME, event.getmEndTime() );
        values.put( START_DATE, event.getmStartDate() );
        values.put( END_DATE, event.getmEndDate() );


        db.insert( TABLE_NAME, null, values );
        db = this.getReadableDatabase();
        String id = " SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery( id, null );
        if (cursor != null) cursor.moveToFirst();
        db.close();
        return Integer.parseInt( cursor.getString( 0 ) );
    }

    public boolean deleteCalendar(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        int check = db.delete( TABLE_NAME, ID + " = " + event.getID(), null );
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

    public List<Event> getAllEvent() {
        List<Event> calendarsArrayList = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query( TABLE_NAME, columns, null, null, null, null, ID + " DESC", null );

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID( cursor.getInt( 0 ) );
                event.setmTitle( cursor.getString( 1 ) );
                event.setmContent( cursor.getString( 2 ) );

                event.setmStartTime( cursor.getString( 3 ) );
                event.setmEndTime( cursor.getString( 4 ) );
                event.setmStartDate( cursor.getString( 5 ) );
                event.setmEndDate( cursor.getString( 6 ) );

                calendarsArrayList.add( event );
            } while (cursor.moveToNext());
        }
        return calendarsArrayList;
    }

    public boolean updateCalendar(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( TITLE, event.getmTitle() );
        contentValues.put( CONTENT, event.getmContent() );
        contentValues.put( START_TIME, event.getmStartTime() );
        contentValues.put( END_TIME, event.getmEndTime() );
        contentValues.put( START_DATE, event.getmStartDate() );
        contentValues.put( END_DATE, event.getmEndDate() );

        int check = db.update( TABLE_NAME, contentValues, ID + " =" + event.getID(), null );
        if (check != 0) {

            db.close();
            return true;
        } else {

            db.close();
            return false;

        }

    }

    public int getLastedIdInsert() {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
//        String id = " SELECT last_insert_rowid()",null;
//        Cursor cursor = db.rawQuery( id, null );
//        if (cursor != null) cursor.moveToFirst();
//        db.close();
//        return Integer.parseInt( cursor.getString( 0 ) );
        Cursor cursor = (Cursor) db.rawQuery( "SELECT MAX(Calender_Id) FROM " + TABLE_NAME, null );
//        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            id = cursor.getInt( 0 );
        }
        Log.e( "autoSave_logID_database", String.valueOf( id ) );
        return id;
    }

    public Event getEventWithId(int idEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Calender_Id = " + idEvent;
        Cursor cursor = db.query( TABLE_NAME, columns, "Calender_Id" + "=" + idEvent, null, null, null, ID + " DESC", null );
        Event event = new Event();
        if (cursor.moveToFirst()) {
            do {

                event.setID( cursor.getInt( 0 ) );
                event.setmTitle( cursor.getString( 1 ) );
                event.setmContent( cursor.getString( 2 ) );

                event.setmStartTime( cursor.getString( 3 ) );
                event.setmEndTime( cursor.getString( 4 ) );
                event.setmStartDate( cursor.getString( 5 ) );
                event.setmEndDate( cursor.getString( 6 ) );

            } while (cursor.moveToNext());
        }
        return event;
    }

}
