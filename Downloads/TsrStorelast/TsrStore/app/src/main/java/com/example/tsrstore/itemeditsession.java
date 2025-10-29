package com.example.tsrstore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class itemeditsession {
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

    private static final String IS_PRODUCT = "isproduct";
    // Constructor
    public itemeditsession(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String id, String shopname, String shopcategory, String availableproducts, String productname, String price,String description){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
//php pagele variables aanu



        editor.putString("id", id);
        editor.putString("shopname", shopname);
        editor.putString("shopcategory", shopcategory);
        editor.putString("availableproducts", availableproducts);
        editor.putString("productname", productname);
        editor.putString("price", price);
        editor.putString("description", description);


        // commit changes
        editor.commit();
    }

    public boolean checkLogin(){
        // Check login status
        if(this.isLoggedIn()) {
            return true;
        } else {
            return false;

        }

    }
//    public void createproductSession(String id, String name, String category, String availableproducts, String productname, String price,String location,String description,String img){
//        // Storing login value as TRUE
//        editor.putBoolean(IS_PRODUCT, true);
////php pagele variables aanu
//
//
//
//        editor.putString("id", id);
//        editor.putString("shopname", name);
//        editor.putString("shopcategory", category);
//        editor.putString("availableproducts", availableproducts);
//        editor.putString("productname", productname);
//        editor.putString("price", price);
//        editor.putString("location", location);
//        editor.putString("description", description);
//        editor.putString("image", img);
//
//
//
//        // commit changes
//        editor.commit();
//    }


    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put("id",pref.getString("id",null));
        user.put("shopname",pref.getString("shopname",null));
        user.put("shopcategory",pref.getString("shopcategory",null));
        user.put("availableproducts",pref.getString("availableproducts",null));
        user.put("productname",pref.getString("productname",null));
        user.put("price",pref.getString("price",null));
        user.put("description",pref.getString("description",null));






        // return user
        return user;

    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}


