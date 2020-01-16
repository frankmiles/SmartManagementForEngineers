package com.freeoda.franktirkey.smartmanagementforengineers.AbsTestingBKs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.getLocalDB;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbsTestingBks extends AppCompatActivity {
    TextView tv_AbsTestingBKs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_testing_bks);

        tv_AbsTestingBKs = findViewById(R.id.tv_AbsTestingBKs);

        Backendless.Data.of("User").find(new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
//                tv_AbsTestingBKs.setText(response.toString());
                String collageId = response.get(0).get("collageId").toString();
                String sem = response.get(0).get("semester").toString();
                String branch = response.get(0).get("branch").toString();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                tv_AbsTestingBKs.setText("Failed");
            }
        });




    }
}
