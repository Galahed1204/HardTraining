package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity
public class ListTraining {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @Expose
    private Exercise exercise;

    @Expose
    private int itr;

    @Expose
    private int numberofitr;

    @Expose
    private int weight;

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setItr(int itr) {
        this.itr = itr;
    }

    public void setNumberofitr(int numberofitr) {
        this.numberofitr = numberofitr;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getItr() {
        return itr;
    }

    public int getNumberofitr() {
        return numberofitr;
    }

    public int getWeight() {
        return weight;
    }
}
