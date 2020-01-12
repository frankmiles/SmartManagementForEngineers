package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectMain extends AppCompatActivity {

    TextView syllabusMain_tv;
    RecyclerView rv_subject_main;

    List<SubjectMainModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_main);

        syllabusMain_tv = findViewById(R.id.SubjectMain_tv);
        rv_subject_main = findViewById(R.id.rv_subject_main);

        syllabusMain_tv.setText(getIntent().getStringExtra("data"));

        int imgRes = getResources().getIdentifier("act_bg", "drawable", getPackageName());
        int imgRes2 = getResources().getIdentifier("act_card2", "drawable", getPackageName());
        list.add(new SubjectMainModel(001,"FBMS",imgRes));
        list.add(new SubjectMainModel(002,"FBMS",imgRes2));

        rv_subject_main.setLayoutManager(new LinearLayoutManager(this));
        SubjectMainAdapter adapter = new SubjectMainAdapter(list);
        rv_subject_main.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
