package com.nagstud.adnan.mobileapp_1;
//this class retrive the data from the userstorage and  view inside the activity_download.xml
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class DownloadActivity extends AppCompatActivity {
    private RecyclerView horizontalList, horizontalList2, horizontalList3;
    private HorizontalListAdapter horizontalAdapter, horizontalAdapter2, horizontalAdapter3;
    //private RecyclerView verticalList; for vertical
    //private VerticalListAdapter verticalAdapter; for vertical
    //private RecyclerView recyclerView; for grid
    //private GridViewAdapter adapter;  for grid

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        //insert the textview in class to show the activity name
//        TextView textView = findViewById(R.id.text_view);
//        textView.setText("A TextView in download activity");
        //add the toolbar at the top of the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add the arrow to finsh or add the goback support
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra("string"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for horizontallistview1
        horizontalList = (RecyclerView) findViewById(R.id.horizontal_recycler);
        horizontalList.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalList.setLayoutManager(horizontalManager);
        horizontalAdapter = new HorizontalListAdapter(this);
        horizontalList.setAdapter(horizontalAdapter);

        //for horizontallistview2
        horizontalList2 = (RecyclerView) findViewById(R.id.horizontal_recycler2);
        horizontalList2.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalList2.setLayoutManager(horizontalManager2);
        horizontalAdapter2 = new HorizontalListAdapter(this);
        horizontalList2.setAdapter(horizontalAdapter2);

        //for horizontallistview3
        horizontalList3 = (RecyclerView) findViewById(R.id.horizontal_recycler3);
        horizontalList3.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalList3.setLayoutManager(horizontalManager3);
        horizontalAdapter3 = new HorizontalListAdapter(this);
        horizontalList3.setAdapter(horizontalAdapter3);

        //        for horizontallistview1
//        horizontalList = (RecyclerView) view.findViewById(R.id.horizontal_recycler);
//        horizontalList.setHasFixedSize(true);
//        set horizontal LinearLayout as layout manager to creating horizontal list view
//        LinearLayoutManager horizontalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        horizontalList.setLayoutManager(horizontalManager);
//        horizontalAdapter = new HorizontalListAdapter(getActivity());
//        horizontalList.setAdapter(horizontalAdapter);

//         for verticallistview
//         verticalList = (RecyclerView) view.findViewById(R.id.recyle_view);
//         verticalList.setHasFixedSize(true);
//         set vertical LinearLayout as layout manager for vertial listview
//         LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//         verticalList.setLayoutManager(layoutManager);
//         verticalAdapter = new VerticalListAdapter(getActivity());
//         verticalList.setAdapter(verticalAdapter);


//        for grid
//        recyclerView = (RecyclerView)findViewById(R.id.recyle_view);
//        recyclerView.setHasFixedSize(true);
//        set GridLayoutManager
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new GridViewAdapter(this);
//        recyclerView.setAdapter(adapter);
    }

    //add the menu item in action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        } else if (id == R.id.action_share) {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nagpurstudents");
            startActivity(Intent.createChooser(i, "Share via"));
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
