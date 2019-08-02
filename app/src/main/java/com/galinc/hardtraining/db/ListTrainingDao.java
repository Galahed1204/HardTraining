package com.galinc.hardtraining.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galinc.hardtraining.itility.Exercise;
import com.galinc.hardtraining.itility.ListTraining;

import java.util.List;

@Dao
public interface ListTrainingDao {
    @Query("SELECT * FROM listtraining")
    List<ListTraining> getAll();

    @Query("SELECT * FROM listtraining WHERE id = :id")
    ListTraining getById(long id);

    @Insert
    void insert(ListTraining listTraining);

    @Insert
    void insert(List<ListTraining> listTrainings);

    @Update
    void update(ListTraining listTraining);

    @Delete
    void delete(ListTraining listTraining);

    @Query("DELETE FROM listtraining")
    void deleteAll();
}
