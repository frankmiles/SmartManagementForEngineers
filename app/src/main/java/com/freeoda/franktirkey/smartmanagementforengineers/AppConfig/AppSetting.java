package com.freeoda.franktirkey.smartmanagementforengineers.AppConfig;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;
import com.freeoda.franktirkey.smartmanagementforengineers.Login;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

import org.w3c.dom.DOMConfiguration;

public class AppSetting extends AppCompatActivity {

    Button btn_setting_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        btn_setting_Logout = findViewById(R.id.btn_setting_Logout);

        btn_setting_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        startActivity(new Intent(AppSetting.this, Login.class));
                        BackendlessApplication.setUserFromDB(null);
                        BackendlessApplication.setUser(null);
                        BackendlessApplication.setDb(null);
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        //TODO enter this error code into the DOC Code: 152
                        Toast.makeText(AppSetting.this,"Error ",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
