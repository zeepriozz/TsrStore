package com.example.tsrstore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Login";
    private static final String IS_LOGIN = "IsLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String name, String category, String address, String phonenumber, String password, String img) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("id", id);
        editor.putString("shopname", name);
        editor.putString("shopcategory", category);
        editor.putString("address", address);
        editor.putString("shopphonenumber", phonenumber);
        editor.putString("password", password);
        editor.putString("logo", img);
        editor.commit();
    }

    public boolean checkLogin() {
        return this.isLoggedIn();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put("id", pref.getString("id", null));
        user.put("shopname", pref.getString("shopname", null));
        user.put("shopcategory", pref.getString("shopcategory", null));
        user.put("address", pref.getString("address", null));
        user.put("shopphonenumber", pref.getString("shopphonenumber", null));
        user.put("password", pref.getString("password", null));
        user.put("logo", pref.getString("logo", null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
