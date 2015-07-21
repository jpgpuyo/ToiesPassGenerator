package com.toies.jpuyo.toiespassgenerator.app.data;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonPlayerParser{

	private JSONObject jsonObject;

	public JsonPlayerParser(Context ctx) throws JSONException {
		String jsonString = generateJsonStringFromAssets(ctx);
		jsonObject = new JSONObject(jsonString);
	}

	private String generateJsonStringFromAssets(Context ctx) throws JSONException {
		String jsonString;
		try {
			InputStream is = ctx.getAssets().open("players.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			jsonString = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return jsonString;
	}

	public ContentValues[] getContentValues() throws JSONException {

		List<ContentValues> valuesList = new ArrayList<>();

		Iterator<?> keys = jsonObject.keys();
        while( keys.hasNext() ){
            String key = (String)keys.next();
            if( jsonObject.get(key) instanceof JSONArray ){
            	JSONArray rows = jsonObject.getJSONArray(key);
            	for(int i = 0; i<rows.length(); i++){
					JSONObject jsonObj = rows.getJSONObject(i);
					ContentValues playerValues = new ContentValues();

					Iterator<?> columns = jsonObj.keys();
					while( columns.hasNext() ){

						String columnName = (String)columns.next();
						String columnValue = jsonObj.get(columnName).toString();

						if (columnName.equals(PlayerContract.PlayerEntry.PLAYER_ID)){
							playerValues.put(columnName, Integer.parseInt(columnValue));
						}else{
							playerValues.put(columnName, columnValue);
						}
					}
					valuesList.add(playerValues);
				}
            }
        }

		return valuesList.toArray(new ContentValues[valuesList.size()]);
	}
}
