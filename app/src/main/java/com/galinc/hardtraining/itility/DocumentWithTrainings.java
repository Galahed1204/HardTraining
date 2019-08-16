package com.galinc.hardtraining.itility;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class DocumentWithTrainings {
    @Embedded
    public Document document;

    @Relation(parentColumn = "id",entityColumn = "document_id")
    public List<ListTraining> listTraining;
}
