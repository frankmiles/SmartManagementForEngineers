package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.freeoda.franktirkey.smartmanagementforengineers.AbsTestingBKs.AbsTestingBks;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.delLocalDB;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.getLocalDB;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.setLocalDB;

public class Register extends AppCompatActivity {

    Spinner spinner_Register_collage,spinner_Register_branch,spinner_Register_Sem;

    TextView tv_Name,tv_email,tv_branch,tv_Sem,tv_RegNumber;
    EditText et_Name,et_email,et_RegNumber,et_pass;
    Button btnRegister_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        spinner_Register_collage = findViewById(R.id.spinner_Register_collage);
        spinner_Register_branch = findViewById(R.id.spinner_Register_branch);
        spinner_Register_Sem = findViewById(R.id.spinner_Register_Sem);

        tv_Name = findViewById(R.id.tv_Name);
        tv_email = findViewById(R.id.tv_email);
        tv_branch = findViewById(R.id.tv_branch);
        tv_Sem = findViewById(R.id.tv_Sem);
        tv_RegNumber = findViewById(R.id.tv_RegNumber);

        et_Name = findViewById(R.id.et_Name);
        et_email = findViewById(R.id.et_email);
        et_RegNumber = findViewById(R.id.et_RegNumber);
        et_pass = findViewById(R.id.et_pass);

        btnRegister_Submit = findViewById(R.id.btnRegister_Submit);

        btnRegister_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Register.this,Login.class));

                setNewUserBackendless(et_email.getText().toString(),et_pass.getText().toString(),et_Name.getText().toString());

                //finish();
            }
        });
    }

    private void setNewUserBackendless(@NonNull String email, @NonNull String password, @NonNull String name){
        BackendlessUser registerBackendless = new BackendlessUser();
        registerBackendless.setEmail(email);
        registerBackendless.setPassword(password);
        registerBackendless.setProperty("name",name);
        Backendless.UserService.register(registerBackendless, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {

                Toast.makeText(Register.this,"responce:-true ",Toast.LENGTH_SHORT).show();

                BackendlessApplication.getUser()
                        .setOwnerId(response.getProperty("ownerId").toString());
                BackendlessApplication.getUser()
                        .setName(response.getProperty("name").toString());
                BackendlessApplication.getUser()
                        .setRegNo(et_RegNumber.getText().toString());
                BackendlessApplication.getUser()
                        .setSemester(spinner_Register_Sem.getSelectedItem().toString());
                BackendlessApplication.getUser()
                        .setCollage(spinner_Register_collage.getSelectedItem().toString());
                BackendlessApplication.getUser()
                        .setEmail(response.getEmail());
                BackendlessApplication.getUser()
                        .setBranch(spinner_Register_branch.getSelectedItem().toString());

                BackendlessApplication
                        .db
                        .userDao()   //TODO this is for testing only Running on Main.Thread
                        .deleteAll();

                BackendlessApplication
                        .db
                        .userDao()  //TODO this is for testing only Running on Main.Thread
                        .insertAll(BackendlessApplication.getUser());

                uploadToBKs();

                new getLocalDB(Register.this).execute();
                startActivity(new Intent(Register.this, MainActivity.class));
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //super.handleFault(fault);
                String res = fault.getCode();
                if(fault.getCode().equalsIgnoreCase("3033")){
                    Toast.makeText(Register.this,"Email already taken",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Register.this,"responce:- "+res+" "+fault.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }

    public void uploadToBKs(){
        User savingUser = BackendlessApplication.getUser();
        Backendless.Persistence.save(savingUser, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {
                Toast.makeText(Register.this,"Data Uploaded",Toast.LENGTH_LONG);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Register.this,"Server Busy!",Toast.LENGTH_LONG);
            }
        });
    }
}