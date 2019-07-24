package com.galinc.hardtraining.itility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Voting {
    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("guid")
    @Expose
    public String guid;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Voting(String title) {
        this.title = title;
    }
}
