package com.toies.jpuyo.toiespassgenerator.app.data.table;

import android.content.ContentValues;

import java.util.HashSet;

public interface Table {
    String getName();
    HashSet<String> getColumnsSet();
    ContentValues createValuesForSingleInsert();
    ContentValues[] createValuesForBulkInsert();
    int getBulkInsertRecordsToInsert();
}

