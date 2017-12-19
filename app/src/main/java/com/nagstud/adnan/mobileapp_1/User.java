package com.nagstud.adnan.mobileapp_1;

import android.util.Log;

import static android.content.ContentValues.TAG;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class User {

     private String username, email, password,flag;
    public User(String username, String email,String password,String flag) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.flag=flag;
    }

    public String getUsername() {
        Log.e(TAG,username);
        return username; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getFlag() {
        return flag;
    }

}