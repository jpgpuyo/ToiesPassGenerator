package com.toies.jpuyo.toiespassgenerator.app;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.toies.jpuyo.toiespassgenerator.app.data.JsonPlayerParser;
import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import org.json.JSONException;


public class MainActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateDatabaseIfNecessary();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);

        PlayerFragment playerFragment =  ((PlayerFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_player));
    }

    private void populateDatabaseIfNecessary() {

        if(!databaseIsPopulated())
        {
            populateDatabase();
            markDatabaseAsPopulated();
        }
    }

    private boolean databaseIsPopulated() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("database_populated",false);
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

    private void markDatabaseAsPopulated() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("database_populated",true);
        editor.apply();
    }
}
