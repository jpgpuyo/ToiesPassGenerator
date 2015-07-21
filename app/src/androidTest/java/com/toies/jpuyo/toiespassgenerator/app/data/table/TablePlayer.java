package com.toies.jpuyo.toiespassgenerator.app.data.table;

import android.content.ContentValues;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TablePlayer implements Table {

    @Override
    public String getName() {
        return PlayerContract.PlayerEntry.TABLE_NAME;
    }

    @Override
    public HashSet<String> getColumnsSet() {
        final HashSet<String> columnsHashSet = new HashSet<>();
        columnsHashSet.add(PlayerContract.PlayerEntry.NUMBER);
        columnsHashSet.add(PlayerContract.PlayerEntry.NAME);
        return columnsHashSet;
    }

    @Override
    public ContentValues createValuesForSingleInsert() {
        ContentValues playerValues = new ContentValues();
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 13);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Claudio Bravo");
        return playerValues;
    }

    static private final int BULK_INSERT_RECORDS_TO_INSERT = 24;
    @Override
    public ContentValues[] createValuesForBulkInsert() {
        ContentValues[] valuesArray = new ContentValues[BULK_INSERT_RECORDS_TO_INSERT];
        ContentValues playerValues = new ContentValues();

        int i = 0;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 13);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Claudio Bravo");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 25);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Masip");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 1);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Marc-Andre ter Stegen");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 21);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Adriano");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 15);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Bartra");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 22);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Dani Alves");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 33);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Diawandou Diagne");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 16);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Douglas");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 18);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Alba");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 24);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jeremy Mathieu");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 3);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pique");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 23);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Thomas Vermaelen");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 5);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Busquets");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 30);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Alen Halilovic");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 8);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Iniesta");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 14);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Mascherano");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 12);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Rafinha");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 4);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Ivan Rakitic");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 20);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Sergi Roberto");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 10);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Lionel Messi");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 31);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Munir");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 11);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Neymar");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 7);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pedro");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 9);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Luis Suarez");
        valuesArray[i]=playerValues;

        return valuesArray;
    }

    @Override
    public int getBulkInsertRecordsToInsert() {
        return BULK_INSERT_RECORDS_TO_INSERT;
    }
}
