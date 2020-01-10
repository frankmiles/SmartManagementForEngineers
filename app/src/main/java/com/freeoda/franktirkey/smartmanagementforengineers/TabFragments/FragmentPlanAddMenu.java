package com.freeoda.franktirkey.smartmanagementforengineers.TabFragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlanAddMenu extends Fragment {

    View view;
    static EditText et_Plan_addTask;
    java.lang.String msg;
    Button btn_AddTask;

    public FragmentPlanAddMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_plan_add_menu, container, false);
        Context context = getContext();

        et_Plan_addTask = view.findViewById(R.id.et_Plan_addTask);
        btn_AddTask = view.findViewById(R.id.btn_AddTask);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btn_AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = et_Plan_addTask.getText().toString();;
                Fragment_Plan.list.add(msg);
                Fragment_Plan.mainAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
