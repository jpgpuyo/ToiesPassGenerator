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

        String playerName = cursor.getString(cursor.getColumnIndex(PlayerContract.PlayerEntry.NAME));
        long playerRowId = cursor.getInt(cursor.getColumnIndex(PlayerContract.PlayerEntry._ID));
        int passwordUsed = cursor.getInt(cursor.getColumnIndex(PlayerContract.PlayerEntry.PASSWORD_USED));

        if (passwordUsed == 1){
            viewHolder.iconPasswordUsed.setImageResource(R.drawable.ic_circle_green);
        }else{
            viewHolder.iconPasswordUsed.setImageResource(R.drawable.ic_circle_red);
        }
        viewHolder.playerName.setText(playerName);

        Button getPasswordButton = (Button) view.findViewById(R.id.list_item_player_btn_get_password);
        getPasswordButton.setOnClickListener(new GetPasswordOnClickListener(playerRowId,playerName));
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

            mContext.getContentResolver().update(
                    PlayerContract.PlayerEntry.CONTENT_URI, updatedValues, PlayerContract.PlayerEntry._ID + "= ?",
                    new String[]{Long.toString(playerRowId)});
        }

        private String generatePassword() {
            PlayerPassword playerPassword = new PlayerPassword(playerName);
            return playerPassword.generatePassword();
        }

        private void sendPasswordNotification(String password) {
            new PasswordNotification(mContext, password).send();
        }
    }
}