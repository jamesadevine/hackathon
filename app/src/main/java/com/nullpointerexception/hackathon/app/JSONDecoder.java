package com.nullpointerexception.hackathon.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by James on 06/03/2014.
 */
public class JSONDecoder {
    public static ArrayList<PotHole> decodeJSON(String JSONObjects){
        ArrayList<PotHole> potHoles = new ArrayList<PotHole>();
        try{
            JSONArray js = new JSONArray(JSONObjects);
            Log.e("js",String.valueOf(js.length()));
            for (int i = 0; i < js.length(); i++){
                JSONObject potHoleJSON = js.getJSONObject(i);
                potHoles.add(new PotHole(potHoleJSON.getString("name"),potHoleJSON.getString("longname"),potHoleJSON.getString("description"),potHoleJSON.getDouble("latitude"),potHoleJSON.getDouble("longitude")));
            }
        }catch(Exception e){
            Log.e("stcktrace",Log.getStackTraceString(e));
        }

        return potHoles;
    }
}
