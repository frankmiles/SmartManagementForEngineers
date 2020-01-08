package com.freeoda.franktirkey.smartmanagementforengineers.LocalDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.List;

public class LocalDb extends AppCompatActivity {

    EditText inp_name,inp_branch,inp_collage,inp_regNo,inp_email,inp_sem;
    TextView dis;
    Button btn_add,btn_dis,btn_delete;

    static AppDatabase db;
    static List<User> userFromDB;

    /*TODO DELETE THIS ACTIVITY BEFORE RELEASE
    THIS IS ONLY FOR TEST PURPOSE*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_local_db);

        inp_name = findViewById(R.id.inp_name);
        inp_email = findViewById(R.id.inp_email);
        inp_branch = findViewById(R.id.inp_branch);
        inp_collage = findViewById(R.id.inp_collage);
        inp_regNo = findViewById(R.id.inp_regNo);
        inp_sem = findViewById(R.id.inp_sem);

        dis = findViewById(R.id.dis);
        btn_add = findViewById(R.id.btn_add);
        btn_dis = findViewById(R.id.btn_dis);
        btn_delete = findViewById(R.id.btn_delete);

        db = Room.databaseBuilder(LocalDb.this,
                AppDatabase.class,"User")
                .fallbackToDestructiveMigration()
                .build();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new setLocalDB(LocalDb.this).execute();
            }
        });

        btn_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new getLocalDB(LocalDb.this).execute();
                    String message = getLocalDB.getName()+" "+ getLocalDB.getEmail()+" "
                            + getLocalDB.getBranch()+" "+ getLocalDB.getSem()+" "
                            + getLocalDB.getCollage()+" "+ getLocalDB.getRegNo();
                    dis.setText(message);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new delLocalDB(LocalDb.this).execute();
            }
        });
    }
}
