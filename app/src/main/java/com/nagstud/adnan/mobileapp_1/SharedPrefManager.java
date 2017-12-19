package com.nagstud.adnan.mobileapp_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nagstud.adnan.mobileapp_1.LoginActivity;
import com.nagstud.adnan.mobileapp_1.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.nagstud.adnan.mobileapp_1.URLs.URL_LOGIN;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
//here for this class we are using a singleton pattern

public class SharedPrefManager {

    private static final String TAG ="ShredPref " ;
    //the constants
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_FLAG = "keyflag";
    public String flag_email;
    public StringRequest stringRequest;
    public String u_email="",u_password="",u_name="";
    public String temp;
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null)
                ,sharedPreferences.getString(KEY_FLAG, null)

                );

    }


    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        System.out.println("Userlogin in Shared pref act");
        sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_FLAG,user.getFlag());
        System.out.println("KEY_Email"+KEY_EMAIL);
        editor.apply();
        System.out.println("flag_e"+temp);

    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        temp=getUser().getFlag();
        System.out.println("value of temp in isLoggedIn() in Shared pref "+temp);
        if (temp!="true") {

            System.out.println("if part of isloggedin in sharedpref");
           // validate();
            sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return true;
        }else{
           System.out.println("Else part of isLoggedIn in shared pref");
            return false;
        }
        }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
    public void validate(){
        stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting response to json object
                            u_email=getUser().getEmail();
                            u_password=getUser().getPassword();
                            JSONObject obj = new JSONObject(response);

                            //if condition true from server
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(mCtx, obj.getString("message"), Toast.LENGTH_SHORT).show();

                                System.out.println("if part of on response in Shared pref");
                               // Toast.makeText(mCtx, u_email+" "+u_password, Toast.LENGTH_SHORT).show();

                                //                                Toast.makeText(getApplicationContext(), obj.getString("user"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
/*                                JSONObject userJson = obj.getJSONObject("user");
                                Log.e(TAG,"after user json object");

                                //creating a new user object
                                u_name= userJson.getString("username");
                                u_email= userJson.getString("email");
                                u_password= userJson.getString("password");

                                if ((Objects.equals(u_email, getUser().getEmail()))&&(Objects.equals(u_password,getUser().getPassword()))){
                                System.out.println("Success");

                                }
                                else{
                                    System.out.println("Not completed");

                                }

*/

                            } else {

                                System.out.println("else part of on response in loginact");
                                Toast.makeText(mCtx, obj.getString("message"), Toast.LENGTH_SHORT).show();
                                mCtx.startActivity(new Intent(mCtx,LoginActivity.class));

                            }
                        } catch (JSONException e) {
                            Toast.makeText(mCtx,"Exception",Toast.LENGTH_SHORT);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mCtx, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mCtx, "No internet Connection", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",u_email);
                params.put("password",u_password);
                return params;
            }
        };

        VolleySingleton.getInstance(mCtx).addToRequestQueue(stringRequest);

    }
}