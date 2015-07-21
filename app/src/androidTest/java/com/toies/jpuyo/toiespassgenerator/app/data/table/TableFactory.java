package com.toies.jpuyo.toiespassgenerator.app.data.table;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TableFactory {

    private HashMap<String, Table> tables;

    public TableFactory(){
        tables = new HashMap<>();
        tables.put(PlayerContract.PlayerEntry.TABLE_NAME, new TablePlayer());
    }

    public ArrayList<Table> getAllTables() {

        ArrayList<Table> tableList = new ArrayList<>();

        for (Table table : tables.values()) {
            tableList.add(table);
        }

        return tableList;
    }

    public Table getPlayerTable() {
        return tables.get(PlayerContract.PlayerEntry.TABLE_NAME);
    }

}
