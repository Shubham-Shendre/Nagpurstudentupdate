package com.nagstud.adnan.mobileapp_1;
//this class file handle the answers tab fragment

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;
//this class is used to design the cart
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Fragment_answerpapers extends Fragment {
    // Declare Variables for the data -
    ListView list;
    ListViewAdapter adapter;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_answerpapers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //here fragmentanswer.java->gridviewadapter->fragmentanswer.xml

        //fucntion on button one
        Button button1 = (Button) view.findViewById(R.id.selectBranch);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // onclick of button 1
                final CharSequence[] branch = {"First Year common", "Computer science and engineering", "Civil Engineering", "Elcetrical Engineering", "Electronics and Telcommunication", "Mechanical Engineering"};
                // TODO Auto-generated method stub
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Select Branch").setItems(branch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String currentbranch = (String) branch[which];
                        Toast.makeText(getActivity(), "you selected " + branch[which] + " Branch", Toast.LENGTH_LONG).show();
                    }
                });
                builder1.show();
                //
            }
        });

        //function on button two
        Button button2 = (Button) view.findViewById(R.id.selectSem);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // onclick of button 1
                final CharSequence[] semister = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8", "Sem9", "Sem10", "Sem11", "Sem12"};
                // TODO Auto-generated method stub
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Select Semister").setItems(semister, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String currentsemister = (String) semister[which];
                        Toast.makeText(getActivity(), "You selected semister " + semister[which], Toast.LENGTH_LONG).show();
                    }
                });
                builder2.show();
                //
            }
        });
        //function on button two
        Button button3 = (Button) view.findViewById(R.id.getdata);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), getdata.class);
                startActivity(i);
            }
        });

        //get the list
        // Generate sample data into string arrays
        rank = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        country = new String[]{"China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan"};

        population = new String[]{"1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000"};

        flag = new int[]{R.drawable.china, R.drawable.india,
                R.drawable.unitedstates, R.drawable.indonesia,
                R.drawable.brazil, R.drawable.pakistan, R.drawable.nigeria,
                R.drawable.bangladesh, R.drawable.russia, R.drawable.japan};

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), rank, country, population, flag);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture ListView item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(getActivity(), Answers_Activity1.class);
                // Pass all data rank
                i.putExtra("rank", rank);
                // Pass all data country
                i.putExtra("country", country);
                // Pass all data population
                i.putExtra("population", population);
                // Pass all data flag
                i.putExtra("flag", flag);
                // Pass a single position
                i.putExtra("position", position);
                // Open SingleItemView.java Activity
                startActivity(i);
            }

        });
    }
}
