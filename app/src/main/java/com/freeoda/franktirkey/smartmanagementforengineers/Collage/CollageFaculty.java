package com.freeoda.franktirkey.smartmanagementforengineers.Collage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class CollageFaculty extends AppCompatActivity {

    TextView tv_collage_faculty_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_faculty);

        tv_collage_faculty_data = findViewById(R.id.tv_collage_faculty_data);

        tv_collage_faculty_data.setText(getIntent().getStringExtra("data"));
    }
}
