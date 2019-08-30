package com.galinc.hardtraining.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.galinc.hardtraining.itility.Document;
import com.galinc.hardtraining.itility.DocumentWithTrainings;
import com.galinc.hardtraining.itility.ListTraining;

import java.util.List;

import io.reactivex.Flowable;

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

    @Query("SELECT * FROM document")
    Flowable<List<Document>> getAllData();

    @Query("SELECT * FROM document WHERE id = :id")
    Flowable<Document> getByIdFlow(long id);


    @Transaction
    @Query("SELECT * FROM document WHERE id = :id")
    LiveData<DocumentWithTrainings> loadDocumentBy(long id);

    @Transaction
    @Query("SELECT * FROM document WHERE id = :id")
    Flowable<DocumentWithTrainings> loadDocumentByIdFlow(long id);

    @Transaction @Query("SELECT * FROM document WHERE id = :id")
    DocumentWithTrainings getDocumentById(long id);
}
