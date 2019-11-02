package com.example.hearthstoneareantracker;

import java.util.Date;
import java.util.UUID;

public class Run {

    private Date rDate;
    private UUID rID;
    private boolean rDone;

    public Run(){
     rID = UUID.randomUUID();
     rDate = new Date();
    }

    public void setrDone(boolean rDone) {
        this.rDone = rDone;
    }

    public boolean isrDone() {
        return rDone;
    }

    public void setrID(UUID rID) {
        this.rID = rID;
    }

    public UUID getrID() {
        return rID;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

}
