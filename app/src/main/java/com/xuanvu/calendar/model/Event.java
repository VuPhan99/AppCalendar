package com.xuanvu.calendar.model;

import java.io.Serializable;

public class Event implements Serializable {

    private int ID;
    private String mTitle;
    private String mContent;
    private String mStartTime;
    private String mEndTime;
    private String mStartDate;
    private String mEndDate;


    public Event() {
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

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Event(int ID, String mTitle, String mContent, String mStartTime, String mEndTime, String mStartDate, String mEndDate) {
        this.ID = ID;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }
}
