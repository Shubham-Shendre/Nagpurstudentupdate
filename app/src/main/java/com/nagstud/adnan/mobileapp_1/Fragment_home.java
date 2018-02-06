        package com.nagstud.adnan.mobileapp_1;
//this class handle the fragment home layout

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.Arrays;

/**
 * Created by Shubham Dilip Shendre aka SdS
 *            Adnan Kazi aka Addy
 *            Furqan
 *            Sadat Hussain
 *
 * for NagStud LLP Project nagpurstudents
 */
public class Fragment_home extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;

    private static final int MULTIPLE = 0;
    private UpdateDataAdapter mAdapter2;

    public Fragment_home() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //for list 1
        alName = new ArrayList<>(Arrays.asList("Engineering", "Btech", "Commerce", "Medicine", "Law"));
        alImage = new ArrayList<>(Arrays.asList(R.drawable.eng, R.drawable.btech, R.drawable.commerce, R.drawable.medicine, R.drawable.law));

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter(getContext(), alName, alImage);
        mRecyclerView.setAdapter(mAdapter);

        //for list 2
        alName = new ArrayList<>(Arrays.asList("Engineering", "Btech", "Commerce", "Medicine", "Law"));
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter2(getContext(), alName);
        mRecyclerView.setAdapter(mAdapter);

        //for list3
        alName = new ArrayList<>(Arrays.asList("FirstyearCommon", "CSE", "ETC", "EXTC", "MECH","CIVIL"));
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view3);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter2(getContext(), alName);
        mRecyclerView.setAdapter(mAdapter);
          }
    private ArrayList<UpdateData> getDataSet() {
        ArrayList results = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
            UpdateData obj = new UpdateData("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        return results;
    }

}
