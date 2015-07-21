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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.toies.jpuyo.toiespassgenerator.app.data.table.Table;
import com.toies.jpuyo.toiespassgenerator.app.data.table.TableFactory;

import java.util.ArrayList;
import java.util.HashSet;

public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();
    private final HashSet<String> tableNameHashSet = new HashSet<>();

    public void setUp() {
        deleteTheDatabase();
        tableNameHashSet.add(PlayerContract.PlayerEntry.TABLE_NAME);
    }

    private void deleteTheDatabase() {
        mContext.deleteDatabase(ToiesDbHelper.DATABASE_NAME);
    }

    public void testCreateDb() throws Throwable {
        SQLiteDatabase db = new ToiesDbHelper(this.mContext).getWritableDatabase();
        checkTables(db);
        checkColumns(db);
        db.close();
    }

    private void checkTables(SQLiteDatabase db) throws Throwable {
        assertEquals(true, db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );

        assertTrue("Error: Your database was created without the correct tables",
                tableNameHashSet.isEmpty());
    }

    private void checkColumns(SQLiteDatabase db) throws Throwable {

        TableFactory tableFactory = new TableFactory();
        ArrayList<Table> tableList = tableFactory.getAllTables();

        for (Table table : tableList) {
            Cursor c = db.rawQuery("PRAGMA table_info(" + table.getName() + ")", null);
            assertTrue("Error: This means that we were unable to query the database for table information from table " + table.getName(),
                    c.moveToFirst());

            HashSet<String> columnsHashSet = table.getColumnsSet();
            int columnNameIndex = c.getColumnIndex("name");
            do {
                String columnName = c.getString(columnNameIndex);
                columnsHashSet.remove(columnName);
            } while(c.moveToNext());
            assertTrue("Error: The database doesn't contain all of the required columns for the table " + table.getName(),
                    columnsHashSet.isEmpty());
        }
    }

    public void testInsertOneRecordForEachTable() {

        ToiesDbHelper dbHelper = new ToiesDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        TableFactory tableFactory = new TableFactory();
        ArrayList<Table> tableList = tableFactory.getAllTables();

        for (Table table : tableList) {
            ContentValues tableValues = table.createValuesForSingleInsert();
            long tableRowId = db.insert(table.getName(), null, tableValues);
            assertTrue(tableRowId != -1);

            Cursor tableCursor = db.query(
                    table.getName(),  // Table to Query
                    null, // leaving "columns" null just returns all the columns.
                    null, // cols for "where" clause
                    null, // values for "where" clause
                    null, // columns to group by
                    null, // columns to filter by row groups
                    null  // sort order
            );
            assertTrue( "Error: No Records returned from " + table.getName() +" query", tableCursor.moveToFirst() );

            TestUtilities.validateCurrentRecord("testInsertReadDb for table " + table.getName() + " failed to validate",
                    tableCursor, tableValues);

            assertFalse("Error: More than one record returned from " + table.getName() + " query",
                    tableCursor.moveToNext());

            tableCursor.close();
        }

        dbHelper.close();
    }
}
