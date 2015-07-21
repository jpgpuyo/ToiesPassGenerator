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
import android.test.AndroidTestCase;

import com.toies.jpuyo.toiespassgenerator.app.data.table.Table;
import com.toies.jpuyo.toiespassgenerator.app.data.table.TableFactory;

import org.json.JSONException;

public class TestJsonPlayerParser extends AndroidTestCase {

    public static final String LOG_TAG = TestJsonPlayerParser.class.getSimpleName();

    public void setUp() {
        deleteTheDatabase();
    }

    private void deleteTheDatabase() {
        mContext.deleteDatabase(ToiesDbHelper.DATABASE_NAME);
    }

    public void testLoadJsonToSqlite() throws JSONException {

        int recordsToInsert = 2;

        String jsonString =
                "{ \"player\" :" +
                        "[{\"number\":1,\"name\":\"Abelardo\"}," +
                        " {\"number\":2,\"name\":\"Abellan\"}" +
                        "]" +
                        "}";

        JsonPlayerParser jsql = new JsonPlayerParser(jsonString);
        ContentValues[] bulkInsertContentValues = jsql.getContentValues();

        TestUtilities.TestContentObserver weatherObserver = TestUtilities.getTestContentObserver();
        mContext.getContentResolver().registerContentObserver(PlayerContract.PlayerEntry.CONTENT_URI, true, weatherObserver);

        int insertCount = mContext.getContentResolver().bulkInsert(PlayerContract.PlayerEntry.CONTENT_URI, bulkInsertContentValues);

        weatherObserver.waitForNotificationOrFail();
        mContext.getContentResolver().unregisterContentObserver(weatherObserver);

        assertEquals(insertCount, recordsToInsert);

        // A cursor is your primary interface to the query results.
        Cursor cursor = mContext.getContentResolver().query(
                PlayerContract.PlayerEntry.CONTENT_URI,
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                PlayerContract.PlayerEntry.NUMBER + " ASC"  // sort order == by DATE ASCENDING
        );

        // we should have as many records in the database as we've inserted
        assertEquals(cursor.getCount(), recordsToInsert);

        // and let's make sure they match the ones we created
        cursor.moveToFirst();
        for ( int i = 0; i < recordsToInsert; i++, cursor.moveToNext() ) {
            TestUtilities.validateCurrentRecord("testLoadJsonToSqlite.  Error validating JsonToSqlite " + i,
                    cursor, bulkInsertContentValues[i]);
        }
        cursor.close();
    }
}
