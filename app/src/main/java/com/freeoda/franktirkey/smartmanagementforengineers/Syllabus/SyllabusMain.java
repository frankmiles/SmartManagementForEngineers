package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SyllabusMain extends AppCompatActivity {

    TextView syllabusMain_tv,tv_cv_Syllabus_Main,tv_SubjectName_SyllabusMain;
    RecyclerView rv_syllabus_main_subjectSelect;
    FloatingActionButton fb_Syllabus_main;
    CardView cv_Syllabus_main;

    ShimmerFrameLayout container;

    List<SyllabusMainModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_main);

        tv_SubjectName_SyllabusMain = findViewById(R.id.tv_SubjectName_SyllabusMain);
        syllabusMain_tv = findViewById(R.id.syllabusMain_tv);
        syllabusMain_tv.setText(getIntent().getStringExtra("url"));

        rv_syllabus_main_subjectSelect = findViewById(R.id.rv_syllabus_main_subjectSelect);
        fb_Syllabus_main = findViewById(R.id.fb_Syllabus_main);
        cv_Syllabus_main = findViewById(R.id.cv_Syllabus_main);
        tv_cv_Syllabus_Main = findViewById(R.id.tv_cv_Syllabus_Main);

        tv_cv_Syllabus_Main.setText(getIntent().getExtras().getString("name"));



        container = findViewById(R.id.shimmer_view_container);
        container.setVisibility(View.GONE);


        list.add(new SyllabusMainModel(001,"Loading Content..."));
        list.add(new SyllabusMainModel(002,"Check your internet!"));
        list.add(new SyllabusMainModel(003,"'Even a Talent can be"));
        list.add(new SyllabusMainModel(004,"bitten by plan!'"));


        fb_Syllabus_main.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                rv_syllabus_main_subjectSelect.setVisibility(View.VISIBLE);
                cv_Syllabus_main.setVisibility(View.GONE);
                fb_Syllabus_main.setVisibility(View.GONE);
            }
        });

        interface_RecyclerViewClickListner_Syllabus onClickView = new interface_RecyclerViewClickListner_Syllabus() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                rv_syllabus_main_subjectSelect.setVisibility(View.GONE);
                cv_Syllabus_main.setVisibility(View.VISIBLE);
                fb_Syllabus_main.setVisibility(View.VISIBLE);
            }
        };

        rv_syllabus_main_subjectSelect.setLayoutManager(new LinearLayoutManager(this));
        SyllabusMainAdaper adaper = new SyllabusMainAdaper(list,onClickView);
        rv_syllabus_main_subjectSelect.setAdapter(adaper);
        adaper.notifyDataSetChanged();
    }
}
