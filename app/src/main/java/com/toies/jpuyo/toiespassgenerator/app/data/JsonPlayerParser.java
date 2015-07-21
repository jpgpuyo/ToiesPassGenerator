package com.toies.jpuyo.toiespassgenerator.app.data;

import android.content.ContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonPlayerParser extends JSONObject{

	public JsonPlayerParser(String jsonString) throws JSONException {
		super(jsonString);
	}
	
	public ContentValues[] getContentValues() throws JSONException {

		List<ContentValues> valuesList = new ArrayList<>();

		Iterator<?> keys = this.keys();
        while( keys.hasNext() ){
            String key = (String)keys.next();
            if( this.get(key) instanceof JSONArray ){
            	JSONArray rows = getJSONArray(key);
            	for(int i = 0; i<rows.length(); i++){
					JSONObject jsonObj = rows.getJSONObject(i);
					ContentValues playerValues = new ContentValues();

					Iterator<?> columns = jsonObj.keys();
					while( columns.hasNext() ){

						String columnName = (String)columns.next();
						String columnValue = jsonObj.get(columnName).toString();

						if (columnName.equals(PlayerContract.PlayerEntry.NUMBER)){
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
