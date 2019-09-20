package com.galinc.hardtraining.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.itility.ListTraining;
import com.galinc.hardtraining.itility.TemplateTraining;

@Database(entities = {Exercise.class, Document.class, ListTraining.class, TemplateTraining.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract DocumentDao documentDao();
    public abstract TemplateDao templateTrainingDao();

}
