package com.nagstud.adnan.mobileapp_1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.nagstud.adnan.mobileapp_1.URLs.URL_LOGIN;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Teststatus extends Activity {
    public String username,password;
    public String ur_email,ur_pass;
    private ProgressDialog progressDialog;
    public  String TAG ="LoginAct" ;
    ProgressBar progressBar;
    public StringRequest stringRequest;
    public String u_name,u_password,u_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail()!=null) {
                        ur_email = SharedPrefManager.getInstance(getApplicationContext()).getUser().getEmail();
                        ur_pass = SharedPrefManager.getInstance(getApplicationContext()).getUser().getPassword();
                        user_validate();
                    }
                    else{
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                    }
                }
            },2000);
        }
        catch(Exception ignored) { System.out.println("Exception Occurs "+ignored);

        }
    }

    public void user_validate(){

        //first getting the values
         username = ur_email;
         password = ur_pass;
        System.out.println("Userlogin in login activity");

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }

        if (TextUtils.isEmpty(password)) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
        showProgressDialog();
        //progressBar.setVisibility(View.VISIBLE);

        stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("on response in login ACt");

                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                System.out.println("if part of on response in loginact");

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), obj.getString("user"), Toast.LENGTH_SHORT).show();
                                    dismissProgressDialog();

                                finish();
                                startActivity(new Intent(getApplicationContext(), FullscreenActivity.class));

                            } else {
                                System.out.println("else part of on response in loginact");
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                               dismissProgressDialog();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                              Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                   dismissProgressDialog();
                            //    Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
