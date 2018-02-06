package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 * Adnan Kazi aka Addy
 * Furqan
 * Sadat Hussain
 * <p>
 * for NagStud LLP Project nagpurstudents
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    Button open_dialog;
    Dialog d1;

    String query = "faculties";
    private String JSON_URL = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + query;

    List<String> list;
    //listview object
    ListView listView, listView2, listView3, listView4, listView5;
    long selection;
    //the facultylist where we will store all the facultyobjects after parsing json
    List<Faculty> facultyList, list1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //add the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add the actionbar
        ActionBar actionBar = getSupportActionBar();
        //add the title at the action bar
        actionBar.setTitle(getIntent().getStringExtra("string"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initializing listview and faculty list
        facultyList = new ArrayList<>();

        open_dialog = (Button) findViewById(R.id.btn_dialog);
       // list1 = loadFacultyList();
        open_dialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

                list1 = loadFacultyList();
                d1 = new Dialog(SettingsActivity.this);

                d1.setContentView(R.layout.custom_dialog);
                progressBar.setVisibility(View.VISIBLE);
                ListView list1;
                list1 = (ListView) d1.findViewById(R.id.list);
                ListViewAdapterFaculty adapter = new ListViewAdapterFaculty(facultyList, getApplicationContext());
                list1.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);

                list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Toast.makeText(getApplicationContext(), "item position" + position, Toast.LENGTH_LONG).show();
                    }
                });
                d1.show();
            }
        });
    }



    @Override
    public void onBackPressed() {
        finish();
    }

    private List<Faculty> loadFacultyList() {
        //getting the progressbar
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        System.out.println(facultyList);
        //making the progressbar visible
      //  progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                         //progressBar.setVisibility(View.INVISIBLE);

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            //we have the array named faculty inside the object
                            //so here we are getting that json array
                            JSONArray facultyArray = obj.getJSONArray("faculty");
                            //now looping through all the elements of the json array
                            facultyList.clear();
                            for (int i = 0; i < facultyArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject facultyObject = facultyArray.getJSONObject(i);
                                //creating a faculty object and giving them the values from json object
                                Faculty faculty = new Faculty(facultyObject.getString("id"), facultyObject.getString("name"), facultyObject.getString("code"), facultyObject.getString("trade"));
                                facultyList.add(faculty);



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "check internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);
        return facultyList;
    }
}
