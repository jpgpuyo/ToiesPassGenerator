package com.toies.jpuyo.toiespassgenerator.app;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity implements PlayerFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);

        PlayerFragment playerFragment =  ((PlayerFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_player));
    }

    @Override
    public void onItemSelected(Uri dateUri) {

    }
}
