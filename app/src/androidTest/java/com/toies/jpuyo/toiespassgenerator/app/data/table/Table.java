package com.toies.jpuyo.toiespassgenerator.app.data.table;

import android.content.ContentValues;

import java.util.HashSet;

public interface Table {
    public String getName();
    public HashSet<String> getColumnsSet();
    public ContentValues createValues();
}

