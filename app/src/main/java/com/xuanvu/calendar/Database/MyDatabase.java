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

    private static final String START_TIME_HOUR = "Start_time_hour";
    private static final String START_TIME_MINUTE = "Start_time_minute";
    private static final String START_TIME_DAY = "Start_time_day";
    private static final String START_TIME_MONTH = "Start_time_month";
    private static final String START_TIME_YEAR = "Start_time_year";

    private static final String END_TIME_HOUR = "End_time_hour";
    private static final String END_TIME_MINUTE = "End_time_minute";
    private static final String END_TIME_DAY = "End_time_day";
    private static final String END_TIME_MONTH = "End_time_month";
    private static final String END_TIME_YEAR = "End_time_year";

    private String[] columns = {"Calender_Id", "Calender_Title", "Calender_Content", "Start_time_hour", "Start_time_minute", "Start_time_day", "Start_time_month", "Start_time_year", "End_time_hour", "End_time_minute", "End_time_day", "End_time_month", "End_time_year"};

    public MyDatabase(@Nullable Context context) {
        super( context, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = " CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + TITLE + " TEXT, " + CONTENT + " TEXT, " + START_TIME_HOUR + " TEXT, " + START_TIME_MINUTE + " TEXT, " + START_TIME_DAY + " TEXT ," + START_TIME_MONTH + " TEXT ," + START_TIME_YEAR + " TEXT, " + END_TIME_HOUR + " TEXT , " + END_TIME_MINUTE + " TEXT ," + END_TIME_DAY + " TEXT," + END_TIME_MONTH + " TEXT ," + END_TIME_YEAR + " TEXT)";
        db.execSQL( script );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public int addCalendar(com.xuanvu.calendar.Model.Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( TITLE, calendar.getmTitle() );
        values.put( CONTENT, calendar.getmContent() );
        values.put( START_TIME_HOUR, calendar.getmStartTimeNewHour() );
        values.put( START_TIME_MINUTE, calendar.getmStartTimeNewMinute() );
        values.put( START_TIME_DAY, calendar.getmStartTimeNewDay() );
        values.put( START_TIME_MONTH, calendar.getmStartTimeNewMonth() );
        values.put( START_TIME_YEAR, calendar.getmStartTimeNewYear() );

        values.put( END_TIME_HOUR, calendar.getmEndTimeNewHour() );
        values.put( END_TIME_MINUTE, calendar.getmEndTimeNewMinute() );
        values.put( END_TIME_DAY, calendar.getmEndTimeNewDay() );
        values.put( END_TIME_MONTH, calendar.getmEndTimeNewMonth() );
        values.put( END_TIME_YEAR, calendar.getmEndTimeNewYear() );

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

    public List<Calendar> getAllEvent() {
        List<Calendar> notesArrayList = new ArrayList<Calendar>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query( TABLE_NAME, columns, null, null, null, null, ID + " DESC", null );

        if (cursor.moveToFirst()) {
            do {
                Calendar calendar = new Calendar();
                calendar.setID( cursor.getInt( 0 ) );
                calendar.setmTitle( cursor.getString( 1 ) );
                calendar.setmContent( cursor.getString( 2 ) );

                calendar.setmStartTimeNewHour( cursor.getInt( 3 ) );
                calendar.setmStartTimeNewMinute( cursor.getInt( 4 ) );
                calendar.setmStartTimeNewDay( cursor.getInt( 5 ) );
                calendar.setmStartTimeNewMonth( cursor.getInt( 6 ) );
                calendar.setmStartTimeNewYear( cursor.getInt( 7 ) );

                calendar.setmEndTimeNewHour( cursor.getInt( 8 ) );
                calendar.setmEndTimeNewMinute( cursor.getInt( 9 ) );
                calendar.setmEndTimeNewDay( cursor.getInt( 10 ) );
                calendar.setmEndTimeNewMonth( cursor.getInt( 11 ) );
                calendar.setmEndTimeNewYear( cursor.getInt( 12 ) );
                notesArrayList.add( calendar );
            } while (cursor.moveToNext());
        }
        return notesArrayList;
    }

    public void updateCalendar(com.xuanvu.calendar.Model.Calendar calendar, int idCalendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( TITLE, calendar.getmTitle() );
        contentValues.put( CONTENT, calendar.getmContent() );
        contentValues.put( START_TIME_HOUR, calendar.getmStartTimeNewHour() );
        contentValues.put( START_TIME_MINUTE, calendar.getmStartTimeNewMinute() );
        contentValues.put( START_TIME_DAY, calendar.getmStartTimeNewDay() );
        contentValues.put( START_TIME_MONTH, calendar.getmStartTimeNewMonth() );
        contentValues.put( START_TIME_YEAR, calendar.getmStartTimeNewYear() );

        contentValues.put( END_TIME_HOUR, calendar.getmEndTimeNewHour() );
        contentValues.put( END_TIME_MINUTE, calendar.getmEndTimeNewMinute() );
        contentValues.put( END_TIME_DAY, calendar.getmEndTimeNewDay() );
        contentValues.put( END_TIME_MONTH, calendar.getmEndTimeNewMonth() );
        contentValues.put( END_TIME_YEAR, calendar.getmEndTimeNewYear() );
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
        Cursor cursor = (Cursor) db.rawQuery( "SELECT MAX(Calender_Id) FROM " + TABLE_NAME, null );
//        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            id = cursor.getInt( 0 );
        }
        Log.e( "autoSave_logID_database", String.valueOf( id ) );
        return id;
    }

    public Calendar getEventWithId(int idEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Calender_Id = " + idEvent;
        Cursor cursor = db.query( TABLE_NAME, columns, "Calender_Id" + "=" + idEvent, null, null, null, ID + " DESC", null );
        Calendar calendar = new Calendar();
        if (cursor.moveToFirst()) {
            do {

                calendar.setID( cursor.getInt( 0 ) );
                calendar.setmTitle( cursor.getString( 1 ) );
                calendar.setmContent( cursor.getString( 2 ) );

                calendar.setmStartTimeNewHour( cursor.getInt( 3 ) );
                calendar.setmStartTimeNewMinute( cursor.getInt( 4 ) );
                calendar.setmStartTimeNewDay( cursor.getInt( 5 ) );
                calendar.setmStartTimeNewMonth( cursor.getInt( 6 ) );
                calendar.setmStartTimeNewYear( cursor.getInt( 7 ) );

                calendar.setmEndTimeNewHour( cursor.getInt( 8 ) );
                calendar.setmEndTimeNewMinute( cursor.getInt( 9 ) );
                calendar.setmEndTimeNewDay( cursor.getInt( 10 ) );
                calendar.setmEndTimeNewMonth( cursor.getInt( 11 ) );
                calendar.setmEndTimeNewYear( cursor.getInt( 12 ) );
            } while (cursor.moveToNext());
        }
        return calendar;
    }

}
