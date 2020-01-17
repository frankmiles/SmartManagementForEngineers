package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectMain extends AppCompatActivity {

    Spinner spinner_subject_main_sem,spinner_subject_main_branch;
    TextView syllabusMain_tv;
    RecyclerView rv_subject_main;
    SubjectMainAdapter adapter;
    String collageId;
    List<SubjectMainModel> list = new ArrayList<>();
    List<String> urlList = new ArrayList<>();
    String clickedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_main);

        syllabusMain_tv = findViewById(R.id.SubjectMain_tv);
        rv_subject_main = findViewById(R.id.rv_subject_main);

        spinner_subject_main_sem = findViewById(R.id.spinner_subject_main_sem);
        spinner_subject_main_branch = findViewById(R.id.spinner_subject_main_branch);

        interface_RvClickListner_Subject_Main onClickView = new interface_RvClickListner_Subject_Main() {
            @Override
            public void onClick(View view, int position) {
                clickedUrl = urlList.get(position);
                Intent intent = new Intent(SubjectMain.this, SyllabusMain.class);
                intent.putExtra("url",clickedUrl);
                intent.putExtra("name",list.get(position).subjName);
                startActivity(intent);
            }
        };

        rv_subject_main.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubjectMainAdapter(list,onClickView);
        rv_subject_main.setAdapter(adapter);

        List<String> list;
        list = Arrays.asList(getResources().getStringArray(R.array.BranchAbbr)); //Todo add Branch here
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(SubjectMain.this,android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_subject_main_branch.setAdapter(dataAdapter);

        getUserData(); //put here the loading process

        spinner_subject_main_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                extractSubj(collageId, spinner_subject_main_branch.getSelectedItem().toString(),
                        (spinner_subject_main_sem.getSelectedItem().toString()).substring(0,1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_subject_main_sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                extractSubj(collageId, spinner_subject_main_branch.getSelectedItem().toString(),
                        (spinner_subject_main_sem.getSelectedItem().toString()).substring(0,1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void getUserData(){
        String where = "email = '"+BackendlessApplication.getUser().getEmail()+"'";
        DataQueryBuilder dqb = DataQueryBuilder.create();
        dqb.setWhereClause(where);
        Backendless.Data.of("User").find(dqb, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {

                HashMap hm = (HashMap) response.get(0).get("collageId");
                Log.d("msg",hm.get("collageId").toString());
                collageId = hm.get("collageId").toString();
                spinner_subject_main_branch.setVisibility(View.VISIBLE);
                spinner_subject_main_sem.setVisibility(View.VISIBLE);

                extractSubj(collageId, spinner_subject_main_branch.getSelectedItem().toString(),
                        (spinner_subject_main_sem.getSelectedItem().toString()).substring(0,1)); //initial data-ploting
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msg",fault.getMessage());
            }
        });
    }

    public void extractSubj(final String collage, final String branch, final String sem){

        String query = "collageId = '"+collage+"' AND branchId = '"+branch+"' AND semester = '"+sem+"'";
//        String query = "collageId = '"+collage+"' AND branchId = '"+branch+"' AND semester = '"+sem+"'";

        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        dataQueryBuilder.setWhereClause(query);
        Backendless.Data.of("Semester").find(dataQueryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {

                if(response != null && response.size() != 0){
                    HashMap[] hm = (HashMap[]) response.get(0).get("subject");
                    int imgRes = getResources().getIdentifier("act_bg",
                            "drawable", getPackageName());
                    for(HashMap l:hm){
                        Log.d("msgAbs",l.get("pathUrl").toString());
                        list.add(new SubjectMainModel(001, l.get("name").toString(),imgRes));
                        urlList.add(l.get("pathUrl").toString());
                    }
                }else {
                    list.clear();
                    urlList.clear();
                    Log.d("msg","no subect found for:"+collage+
                            " "+branch+" "+sem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msgError",fault.getMessage());
            }
        });

    }
}
