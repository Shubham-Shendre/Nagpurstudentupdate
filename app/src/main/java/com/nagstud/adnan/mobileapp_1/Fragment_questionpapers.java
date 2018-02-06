package com.nagstud.adnan.mobileapp_1;

import android.annotation.SuppressLint;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * Created by Shubham Dilip Shendre aka SdS
 * Adnan Kazi aka Addy
 * Furqan
 * Sadat Hussain
 * <p>
 * for NagStud LLP Project nagpurstudents
 */
public class Fragment_questionpapers extends Fragment {
    ListView listView4, listView5;

    List<Years> yearsList;
    List<Qpapers> qpapersList;

    String Subname;
    String facultysend="btech";
    String branchsend="cmps";
    Integer semsend=4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_questionpapers, container, false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //intializing the listview and the years list
        listView4 = (ListView) view.findViewById(R.id.listView1);
        yearsList = new ArrayList<>();
        //intializing the listview and the qpapers list
        listView5 = (ListView) view.findViewById(R.id.listView2);
        qpapersList = new ArrayList<>();
        loadYearList(view,semsend);

    }
    private void loadYearList(final View view, Integer semrec)
    {
        final String JSON_URL3 = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + "getqpaper" + "&&" + "faculty=" + facultysend + "&&" + "branch=" + branchsend + "&&" + "sem=" + semrec;
        System.out.println("under loadyearlist recive url="+JSON_URL3);
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
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
                            final ListViewAdapterYear adapter = new ListViewAdapterYear(yearsList, getActivity());
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
                                    Toast.makeText(getActivity(), "you clicked = "+year.getCode(), Toast.LENGTH_SHORT).show();
                                    loadPaperList(view,yearselect);
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

                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //adding the string request to request queue
        requestQueue.add(stringRequest2);
        return;
    }
    private void loadPaperList(View view,String year)
    {
        final String JSON_URL4 = "https://www.nagpurstudents.org/api/others/api.php?apicall=" + "papers" + "&&" + "faculty=" + facultysend + "&&" + "branch=" + branchsend + "&&" + "sem=" + semsend + "&&" + "year=" + year;
        System.out.println("under loadPaperlist created url="+JSON_URL4);
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
        //System.out.println(branchList);
        //making the progressbar visible
        progressBar.setVisibility(view.VISIBLE);
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
                            qpapersList.clear();
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
                            ListViewAdapterQpapers adapter2 = new ListViewAdapterQpapers(qpapersList, getActivity());
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

                                    Toast.makeText(getActivity(), "you clicked = "+qpaper.getName(), Toast.LENGTH_SHORT).show();
                                    //downloadFile(qpaper.getLink());
                                    String url = "https://www.nagpurstudents.org/"+qpaper.getLink();
                                    url = url.replaceAll(" ", "%20");
                                    System.out.println("final url="+url);
                                    Subname = qpaper.getName();

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

                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //adding the string request to request queue
        requestQueue.add(stringRequest2);

    }
}
