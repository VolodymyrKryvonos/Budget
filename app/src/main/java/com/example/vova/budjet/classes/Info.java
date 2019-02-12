package com.example.vova.budjet.classes;

import java.util.Date;
import java.util.UUID;

public class Info {

    static private Date mDate;
    static private String moneyChange = null, shortDescribe, describe, choise;
    static private UUID id;


    public Info() {
        id = UUID.randomUUID();
        mDate = new Date();
    }

    public Date getDate() {
        return mDate;
    }

    public String getMoneyChange() {
        return moneyChange;
    }

    public String getShortDescribe() {
        return shortDescribe;
    }

    public String getDescribe() {
        return describe;
    }

    public String getChoise() {
        return choise;
    }

    public void setChoise(String choise) {
        this.choise = choise;
    }

    public UUID getId() {
        return id;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setMoneyChange(String moneyChange) {
        this.moneyChange = moneyChange;
    }

    public void setShortDescribe(String shortDescribe) {
        this.shortDescribe = shortDescribe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


}
