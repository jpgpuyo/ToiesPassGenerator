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

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerFragment extends Fragment  {
    public static final String LOG_TAG = PlayerFragment.class.getSimpleName();
    private PlayerAdapter mPlayerAdapter;

    ArrayAdapter<String> mForecastAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;

    private static final String SELECTED_KEY = "selected_position";

    private static final int PLAYER_LOADER = 0;

    private static final String[] PLAYER_COLUMNS = {
            PlayerContract.PlayerEntry.TABLE_NAME + "." + PlayerContract.PlayerEntry._ID,
            PlayerContract.PlayerEntry.PLAYER_ID,
            PlayerContract.PlayerEntry.NAME
    };

    static final int COL_ID = 0;
    static final int COL_PLAYER_ID = 1;
    static final int COL_NAME = 2;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        void onItemSelected(Uri dateUri);
    }

    public PlayerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Abelardo",
                "Nadal",
                "Douglas",
                "Ezquerro",
                "Vermaelen",
                "Dehu",
                "Chygrinsky",
                "Ciric",
                "Korneiev",
                "Couto",
                "Dugarry",
                "Escaich",
                "Eskurza"
        };
        List<String> weekForecast = new ArrayList<>(Arrays.asList(data));
        mForecastAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_player,
                        R.id.list_item_player_name,
                        weekForecast);

        /*mPlayerAdapter = new PlayerAdapter(getActivity(), null, 0);*/

        View rootView = inflater.inflate(R.layout.player_fragment, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_player);
        mListView.setAdapter(mForecastAdapter);
        // We'll call our MainActivity
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // CursorAdapter returns a cursor at the correct position for getItem(), or null
                // if it cannot seek to that position.
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
                    String locationSetting = Utility.getPreferredLocation(getActivity());
                    ((Callback) getActivity())
                            .onItemSelected(PlayerContract.PlayerEntry.buildWeatherLocationWithDate(
                                    locationSetting, cursor.getLong(COL_WEATHER_DATE)
                            ));
                }
                mPosition = position;
            }*
        });*/

        // If there's instance state, mine it for useful information.
        // The end-goal here is that the user never knows that turning their device sideways
        // does crazy lifecycle related things.  It should feel like some stuff stretched out,
        // or magically appeared to take advantage of room, but data or place in the app was never
        // actually *lost*.
        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        /*getLoaderManager().initLoader(PLAYER_LOADER, null, this);*/
        super.onActivityCreated(savedInstanceState);
    }

    // since we read the location when we create the loader, all we need to do is restart things
    /*void onLocationChanged( ) {
        updateWeather();
        getLoaderManager().restartLoader(PLAYER_LOADER, null, this);
    }*/

    /*private void updateWeather() {
        //SunshineSyncAdapter.syncImmediately(getActivity());
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // When tablets rotate, the currently selected list item needs to be saved.
        // When no item is selected, mPosition will be set to Listview.INVALID_POSITION,
        // so check for that before storing.
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    /*@Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // This is called when a new Loader needs to be created.  This
        // fragment only uses one loader, so we don't care about checking the id.

        // To only show current and future dates, filter the query to return weather only for
        // dates after or including today.

        // Sort order:  Ascending, by date.
        String sortOrder = PlayerContract.PlayerEntry.COLUMN_DATE + " ASC";

        String locationSetting = Utility.getPreferredLocation(getActivity());
        Uri weatherForLocationUri = WeatherContract.WeatherEntry.buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        return new CursorLoader(getActivity(),
                weatherForLocationUri,
                PLAYER_COLUMNS,
                null,
                null,
                sortOrder);
    }*/

    /*@Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mPlayerAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }*/

   /* @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mPlayerAdapter.swapCursor(null);
    }*/
}