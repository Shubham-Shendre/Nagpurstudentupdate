package com.nagstud.adnan.mobileapp_1;
//this class file handle the activity_answer1.xml file
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Answers_Activity1 extends AppCompatActivity {
    // Declare Variables for the data of
    TextView txtrank;
    TextView txtcountry;
    TextView txtpopulation;
    ImageView imgflag;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers1);
        //add the textview into class file add for display activity name
        TextView textView = findViewById(R.id.activityname);
        //send the text from class to xml for activity name textview
        textView.setText("A TextView in answer activity");
        //add the textview in class file show the buy text
        TextView textView2 = findViewById(R.id.buy);

        //add the buy now button into class file
        Button button1 = (Button) findViewById(R.id.buynow);
        //add the add to cart button into class file
        Button button2 = (Button) findViewById(R.id.addtocart);
        //add the toolbar into xml file activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set the default action for the arrow press back
        ActionBar actionBar = getSupportActionBar();
        //set the title for the action bar
        actionBar.setTitle(getIntent().getStringExtra(String.valueOf(country)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // insert the card view for buy now and add to cart button
        CardView cardView = (CardView) findViewById(R.id.card_view);

        // Retrieve data from fragment into answers_activity1 item click for display the data
        Intent i = getIntent();
        // Get a single position
        position = i.getExtras().getInt("position");
        // Get the list of rank
        rank = i.getStringArrayExtra("rank");
        // Get the list of country
        country = i.getStringArrayExtra("country");
        // Get the list of population
        population = i.getStringArrayExtra("population");
        // Get the list of flag
        flag = i.getIntArrayExtra("flag");

        // Locate the TextViews in singleitemview.xml
        txtrank = (TextView) findViewById(R.id.rank);
        txtcountry = (TextView) findViewById(R.id.country);
        txtpopulation = (TextView) findViewById(R.id.population);

        // Locate the ImageView in singleitemview.xml
        imgflag = (ImageView) findViewById(R.id.flag);

        // Load the text into the TextViews followed by the position
        txtrank.setText(rank[position]);
        txtcountry.setText(country[position]);
        txtpopulation.setText(population[position]);

        // Load the image into the ImageView followed by the position
        imgflag.setImageResource(flag[position]);
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
        }
        return super.onOptionsItemSelected(item);
    }

    //add menu item end
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    */
    // function for click of backpress  "<-" arrow
    @Override
    public void onBackPressed() {
        finish();
    }

    //function for buy now  button
    public void buynow(View view) {
        Intent intent = new Intent(Answers_Activity1.this, Answers_Activity2.class);
        startActivity(intent);
    }

    //fucntion for addtocart button
    public void addtocart(View view) {

    }

}