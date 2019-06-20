package com.xuanvu.calendar.Model;

import java.io.Serializable;

public class Calendar implements Serializable {

    private int ID;
    private String mTitle;
    private String mContent;

    private int mStartTimeNewHour;
    private int mStartTimeNewMinute;
    private int mStartTimeNewDay;
    private int mStartTimeNewMonth;
    private int mStartTimeNewYear;

    private int mEndTimeNewHour;
    private int mEndTimeNewMinute;
    private int mEndTimeNewDay;
    private int mEndTimeNewMonth;
    private int mEndTimeNewYear;


    public Calendar() {
    }


    public Calendar(int ID, String mTitle, String mContent, int mStartTimeNewHour, int mStartTimeNewMinute, int mStartTimeNewDay, int mStartTimeNewMonth, int mStartTimeNewYear, int mEndTimeNewHour, int mEndTimeNewMinute, int mEndTimeNewDay, int mEndTimeNewMonth, int mEndTimeNewYear) {
        this.ID = ID;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStartTimeNewHour = mStartTimeNewHour;
        this.mStartTimeNewMinute = mStartTimeNewMinute;
        this.mStartTimeNewDay = mStartTimeNewDay;
        this.mStartTimeNewMonth = mStartTimeNewMonth;
        this.mStartTimeNewYear = mStartTimeNewYear;
        this.mEndTimeNewHour = mEndTimeNewHour;
        this.mEndTimeNewMinute = mEndTimeNewMinute;
        this.mEndTimeNewDay = mEndTimeNewDay;
        this.mEndTimeNewMonth = mEndTimeNewMonth;
        this.mEndTimeNewYear = mEndTimeNewYear;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmStartTimeNewMinute() {
        return mStartTimeNewMinute;
    }

    public void setmStartTimeNewMinute(int mStartTimeNewMinute) {
        this.mStartTimeNewMinute = mStartTimeNewMinute;
    }

    public int getmStartTimeNewMonth() {
        return mStartTimeNewMonth;
    }

    public void setmStartTimeNewMonth(int mStartTimeNewMonth) {
        this.mStartTimeNewMonth = mStartTimeNewMonth;
    }

    public int getmStartTimeNewYear() {
        return mStartTimeNewYear;
    }

    public void setmStartTimeNewYear(int mStartTimeNewYear) {
        this.mStartTimeNewYear = mStartTimeNewYear;
    }

    public int getmEndTimeNewMinute() {
        return mEndTimeNewMinute;
    }

    public void setmEndTimeNewMinute(int mEndTimeNewMinute) {
        this.mEndTimeNewMinute = mEndTimeNewMinute;
    }

    public int getmEndTimeNewMonth() {
        return mEndTimeNewMonth;
    }

    public void setmEndTimeNewMonth(int mEndTimeNewMonth) {
        this.mEndTimeNewMonth = mEndTimeNewMonth;
    }

    public int getmEndTimeNewYear() {
        return mEndTimeNewYear;
    }

    public void setmEndTimeNewYear(int mEndTimeNewYear) {
        this.mEndTimeNewYear = mEndTimeNewYear;
    }

    public int getmStartTimeNewHour() {
        return mStartTimeNewHour;
    }

    public void setmStartTimeNewHour(int mStartTimeNewHour) {
        this.mStartTimeNewHour = mStartTimeNewHour;
    }

    public int getmStartTimeNewDay() {
        return mStartTimeNewDay;
    }

    public void setmStartTimeNewDay(int mStartTimeNewDay) {
        this.mStartTimeNewDay = mStartTimeNewDay;
    }

    public int getmEndTimeNewHour() {
        return mEndTimeNewHour;
    }

    public void setmEndTimeNewHour(int mEndTimeNewHour) {
        this.mEndTimeNewHour = mEndTimeNewHour;
    }

    public int getmEndTimeNewDay() {
        return mEndTimeNewDay;
    }

    public void setmEndTimeNewDay(int mEndTimeNewDay) {
        this.mEndTimeNewDay = mEndTimeNewDay;
    }

    public void setmStartTimeNewMinute(String nextToken) {
    }
}
