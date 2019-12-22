package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView tvUsername,tvPass,getTvUsername_feedback,tvPass_feedback;
    EditText etUsername,etPass;
    Button btnRegister,btnSignin;

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

                startActivity(new Intent(Login.this,MainActivity.class));
                finish();

            }
        });
    }
}
