package com.example.tsrstore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagerUser {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Login";
    private static final String IS_LOGIN = "IsLoggedIn";

    // Constructor
    public SessionManagerUser(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    // Create login session
    public void createLoginSession(String id, String username, String email, String phone, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("id", id);
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("Address", password);
        editor.commit();
    }

    public boolean checkLogin() {
        return this.isLoggedIn();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put("id", pref.getString("id", null));
        user.put("username", pref.getString("username", null));
        user.put("email", pref.getString("email", null));
        user.put("phone", pref.getString("phone", null));
        user.put("Address", pref.getString("Address", null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.putBoolean(IS_LOGIN, false); // Explicitly set login state to false
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
