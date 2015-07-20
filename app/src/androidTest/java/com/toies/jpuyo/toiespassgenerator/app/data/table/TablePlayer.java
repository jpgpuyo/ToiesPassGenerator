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
        final HashSet<String> columnsHashSet = new HashSet<String>();
        columnsHashSet.add(PlayerContract.PlayerEntry.NUMBER);
        columnsHashSet.add(PlayerContract.PlayerEntry.NAME);
        return columnsHashSet;
    }

    public ContentValues createValues() {
        ContentValues playerValues = new ContentValues();
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 13);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Claudio Bravo");
        /*playerValues.put(PlayerContract.PlayerEntry.NUMBER, 25);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Masip");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 1);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Marc-Andre ter Stegen");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 21);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Adriano");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 15);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Bartra");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 22);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Dani Alves");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 33);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Diawandou Diagne");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 16);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Douglas");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 18);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jordi Alba");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 24);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Jeremy Mathieu");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 3);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pique");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 23);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Thomas Vermaelen");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 5);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Busquets");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 30);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Alen Halilovic");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 8);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Iniesta");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 14);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Mascherano");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 12);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Rafinha");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 4);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Ivan Rakitic");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 20);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Sergi Roberto");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 10);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Lionel Messi");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 31);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Munir");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 11);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Neymar");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 7);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Pedro");
        playerValues.put(PlayerContract.PlayerEntry.NUMBER, 9);
        playerValues.put(PlayerContract.PlayerEntry.NAME, "Luis Suarez");*/
        return playerValues;
    }
}
