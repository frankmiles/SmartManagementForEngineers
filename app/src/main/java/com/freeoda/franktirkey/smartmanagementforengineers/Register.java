package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Objects;

public class Register extends AppCompatActivity {

    Spinner spinner_Register_collage,spinner_Register_branch,spinner_Register_Sem;

    TextView tv_Name,tv_email,tv_branch,tv_Sem,tv_RegNumber;
    EditText et_Name,et_email,et_RegNumber;
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

        btnRegister_Submit = findViewById(R.id.btnRegister_Submit);

        btnRegister_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Register.this,Login.class));
                setNewUserBackendless("xyz@gmail.com","abc","toom");

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
}