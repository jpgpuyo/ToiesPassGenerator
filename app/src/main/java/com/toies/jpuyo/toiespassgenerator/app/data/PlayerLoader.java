package com.toies.jpuyo.toiespassgenerator.app.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

public class PlayerLoader {

    private static final String[] PLAYER_COLUMNS = {
            PlayerContract.PlayerEntry.TABLE_NAME + "." + PlayerContract.PlayerEntry._ID,
            PlayerContract.PlayerEntry.PLAYER_ID,
            PlayerContract.PlayerEntry.NAME,
            PlayerContract.PlayerEntry.PASSWORD_USED
    };

    public PlayerLoader(){}

    public Loader<Cursor> getAllPlayersSortedByName(Context context){

        return new CursorLoader(context,
                PlayerContract.PlayerEntry.CONTENT_URI,
                PLAYER_COLUMNS,
                null,
                null,
                PlayerContract.PlayerEntry.NAME + " ASC");
    }
}
