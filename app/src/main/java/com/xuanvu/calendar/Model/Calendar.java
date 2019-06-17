package com.xuanvu.calendar.Model;

import java.io.Serializable;

public class Calendar implements Serializable {

    private int ID;
    private String mTitle;
    private String mContent;
    private String mStartTime;
    private String mEndTime;

    public Calendar() {
    }


    public Calendar(String mTitle, String mContent, String mStartTime, String mEndTime) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
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
}
