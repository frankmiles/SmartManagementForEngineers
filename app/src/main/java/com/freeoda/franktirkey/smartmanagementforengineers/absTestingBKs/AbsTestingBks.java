package com.freeoda.franktirkey.smartmanagementforengineers.absTestingBKs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbsTestingBks extends AppCompatActivity {
    TextView tv_AbsTestingBKs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_testing_bks);

        tv_AbsTestingBKs = findViewById(R.id.tv_AbsTestingBKs);

        String query = "collageId = '10001' AND semester = '1'";
        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        dataQueryBuilder.setWhereClause(query);
        Backendless.Data.of("Semester").find(dataQueryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {

                HashMap[] hm = (HashMap[]) response.get(0).get("subject");

                for(HashMap l:hm){
                    Log.d("msgAbs",l.get("objectId").toString());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.d("msg",fault.getMessage());
            }
        });


    }
}
