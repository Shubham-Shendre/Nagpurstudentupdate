package com.nagstud.adnan.mobileapp_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class GridViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        recyclerView = (RecyclerView) findViewById(R.id.recyle_view);
        recyclerView.setHasFixedSize(true);

        //set GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GridViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}