package com.toies.jpuyo.toiespassgenerator.app.data.table;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TableFactory {

    private HashMap<String, Table> tables;

    public TableFactory(){
        tables = new HashMap<String, Table>();
        tables.put(PlayerContract.PlayerEntry.TABLE_NAME, new TablePlayer());
    }

    public ArrayList<Table> getAllTables() {

        ArrayList<Table> tableList = new ArrayList<Table>();

        Iterator<Table> it = tables.values().iterator();
        while (it.hasNext()) {
            Table table = (Table) it.next();
            tableList.add(table);
        }

        return tableList;
    }
}
