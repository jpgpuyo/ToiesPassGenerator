package com.toies.jpuyo.toiespassgenerator.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.toies.jpuyo.toiespassgenerator.app.data.JsonPlayerParser;
import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import org.json.JSONException;


public class MainActivity extends ActionBarActivity implements PlayerFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateDatabase();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);

        PlayerFragment playerFragment =  ((PlayerFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_player));
    }

    @Override
    public void onItemSelected(Uri dateUri) {

    }

    private void populateDatabase() {
        this.getContentResolver().delete(
                PlayerContract.PlayerEntry.CONTENT_URI,
                null,
                null
        );

        JsonPlayerParser jsonPlayerParser;
        try {
            jsonPlayerParser = new JsonPlayerParser(this);
            ContentValues[] bulkInsertContentValues = jsonPlayerParser.getContentValues();
            this.getContentResolver().bulkInsert(PlayerContract.PlayerEntry.CONTENT_URI, bulkInsertContentValues);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
