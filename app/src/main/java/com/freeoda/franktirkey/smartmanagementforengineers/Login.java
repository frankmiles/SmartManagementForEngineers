package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class Login extends AppCompatActivity {

    TextView tvUsername,tvPass,getTvUsername_feedback,tvPass_feedback;
    EditText etUsername,etPass;
    Button btnRegister,btnSignin;

    final String userObjectId = UserIdStorageFactory.instance().getStorage().get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUsername = findViewById(R.id.tvUsername);
        tvPass = findViewById(R.id.tvPass);
        getTvUsername_feedback = findViewById(R.id.tvUsername_feedback);
        tvPass_feedback = findViewById(R.id.tvPass_feedback);

        etUsername = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPass);

        btnRegister = findViewById(R.id.btnRegister);
        btnSignin = findViewById(R.id.btnSignin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,Register.class));
                finish();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUsername.getText().toString();
                String pass = etPass.getText().toString();
                btnSignin.setClickable(false);

                Backendless.UserService.login(user, pass, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(Login.this,"LogedIn",Toast.LENGTH_SHORT).show();
                        BackendlessApplication.backendlessUser = response;
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Login.this,"toast No: 21"+fault.getDetail(),Toast.LENGTH_LONG).show();
                        Log.println(Log.ASSERT,"Backendless_error",fault.getDetail());
                        btnSignin.setClickable(true);
                    }
                },true);
            }
        });

        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if(response){
                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(Login.this,"LogedIn",Toast.LENGTH_SHORT).show();
                            BackendlessApplication.backendlessUser = response;
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(Login.this,fault.getCode(),Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Login.this,fault.getCode(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
