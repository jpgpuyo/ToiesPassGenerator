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
package com.toies.jpuyo.toiespassgenerator.app;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;
import com.toies.jpuyo.toiespassgenerator.app.data.PlayerLoader;

public class PlayerFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener{

    private PlayerAdapter mPlayerAdapter;

    private ListView mListView;
    private SearchView mSearchView;
    private String mCurFilter;
    private boolean action_random_player_selected;

    private int mPosition = ListView.INVALID_POSITION;

    private static final String SELECTED_KEY = "selected_position";
    private static final int PLAYER_LOADER = 0;

    public PlayerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.player_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listview_player);
        mPlayerAdapter = new PlayerAdapter(getActivity(), null, 0);

        mListView.setAdapter(mPlayerAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String playerName = mPlayerAdapter.getPlayerName(position);
                long playerRowId =  mPlayerAdapter.getPlayerRowId(position);

                Resources resources = getActivity().getResources();
                Snackbar snackbar = Snackbar.make(view, playerName, Snackbar.LENGTH_LONG)
                                    .setAction(resources.getString(R.string.btn_get_password), new GetPasswordOnClickListener(playerRowId, playerName))
                                    .setActionTextColor(Color.RED);

                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(Color.BLACK);
                TextView textView = (TextView)snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        });

        mSearchView = (SearchView) rootView.findViewById(R.id.searchview_player);
        mSearchView.setFocusable(true);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.playerfragment, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.action_random_player);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_random_player) {
            action_random_player_selected = true;
            getLoaderManager().restartLoader(0, null, this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(PLAYER_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (action_random_player_selected){
            return new PlayerLoader().getARandomPlayer(getActivity());
        }
        if (mCurFilter != null) {
            return new PlayerLoader().getAllPlayersSortedAndFilteredByName(getActivity(), mCurFilter);
        }
        return new PlayerLoader().getAllPlayersSortedByName(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (action_random_player_selected){
            action_random_player_selected = false;
            data.moveToFirst();
            String playerName = data.getString(data.getColumnIndex(PlayerContract.PlayerEntry.NAME));
            mSearchView.setQuery(playerName,false);
            mSearchView.clearFocus();
        }else {
            mPlayerAdapter.swapCursor(data);
            if (mPosition != ListView.INVALID_POSITION) {
                mListView.smoothScrollToPosition(mPosition);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mPlayerAdapter.swapCursor(null);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String newFilter = !TextUtils.isEmpty(newText) ? newText : null;
        if (mCurFilter == null && newFilter == null) {
            return true;
        }
        if (mCurFilter != null && mCurFilter.equals(newFilter)) {
            return true;
        }
        mCurFilter = newFilter;
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    private class GetPasswordOnClickListener implements View.OnClickListener {

        long playerRowId;
        String playerName;

        public GetPasswordOnClickListener(long playerRowId, String playerName)
        {
            this.playerRowId = playerRowId;
            this.playerName = playerName;
        }

        @Override
        public void onClick(View v) {
            markPlayerAsUsed();
            String password = generatePassword();
            sendPasswordNotification(password);
        }

        private void markPlayerAsUsed() {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put(PlayerContract.PlayerEntry.PASSWORD_USED, 1);

            getActivity().getContentResolver().update(
                    PlayerContract.PlayerEntry.CONTENT_URI, updatedValues, PlayerContract.PlayerEntry._ID + "= ?",
                    new String[]{Long.toString(playerRowId)});
        }

        private String generatePassword() {
            PlayerPassword playerPassword = new PlayerPassword(playerName);
            return playerPassword.generatePassword();
        }

        private void sendPasswordNotification(String password) {
            new PasswordNotification(getActivity(), password).send();
        }
    }
}