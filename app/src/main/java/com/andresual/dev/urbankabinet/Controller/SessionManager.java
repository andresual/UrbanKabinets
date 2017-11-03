package com.andresual.dev.urbankabinet.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.andresual.dev.urbankabinet.Activity.BottomNavigationActivity;
import com.andresual.dev.urbankabinet.Activity.MainActivity;
import com.andresual.dev.urbankabinet.Activity.SignInActivity;

import java.util.HashMap;

/**
 * Created by andresual on 10/23/2017.
 */

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "userPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String TOKEN_KEY = "tokenkey";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //masukin token ke shared preference. kurang paham cek SignInActivity.class
    public void createLoginSession(String token){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(TOKEN_KEY, token);
        editor.commit();
    }

    public void checkLogin(){
        // Check login status
        if(this.isLoggedIn()){
            //jika login status user true, redirect ke intent dibawah
            Intent i = new Intent(_context, BottomNavigationActivity.class);
            // hapus semua sehingga tinggal fragment
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // gak paham
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // mulai
            _context.startActivity(i);
        }
    }

    //jika data ingin ditampilkan
    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> token = new HashMap<String, String>();
        token.put(TOKEN_KEY, pref.getString(TOKEN_KEY, null));

        boolean val = token.isEmpty();
        System.out.println("hashmap kosong ? " + val);

        return token;
    }

    public void logoutUser(){
        // hapus data di shared preference
        editor.clear();
        editor.commit();

        //redirect ke main activity
        Intent i = new Intent(_context, MainActivity.class);
        // kok ada ini lagi
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // brosing
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // mulai
        _context.startActivity(i);
    }

    // get status login
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}