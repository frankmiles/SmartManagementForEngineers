package com.freeoda.franktirkey.smartmanagementforengineers.Collage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

public class CollageMain extends AppCompatActivity {

    RecyclerView rv_collage_main;
    List<String> list = new ArrayList<>();

    static View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_main_layout);

        rv_collage_main = findViewById(R.id.rv_collage_main);

        list.add("Administration");
        list.add("Faculty");
        list.add("Accommodation");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv_collage_main.setLayoutManager(layoutManager);

        CollageMainAdapter adapter = new CollageMainAdapter(list,getApplicationContext());
        rv_collage_main.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,collageId,branch,email,finalData;
                name = BackendlessApplication.getUser().getName();
                collageId = BackendlessApplication.getUser().getEmail();
                branch = BackendlessApplication.getUser().getBranch();
                email = BackendlessApplication.getUser().getEmail();
                finalData = name.concat("\n").concat(collageId).concat("\n").concat(branch).concat("\n").concat(email);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,"test@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding");
                intent.putExtra(Intent.EXTRA_TEXT,finalData);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        };

    }
}
