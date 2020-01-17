package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.Collage.Collage;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectMain extends AppCompatActivity {

    Spinner spinner_subject_main_sem,spinner_subject_main_branch;
    TextView syllabusMain_tv;
    RecyclerView rv_subject_main;
    SubjectMainAdapter adapter;

    List<SubjectMainModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_main);

        syllabusMain_tv = findViewById(R.id.SubjectMain_tv);
        rv_subject_main = findViewById(R.id.rv_subject_main);
        spinner_subject_main_sem = findViewById(R.id.spinner_subject_main_sem);
        spinner_subject_main_branch = findViewById(R.id.spinner_subject_main_branch);

        syllabusMain_tv.setText(getIntent().getStringExtra("data"));



        rv_subject_main.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubjectMainAdapter(list);
        rv_subject_main.setAdapter(adapter);

        extractSubj("10001","cse","1");
    }

    public void extractSubj(String collage,String branch,String sem){

        String query = "collage.name = '"+collage+"' AND branchId = '"+branch+"' AND semester = '"+sem+"'";

        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        dataQueryBuilder.setWhereClause(query);
        Backendless.Data.of("Semester").find(dataQueryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {

                HashMap[] hm = (HashMap[]) response.get(0).get("subject");

                int imgRes = getResources().getIdentifier("act_bg", "drawable", getPackageName());

                for(HashMap l:hm){
//                    Log.d("msgAbs",l.get("objectId").toString());
                    list.add(new SubjectMainModel(001, l.get("name").toString(),imgRes));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.d("msg",fault.getMessage());
            }
        });

    }
}
