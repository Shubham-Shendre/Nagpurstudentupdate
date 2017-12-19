package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
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

public class SplashActivity extends Activity {
//    protected int _splashTime = 500;
  User u;
    public StringRequest stringRequest1;

    public static final String PREFS ="login";
    public static final String TAG = "SplashActivity";
    Intent intent;
    String    ser_u_email,ser_u_pass;
    public String u_name,u_email=" ",u_pass=" ";
    //String e=u.getEmail(),p=u.getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
            //Check if user is valid or not

       /* if (SharedPrefManager.getInstance(this).isLoggedIn()) {

            System.out.println("if condition before going to fullscreen");
            finish();
            startActivity(new Intent(this, FullscreenActivity.class));
        }*/

        System.out.println("Email "+SharedPrefManager.getInstance(this).getUser().getEmail());
        try {
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedPrefManager.getInstance(SplashActivity.this).getUser().getEmail()!=null)   {
                    validate1();
 //                   intent = new Intent(getApplication(), FullscreenActivity.class);
   //                 startActivity(intent);
                }   else    {

                    System.out.println("Fisrt time user");
                    intent = new Intent(getApplication(), LoginActivity.class);
                        finish();
                    startActivity(intent);
                }
                           }
        },2000);
            }
    catch(Exception ignored) { System.out.println("Exception Occurs "+ignored);
    }
           }



    public void validate1(){
        stringRequest1 = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                     System.out.println("inside on part of on response in Slash");
                            // converting response to json object
                             u_email=SharedPrefManager.getInstance(SplashActivity.this).getUser().getEmail();
                             u_pass=SharedPrefManager.getInstance(SplashActivity.this).getUser().getPassword();
                            System.out.println(u_email+u_pass);

                            JSONObject obj = new JSONObject(response);
                            JSONObject userJson = obj.getJSONObject("user");

                            //creating a new user object
                           ser_u_email= userJson.getString("email");
                             ser_u_pass= userJson.getString("password");

                            //if condition true from server
                            if ((ser_u_email==u_email)&&(ser_u_pass==u_pass)) {
                                System.out.println("if part of on response in SlashAct+Sucess");
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                System.out.println(obj.getString("message"));
                                finish();
                                startActivity(new Intent(SplashActivity.this,FullscreenActivity.class));

                                // Toast.makeText(mCtx, u_email+" "+u_password, Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println("else part of on response in slash+error");
                                System.out.println(obj.getString("message"));

                                Toast.makeText(SplashActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                                }
                        } catch (JSONException e) {
                            Toast.makeText(SplashActivity.this,"Exception",Toast.LENGTH_SHORT);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"error VOLLEY "+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",u_email);
                params.put("password",u_pass);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest1);

    }

    /*
    public void onBackPressed()
    {
        this.finish();
        super.onBackPressed();
    }
*/

}
