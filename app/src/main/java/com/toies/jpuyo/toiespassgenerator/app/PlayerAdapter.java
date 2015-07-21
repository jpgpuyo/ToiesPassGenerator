package com.toies.jpuyo.toiespassgenerator.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * {@link PlayerAdapter} exposes a list of Players
 * from a {@link Cursor} to a {@link android.widget.ListView}.
 */
public class PlayerAdapter extends CursorAdapter {

    private static final int VIEW_TYPE_COUNT = 1;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    /**
     * Cache of the children views for a forecast list item.
     */
    public static class ViewHolder {

        public final TextView descriptionView;

        public ViewHolder(View view) {
            descriptionView = (TextView) view.findViewById(R.id.list_item_player_name);
        }
    }

    public PlayerAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Choose the layout type
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        switch (viewType) {
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item_player;
                break;
            }
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String name = cursor.getString(PlayerFragment.COL_NAME);
        viewHolder.descriptionView.setText(name);
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }
}