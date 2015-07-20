/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.toies.jpuyo.toiespassgenerator.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract.PlayerEntry;

/**
 * Manages a local database for weather data.
 */
public class ToiesDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "toies.db";

    public ToiesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude
        final String SQL_CREATE_PLAYER_TABLE = "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
                PlayerEntry._ID + " INTEGER PRIMARY KEY," +
                PlayerEntry.NUMBER + " INTEGER NOT NULL, " +
                PlayerEntry.NAME + " TEXT NOT NULL " +
                " );";


        sqLiteDatabase.execSQL(SQL_CREATE_PLAYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
