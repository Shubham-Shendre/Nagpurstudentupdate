package com.nagstud.adnan.mobileapp_1;
//this class handle the fragment home layout

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Fragment_home extends Fragment {

    private RecyclerView horizontalList, horizontalList2, horizontalList3;
    private HorizontalListAdapter horizontalAdapter, horizontalAdapter2, horizontalAdapter3;

    //private RecyclerView verticalList; for vertical
    //private VerticalListAdapter verticalAdapter; for vertical
    //private RecyclerView recyclerView; for grid
    //private GridViewAdapter adapter;  for grid

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //for horizontallistview1
        horizontalList = (RecyclerView) view.findViewById(R.id.horizontal_recycler);
        horizontalList.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalList.setLayoutManager(horizontalManager);
        horizontalAdapter = new HorizontalListAdapter(getActivity());
        horizontalList.setAdapter(horizontalAdapter);

        //for horizontallistview2
        horizontalList2 = (RecyclerView) view.findViewById(R.id.horizontal_recycler2);
        horizontalList2.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalList2.setLayoutManager(horizontalManager2);
        horizontalAdapter2 = new HorizontalListAdapter(getActivity());
        horizontalList2.setAdapter(horizontalAdapter2);

        //for horizontallistview3
        horizontalList3 = (RecyclerView) view.findViewById(R.id.horizontal_recycler3);
        horizontalList3.setHasFixedSize(true);
        //set horizontal LinearLayout as layout manager to creating horizontal list view
        LinearLayoutManager horizontalManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalList3.setLayoutManager(horizontalManager3);
        horizontalAdapter3 = new HorizontalListAdapter(getActivity());
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
}
