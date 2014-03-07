package com.nullpointerexception.hackathon.app;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Stephen on 18/02/14.
 */
public class SessionManager {

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;


    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences("Hackathon", 0);
        editor = sharedPreferences.edit();
        editor.commit();
    }

    public String createGCMKey(){
        SecureRandom random = new SecureRandom();
        String asd =new BigInteger(130, random).toString(32);
        editor.putString("GCMKEY",asd);
        editor.commit();
        return asd;
    }
    public String getGCMKey(){
        return sharedPreferences.getString("GCMKEY",null);
    }

}
