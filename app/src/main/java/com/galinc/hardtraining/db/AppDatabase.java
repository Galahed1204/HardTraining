package com.galinc.hardtraining.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.Exercise;

@Database(entities = {Exercise.class, Document.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract DocumentDao documentDao();
}
