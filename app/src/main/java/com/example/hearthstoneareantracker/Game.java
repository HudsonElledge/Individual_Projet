package com.example.hearthstoneareantracker;

import java.util.Date;
import java.util.UUID;

public class Game {
    private UUID mID;
    private Date mDate;
    private boolean mWin;
    private String mTitle;

    public Game(){
        this(UUID.randomUUID());
    }

    public Game(UUID id){
        mID =id;
        mDate = new Date();
    }

    public void setWin(boolean mWin) {
        this.mWin = mWin;
    }

    public boolean isWin() {
        return mWin;
    }


    public UUID getID() {
        return mID;
    }

    public void setID(UUID mID) {
        this.mID = mID;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public String getTitle(){
        return mTitle;
    }
}
