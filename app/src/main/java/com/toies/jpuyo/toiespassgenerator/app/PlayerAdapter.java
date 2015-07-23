package com.toies.jpuyo.toiespassgenerator.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

public class PlayerAdapter extends CursorAdapter {

    public static class ViewHolder {

        public final ImageView iconPasswordUsed;
        public final TextView playerName;

        public ViewHolder(View view) {
            iconPasswordUsed = (ImageView) view.findViewById(R.id.list_item_player_icon_used);
            playerName = (TextView) view.findViewById(R.id.list_item_player_name);
        }
    }

    public PlayerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_player, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String name = cursor.getString(PlayerFragment.COL_NAME);
        long playerRowId = cursor.getInt(PlayerFragment.COL_ID);
        int passwordUsed = cursor.getInt(PlayerFragment.COL_USED);

        if (passwordUsed == 1){
            viewHolder.iconPasswordUsed.setImageResource(R.drawable.ic_clear);
        }else{
            viewHolder.iconPasswordUsed.setImageResource(R.drawable.ic_light_rain);
        }
        viewHolder.playerName.setText(name);

        Button getPasswordButton = (Button) view.findViewById(R.id.list_item_player_btn_get_password);
        getPasswordButton.setTag(playerRowId);
        getPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                long playerRowId = (long)v.getTag();

                ContentValues updatedValues = new ContentValues();
                updatedValues.put(PlayerContract.PlayerEntry.USED, 1);

                mContext.getContentResolver().update(
                        PlayerContract.PlayerEntry.CONTENT_URI, updatedValues, PlayerContract.PlayerEntry._ID + "= ?",
                        new String[] { Long.toString(playerRowId)});
            }
        });

    }
}