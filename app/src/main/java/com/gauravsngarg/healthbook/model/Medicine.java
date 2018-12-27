package com.gauravsngarg.healthbook.model;

public class Medicine {
    String mName;
    String mSalt;
    String mStartDate;
    String mEndDate;

    public Medicine(){

    }

    public Medicine(String mName, String mSalt, String mStartDate, String mEndDate) {
        this.mName = mName;
        this.mSalt = mSalt;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }

   /* public Medicine(String name, String salt) {
        mName = name;
        mSalt = salt;
        mStartDate = "null";
        mEndDate = "null";
    }*/

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSalt() {
        return mSalt;
    }

    public void setSalt(String salt) {
        mSalt = salt;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }
}
