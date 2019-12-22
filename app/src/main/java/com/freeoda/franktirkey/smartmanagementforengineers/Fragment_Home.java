package com.freeoda.franktirkey.smartmanagementforengineers;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    Fragment_Home_Main_rvAdapter home_main_adapter;
    RecyclerView rvHome;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context context = this.getActivity();

        View view = inflater.inflate(R.layout.fragment_fragment__home, container, false);

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

        rvHome = view.findViewById(R.id.rvHome);
        int numberOfColumns = 2;
        rvHome.setLayoutManager(new GridLayoutManager(context,numberOfColumns));
        home_main_adapter = new Fragment_Home_Main_rvAdapter(context,data);
        home_main_adapter.setClickListener(new Fragment_Home_Main_rvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(context,home_main_adapter.getItem(position),Toast.LENGTH_SHORT).show();
            }
        });

        rvHome.setAdapter(home_main_adapter);











        // Inflate the layout for this fragment
        return view;
    }

}
