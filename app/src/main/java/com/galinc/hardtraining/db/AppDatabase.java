package com.galinc.hardtraining.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.itility.ListTraining;

@Database(entities = {Exercise.class, Document.class, ListTraining.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract DocumentDao documentDao();
}
