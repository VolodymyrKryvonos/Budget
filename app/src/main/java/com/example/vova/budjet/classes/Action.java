package com.example.vova.budjet.classes;

import java.util.Date;
import java.util.UUID;

public class Action {
    private String mInformation;
    private Date mDate;
    private int moneyChange;
    private UUID id;

    public Action(){
        id = UUID.randomUUID();
    }

    public String getInformation() {
        return mInformation;
    }

    public Date getDate() {
        return mDate;
    }

    public int getMoneyChange() {
        return moneyChange;
    }


    public UUID getId() {
        return id;
    }

}
