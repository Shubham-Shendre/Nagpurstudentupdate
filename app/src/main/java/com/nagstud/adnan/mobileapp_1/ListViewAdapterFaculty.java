package com.nagstud.adnan.mobileapp_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class ListViewAdapterFaculty extends ArrayAdapter<Faculty> {

    //the faculty list that will be display
    private List<Faculty> facultyList;

    //the context object
    private Context mCtx;

    //here we are getting the facultylist and context
    //so while creating the object of this adapter class we need to give facultylist and context
    public ListViewAdapterFaculty(List<Faculty> facultyList, Context mCtx)
    {
        super(mCtx, R.layout.list_items, facultyList);
        this.facultyList = facultyList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
       // TextView textViewid = listViewItem.findViewById(R.id.textViewid);
        TextView textViewname = listViewItem.findViewById(R.id.textViewname);
     //  TextView textViewcode = listViewItem.findViewById(R.id.textViewcode);
       // TextView textViewtrade = listViewItem.findViewById(R.id.textViewtrade);

        //Getting the faculty for the specified position
        Faculty faculty = facultyList.get(position);

        //setting faculty values to textviews
       // textViewid.setText(faculty.getId());
        textViewname.setText(faculty.getName());
       // textViewcode.setText(faculty.getCode());
       // textViewtrade.setText(faculty.getTrade());

        //returning the listitem
        return listViewItem;
    }

}
