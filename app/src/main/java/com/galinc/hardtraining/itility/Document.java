package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Document {
    @PrimaryKey(autoGenerate = true)
    public long id;


    private String number;

    private String guid;

    private long date;
}
