package com.freeoda.franktirkey.smartmanagementforengineers.Subject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class SubjectMain extends AppCompatActivity {

    TextView syllabusMain_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_main);

        syllabusMain_tv = findViewById(R.id.SubjectMain_tv);

        syllabusMain_tv.setText(getIntent().getStringExtra("data"));
    }
}
