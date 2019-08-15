package com.galinc.hardtraining.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galinc.hardtraining.itility.Document;

import java.util.List;

@Dao
public interface DocumentDao {
    @Query("SELECT * FROM document")
    List<Document> getAll();

    @Query("SELECT * FROM document WHERE id = :id")
    Document getById(long id);

    @Insert
    void insert(Document listDocument);

    @Insert
    void insert(List<Document> listDocument);

    @Update
    void update(Document listDocument);

    @Delete
    void delete(Document listDocument);

    @Query("DELETE FROM document")
    void deleteAll();
}
