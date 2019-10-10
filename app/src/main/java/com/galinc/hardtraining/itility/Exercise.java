package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Exercise {
    @SerializedName("title")
    @Expose
    private String title;

    @PrimaryKey(autoGenerate = true)
    public long id;

    @SerializedName("guid")
    @Expose
    public String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
