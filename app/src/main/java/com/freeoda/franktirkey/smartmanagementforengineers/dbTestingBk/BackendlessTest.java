package com.freeoda.franktirkey.smartmanagementforengineers.dbTestingBk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class BackendlessTest extends AppCompatActivity {

    TextView testBk;
    String displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backendless_test);

        testBk = findViewById(R.id.testBk);
        testBk.setText(displayData);

        Log.println(Log.VERBOSE,"testBackendless","Saving Data...");
        saveData();

    }

    public void saveData(){

        final PersonInfo personInfo = new PersonInfo();
        personInfo.setName("toom");
        personInfo.setSemester("3 Sem");
        personInfo.setBranch("CSE");
        personInfo.setCollage("GITA");
        personInfo.setRegNo("172149");

//        PersonInfo savedPersonInfo = Backendless.Persistence.save(personInfo);

        Backendless.Persistence.save(personInfo, new AsyncCallback<PersonInfo>() {
            @Override
            public void handleResponse(PersonInfo response) {

                testBk.setText("Successfull");

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                testBk.setText(fault.getDetail());

            }
        });

    }
}
