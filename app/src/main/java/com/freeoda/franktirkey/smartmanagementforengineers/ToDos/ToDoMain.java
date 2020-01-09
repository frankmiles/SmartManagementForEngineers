package com.freeoda.franktirkey.smartmanagementforengineers.ToDos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class ToDoMain extends AppCompatActivity {

    TextView tv_todo_main_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);

        tv_todo_main_data = findViewById(R.id.tv_todo_main_data);

        tv_todo_main_data.setText(getIntent().getStringExtra("data"));
    }
}
