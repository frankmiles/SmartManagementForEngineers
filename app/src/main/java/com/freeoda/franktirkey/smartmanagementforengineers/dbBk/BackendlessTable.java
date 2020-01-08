package com.freeoda.franktirkey.smartmanagementforengineers.dbBk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.LocalDb;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDB.User;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class BackendlessTable extends AppCompatActivity {

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

        User savingUser = BackendlessApplication.getUser();

        Backendless.Persistence.save(savingUser, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {


                testBk.setText("Successfull");
                startActivity(new Intent(BackendlessTable.this, LocalDb.class));

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                testBk.setText(fault.getDetail());

            }
        });

    }
}
