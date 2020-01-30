package com.freeoda.franktirkey.smartmanagementforengineers.AppConfig;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import java.io.File;

public class AppSetting extends AppCompatActivity {

    Button btn_setting_Logout;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        context = this;

        btn_setting_Logout = findViewById(R.id.btn_setting_Logout);

        btn_setting_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        startActivity(new Intent(AppSetting.this, Login.class));

                        deleteAllDB();
                        deleteAllSharedPref();

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

    protected void deleteAllDB(){
        Log.d("msgDB","Deleting DB...");
        BackendlessApplication.getDb().userDao().deleteAll();
        BackendlessApplication.getSubject_db().subjectDao().deleteAll();
        BackendlessApplication.getSyllabus_db().syllabusDao().deleteAll();
        Log.d("msgDB","Deleted DB!");
    }

    protected void deleteAllSharedPref(){

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                boolean flag =true;
                File sharedPreferenceFile = new File("/data/data/"+ getPackageName()+ "/shared_prefs/com.freeoda.franktirkey.smartmanagementforengineers_preferences.xml");
                flag = sharedPreferenceFile.delete();
                Log.d("msg","Deleted SharedPref! : "+ flag);
                return null;
            }
        }.execute();
    }
}