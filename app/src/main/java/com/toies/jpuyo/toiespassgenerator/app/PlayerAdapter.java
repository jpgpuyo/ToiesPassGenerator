package com.toies.jpuyo.toiespassgenerator.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        viewHolder.iconPasswordUsed.setImageResource(R.drawable.ic_fog);
        viewHolder.playerName.setText(name);

    }
}