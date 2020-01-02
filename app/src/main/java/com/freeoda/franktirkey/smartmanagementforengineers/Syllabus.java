package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class Syllabus extends AppCompatActivity {

    Spinner spinner_branch,spinner_sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);


        spinner_branch = findViewById(R.id.spinner_Branch);
        spinner_sem = findViewById(R.id.spinner_Sem);
    }
}
