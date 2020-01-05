package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    private Fragment_Home_Main_rvAdapter home_main_adapter;
    private RecyclerView rvHome;
    private FrameLayout home_framelayout_main;
    private TextView tv_rvHome;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = this.getActivity();

        View view = inflater.inflate(R.layout.fragment_fragment__home, container, false);
        View viewRv = inflater.inflate(R.layout.fragment_home_main_rv_layout, container, false);

        String[] data = {"Syllabus", "Group Dissuasion", "Attendance", "Approach Faculty", "Plannings", "Reach Collage",
                "Important Dates", "Set Goals", "Setting", "Need Help Or Report?", "About"};

        rvHome = view.findViewById(R.id.rvHome);
        home_framelayout_main = view.findViewById(R.id.home_framelayout_main);
        tv_rvHome = viewRv.findViewById(R.id.tv_rvHome);

        int numberOfColumns = 1;
        rvHome.setLayoutManager(new GridLayoutManager(context,numberOfColumns));
        home_main_adapter = new Fragment_Home_Main_rvAdapter(context,data);
        home_main_adapter.setClickListener(new Fragment_Home_Main_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(context,home_main_adapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        rvHome.setAdapter(home_main_adapter);

        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.println(Log.VERBOSE,"rvHome","working New State = "+newState); //TODO Testing Phase
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.println(Log.VERBOSE,"rvHome","working dx = "+dx+" dy = "+dy); //TODO Testing Phase
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
