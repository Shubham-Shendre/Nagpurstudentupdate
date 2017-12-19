package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class getdata extends AppCompatActivity {
    String query = "faculties";
    //the URL having the json data
    private String JSON_URL = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + query;
    //listview object
    ListView listView, listView2, listView3, listView4;
    long selection;
    //the facultylist where we will store all the facultyobjects after parsing json
    List<Faculty> facultyList;
    //the branch list where we will store all the hero objects after parsing json
    List<Branch> branchList;
    //the Sem list where we will store all the hero objects after parsing json
    List<Integer> semList;

    List<Years> yearsList;

    String facultysend;
    String branchsend;
    Integer semsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);
        //initializing listview and faculty list
        listView = (ListView) findViewById(R.id.listView);
        facultyList = new ArrayList<>();
        //initializing listview and branch list
        listView2 = (ListView) findViewById(R.id.listView2);
        branchList = new ArrayList<>();
        //initializing listview and sem list
        listView3 = (ListView) findViewById(R.id.listView3);
        semList = new ArrayList<Integer>();
        //intializing the listview and the years list
        listView4 = (ListView) findViewById(R.id.listView3);
        yearsList = new ArrayList<>();
        //this method will fetch and parse the data
        loadFacultyList();
    }

    private void loadFacultyList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        System.out.println(facultyList);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            //we have the array named faculty inside the object
                            //so here we are getting that json array
                            JSONArray facultyArray = obj.getJSONArray("faculty");
                            //now looping through all the elements of the json array
                            for (int i = 0; i < facultyArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject facultyObject = facultyArray.getJSONObject(i);
                                //creating a faculty object and giving them the values from json object
                                Faculty faculty = new Faculty(facultyObject.getString("id"), facultyObject.getString("name"), facultyObject.getString("code"), facultyObject.getString("trade"));
                                //adding the faculty to facultylist
                                facultyList.add(faculty);
                            }
                            //creating custom adapter object
                            ListViewAdapterFaculty adapter = new ListViewAdapterFaculty(facultyList, getApplicationContext());
                            //adding the adapter to listview
                            listView.setAdapter(adapter);
                            // Capture ListView item click
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Faculty faculty = facultyList.get(position);
                                    // System.out.println(faculty.getCode());
                                    String query1 = "branches&&faculty=" + faculty.getCode();
                                    facultysend = faculty.getCode();
                                    System.out.println("faculties=" + faculty.getCode());
                                    //  System.out.println(query1);
                                    System.out.println("https://www.nagpurstudents.org/api/others/api.php?apicall=" + query1);
                                    //this method will fetch and parse the data
                                    loadBranchList("https://www.nagpurstudents.org/api/others/api.php?apicall=" + query1, facultysend);
                                }

                            });
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
    }

    private void loadBranchList(String URL, final String facultysend) {
        final String JSON_URL2 = URL;
        final String faculty = facultysend;
        //System.out.println("###################################" + faculty);
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //  System.out.println(branchList);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, JSON_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            //we have the array named branch inside the object
                            //so here we are getting that json array
                            JSONArray branchArray = obj.getJSONArray("branches");
                            //now looping through all the elements of the json array
                            branchList.clear();
                            for (int i = 0; i < branchArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject branchObject = branchArray.getJSONObject(i);
                                //System.out.println("branchobject" + branchObject);
                                //creating a branch object and giving them the values from json object
                                Branch branch = new Branch(branchObject.getString("id"), branchObject.getString("name"), branchObject.getString("code"), branchObject.getString("wrtf"), branchObject.getString("yors"), branchObject.getString("ans"));
                                //System.out.println("branch" + branch);
                                //adding the branch to branchlist
                                branchList.add(branch);
                                //System.out.print("branch list end");
                            }
                            //creating custom adapter object
                            final ListViewAdapterBranch adapter = new ListViewAdapterBranch(branchList, getApplicationContext());
                            //adding the adapter to listview
                            listView2.setAdapter(adapter);
                            // Capture ListView item click
                            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Branch branch = branchList.get(position);
                                    branchsend = branch.getCode();
                                    //System.out.println("\n selected branch =" + (branch.getName()));
                                    int max = Integer.parseInt(branch.getYors());
                                    int a = 0;
                                    semList.clear();
                                    int sem[] = new int[max];
                                    if (faculty.equalsIgnoreCase("btech")) {
                                        //System.out.println("//////////////////////////faculty = btech");
                                        if (branch.getCode().equalsIgnoreCase("cmn")) {
                                            //System.out.println("////////////////////////////////////////////////////cmn");
                                            for (int i = 1; i <= max; i++) {
                                                sem[a] = i;
                                                System.out.println("sem[a]" + sem[a]);
                                                semList.add(sem[a]);
                                                a++;
                                            }
                                        } else {
                                            System.out.println("/////////////////////////////////!= cmn");
                                            for (int i = 3; i <= max; i++) {

                                                sem[a] = i;
                                                System.out.println("sem[a]" + sem[a]);
                                                semList.add(sem[a]);
                                                a++;
                                            }
                                        }
                                    } else {
                                        //System.out.println("///////////////////////////////// faculty != btech");
                                        for (int i = 1; i <= max; i++) {
                                            sem[a] = i;
                                            System.out.println("sem[a]" + sem[a]);
                                            semList.add(sem[a]);
                                            a++;
                                        }
                                    }
                                    //System.out.println("\n semlist" + semList);
                                    loadSemList(JSON_URL2, semList);
                                }
                            });
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
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest2);
    }

    private void loadSemList(String url, final List<Integer> sem) {
        final String JSON_URL3 = url;
        //display the listview for the question paper
        ListView listView = (ListView) findViewById(R.id.listView3);
        //using the array adapter for the listview of the question paper
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getdata.this, android.R.layout.simple_list_item_1, sem);
        listView.setAdapter(adapter);
        //function to handle the click event from the list item ie question paper
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                semsend = adapter.getItem(position);
                //System.out.println("selected sem" + adapter.getItem(position));
                //System.out.println("https://www.nagpurstudents.org/api/others/api.php?apicall=" + "getqpaper" + "&&" + "faculty=" + facultysend + "&&" + "branch=" + branchsend + "&&" + "sem=" + semsend);
                String JSON_URL3 = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + "getqpaper" + "&&" + "faculty=" + facultysend + "&&" + "branch=" + branchsend + "&&" + "sem=" + semsend;
                loadYearList(JSON_URL3);
            }
        });
    }

    private void loadYearList(String url)
    {
        final String JSON_URL3 = url;
        //System.out.println(JSON_URL3);
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //  System.out.println(branchList);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, JSON_URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            //we have the array named branch inside the object
                            //so here we are getting that json array
                            JSONArray yearArray = obj.getJSONArray("qpaper");
                            //now looping through all the elements of the json array
                            yearsList.clear();
                            for (int i = 0; i < yearArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject yearObject = yearArray.getJSONObject(i);
                                System.out.println("yearobject" + yearObject);
                                //creating a branch object and giving them the values from json object
                                Years year = new Years(yearObject.getString("id"), yearObject.getString("name"), yearObject.getString("code"), yearObject.getString("wrtf"), yearObject.getString("wrtb"), yearObject.getString("wrtsory"));
                                System.out.println("year" + year);
                                //adding the branch to branchlist
                                yearsList.add(year);
                                System.out.print("year list end");
                            }
                            //creating custom adapter object
                            final ListViewAdapterYear adapter = new ListViewAdapterYear(yearsList, getApplicationContext());
                            //adding the adapter to listview
                            listView4.setAdapter(adapter);
                            // Capture ListView item click
                            listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Years year = yearsList.get(position);
                                    //System.out.println(year.getCode());
                                    Toast.makeText(getApplicationContext(), "you clicked = "+year.getCode(), Toast.LENGTH_SHORT).show();
                                }
                            });
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
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest2);

    }
}