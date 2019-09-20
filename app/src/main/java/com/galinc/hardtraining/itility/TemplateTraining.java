package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.galinc.hardtraining.db.ListTrainingsConverter;

import java.util.List;

@Entity
public class TemplateTraining {

    @PrimaryKey(autoGenerate = true)
    public long id;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @TypeConverters({ListTrainingsConverter.class})
    public List<ListTraining> listTrainings;
}
