package com.toies.jpuyo.toiespassgenerator.app;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toies.jpuyo.toiespassgenerator.app.data.PlayerContract;

public class PlayerAdapter extends CursorAdapter {

    private static final int PASSWORD_NOTIFICATION_ID = 3004;

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

        String name = cursor.getString(cursor.getColumnIndex(PlayerContract.PlayerEntry.NAME));
        long playerRowId = cursor.getInt(cursor.getColumnIndex(PlayerContract.PlayerEntry._ID));
        int passwordUsed = cursor.getInt(cursor.getColumnIndex(PlayerContract.PlayerEntry.PASSWORD_USED));

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
                updatedValues.put(PlayerContract.PlayerEntry.PASSWORD_USED, 1);

                mContext.getContentResolver().update(
                        PlayerContract.PlayerEntry.CONTENT_URI, updatedValues, PlayerContract.PlayerEntry._ID + "= ?",
                        new String[] { Long.toString(playerRowId)});

                int iconId = R.drawable.ic_clear;
                Resources resources = mContext.getResources();
                Bitmap largeIcon = BitmapFactory.decodeResource(resources, iconId);
                String title = mContext.getString(R.string.app_name);

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(mContext)
                                .setColor(mContext.getResources().getColor(R.color.sunshine_light_blue))
                                .setSmallIcon(iconId)
                                .setLargeIcon(largeIcon)
                                .setContentTitle(title)
                                .setContentText("TEST");

                NotificationManager mNotificationManager =
                        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(PASSWORD_NOTIFICATION_ID, mBuilder.build());
            }
        });

    }
}