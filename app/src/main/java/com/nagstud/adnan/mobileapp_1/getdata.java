package com.nagstud.adnan.mobileapp_1;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class getdata extends AppCompatActivity {
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;

    String Subname =null;
    String query = "faculties";
    //the URL having the json data
    private String JSON_URL = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + query;
    //listview object
    ListView listView, listView2, listView3, listView4, listView5;
    long selection;
    //the facultylist where we will store all the facultyobjects after parsing json
    List<Faculty> facultyList;
    //the branch list where we will store all the hero objects after parsing json
    List<Branch> branchList;
    //the Sem list where we will store all the hero objects after parsing json
    List<Integer> semList;

    List<Years> yearsList;

    List<Qpapers> qpapersList;

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
        //intializing the listview and the qpapers list
        listView5 = (ListView) findViewById(R.id.listView3);
        qpapersList = new ArrayList<>();

        //this method will fetch and parse the data
        loadFacultyList();
    }

    private List<Faculty> loadFacultyList() {
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
                                  //  loadBranchList("https://www.nagpurstudents.org/api/others/api.php?apicall=" + query1, facultysend);
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
        return facultyList;
    }

    private void loadBranchList(String URL, final String facultysend) {
        final String JSON_URL2 = URL;
        final String faculty = facultysend;
        System.out.println("under loadBranchlist recive url="+JSON_URL2);
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
        System.out.println("under loadsemlist recive url"+JSON_URL3);
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
        System.out.println("under loadyearlist recive url="+JSON_URL3);
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
                                    System.out.println(year.getCode());
                                    String yearselect = year.getCode();

                                    Toast.makeText(getApplicationContext(), "you clicked = "+year.getCode(), Toast.LENGTH_SHORT).show();
                                    loadPaperList(year.getCode());
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
    private void loadPaperList(String year)
    {
        final String JSON_URL4 = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + "papers" + "&&" + "faculty=" + facultysend + "&&" + "branch=" + branchsend + "&&" + "sem=" + semsend + "&&" + "year=" + year;

        System.out.println("under loadPaperlist created url="+JSON_URL4);
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //  System.out.println(branchList);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        //creating a string request to send request to the url
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, JSON_URL4,
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
                            JSONArray qpapersArray = obj.getJSONArray("papers");
                            //now looping through all the elements of the json array
                           // qpapersList.clear();
                            for (int i = 0; i < qpapersArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject qpapersObject = qpapersArray.getJSONObject(i);
                                System.out.println("papersobject" + qpapersObject);
                                //creating a branch object and giving them the values from json object
                                Qpapers getqpaper = new Qpapers(qpapersObject.getString("link"), qpapersObject.getString("name"));
                                System.out.println("Qpaper" + getqpaper.getName());
                                //adding the branch to branchlist
                                qpapersList.add(getqpaper);
                                System.out.print("Qpaper list end");
                            }
                            System.out.println("ssssssssssssssssss"+qpapersList);
                            //creating custom adapter object
                            ListViewAdapterQpapers adapter2 = new ListViewAdapterQpapers(qpapersList, getApplicationContext());
                            //adding the adapter to listview
                            System.out.println(adapter2);
                            listView5.setAdapter(adapter2);
                            // Capture ListView item click
                            listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Subname = null;
                                    Qpapers qpaper = qpapersList.get(position);
                                   // System.out.println(qpaper.getName());

                                    Toast.makeText(getApplicationContext(), "you clicked = "+qpaper.getName(), Toast.LENGTH_SHORT).show();
                                    //downloadFile(qpaper.getLink());
                                    String url = "https://www.nagpurstudents.org/"+qpaper.getLink();
                                    url = url.replaceAll(" ", "%20");
                                    System.out.println("final url="+url);
                                    Subname = qpaper.getName();
                                    new DownloadFileFromURL().execute(url);
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
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }
    class DownloadFileFromURL extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        @Override
        protected  String doInBackground(String... f_url){
            int count;
            try{
                URL url = new URL(f_url[0]);
                System.out.println();
                URLConnection connection = url.openConnection();
                connection.connect();

                int lenghtOfFile = connection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                String storageDir = String.valueOf((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
                //String storageDir = String.valueOf((Environment.getExternalStoragePublicDirectory(Environment.getExternalStorageState(/storage/sdcard1/yourpath))));
                String fileName = "/"+Subname;
                File imageFile = new File(storageDir+fileName);
                OutputStream output = new FileOutputStream(imageFile);

                byte data[] = new byte[1024];
                long total = 0;

                while((count = input.read(data)) != -1){
                    total += count;

                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    output.write(data, 0, count);
                }
                output.flush();

                output.close();
                input.close();
            }catch (Exception e){
                Log.e("Error: ", e.getMessage());
            }

            return null;

        }

        protected void onProgressUpdate(String... progress){
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url){
            dismissDialog(progress_bar_type);

            //String imagePath = Environment.getExternalStorageDirectory() + "/downloadedfile.jpg";
           // my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        }
    }

}