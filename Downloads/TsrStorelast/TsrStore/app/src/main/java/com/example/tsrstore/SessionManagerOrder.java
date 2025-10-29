package com.example.tsrstore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagerOrder {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Login";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";


    // Constructor
    public SessionManagerOrder(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String id, String size, String totalprice) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
//php pagele variables aanu
        editor.putString("id", id);
        editor.putString("size", size);
        editor.putString("totalprice", totalprice);



        // commit changes
        editor.commit();
    }

    public boolean checkLogin() {
        // Check login status
        if (this.isLoggedIn()) {
            return true;
        } else {
            return false;

        }

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put("id",pref.getString("id",null));
        user.put("size",pref.getString("size",null));
        user.put("totalprice",pref.getString("totalprice",null));




        // return user
        return user;

    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }


    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}