package com.galinc.hardtraining.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galinc.hardtraining.itility.Exercise;

import java.util.List;


@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM Exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM Exercise WHERE id = :id")
    Exercise getById(long id);

    @Insert
    void insert(Exercise exercise);

    @Insert
    void insert(List<Exercise> exercises);

    @Update
    void update(Exercise exercise);

    @Delete
    void delete(Exercise exercise);
}
