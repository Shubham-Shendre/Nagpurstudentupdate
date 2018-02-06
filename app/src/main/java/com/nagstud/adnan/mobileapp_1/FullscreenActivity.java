package com.nagstud.adnan.mobileapp_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class FullscreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Home", "Question papaer", "Answers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

    //add menu item end
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.menu_question_papers) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.menu_answer_papers) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.menu_syllabus) {
            Intent intent = new Intent(this, SyllabusActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        } else if (id == R.id.menu_download) {
            Intent intent = new Intent(this, DownloadActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        } else if (id == R.id.menu_subscribed) {
            Intent intent = new Intent(this, SubscribedActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        } else if (id == R.id.menu_events) {
            Intent intent = new Intent(this, EventsActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        }
        else if (id == R.id.menu_about_us) {
            Intent intent = new Intent(this, AboutusActivity.class);
            intent.putExtra("string", String.valueOf(item));
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
