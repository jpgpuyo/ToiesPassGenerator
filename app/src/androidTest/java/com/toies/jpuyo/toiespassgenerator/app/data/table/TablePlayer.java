package com.toies.jpuyo.toiespassgenerator.app.data.table;

import android.content.ContentValues;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import java.util.HashSet;

public class TablePlayer implements Table {

    @Override
    public String getName() {
        return PlayerContract.PlayerEntry.TABLE_NAME;
    }

    @Override
    public HashSet<String> getColumnsSet() {
        final HashSet<String> columnsHashSet = new HashSet<>();
        columnsHashSet.add(PlayerContract.PlayerEntry.PLAYER_ID);
        columnsHashSet.add(PlayerContract.PlayerEntry.NAME);
        return columnsHashSet;
    }

    @Override
    public ContentValues createValuesForSingleInsert() {
        ContentValues playerValues = new ContentValues();
        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 13);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Claudio Bravo");
        return playerValues;
    }

    static private final int BULK_INSERT_RECORDS_TO_INSERT = 24;
    @Override
    public ContentValues[] createValuesForBulkInsert() {
        ContentValues[] valuesArray = new ContentValues[BULK_INSERT_RECORDS_TO_INSERT];
        ContentValues playerValues = new ContentValues();

        int i = 0;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 13);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Claudio Bravo");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 25);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Masip");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 1);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Marc-Andre ter Stegen");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 21);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Adriano");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 15);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Bartra");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 22);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Dani Alves");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 33);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Diawandou Diagne");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 16);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Douglas");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 18);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Alba");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 24);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jeremy Mathieu");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 3);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pique");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 23);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Thomas Vermaelen");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 5);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Busquets");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 30);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Alen Halilovic");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 8);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Iniesta");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 14);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Mascherano");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 12);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Rafinha");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 4);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Ivan Rakitic");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 20);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Sergi Roberto");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 10);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Lionel Messi");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 31);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Munir");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 11);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Neymar");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 7);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pedro");
        valuesArray[i]=playerValues;
        i++;

        playerValues.put(PlayerContract.PlayerEntry.PLAYER_ID, 9);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Luis Suarez");
        valuesArray[i]=playerValues;

        return valuesArray;
    }

    @Override
    public int getBulkInsertRecordsToInsert() {
        return BULK_INSERT_RECORDS_TO_INSERT;
    }
}
