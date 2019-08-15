package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Document {

    @PrimaryKey(autoGenerate = true)
    public long id;


    private String number;

    private String guid;

    private String date;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public String getGuid() {
        return guid;
    }

    public String getDate() {
        return date;
    }
}
