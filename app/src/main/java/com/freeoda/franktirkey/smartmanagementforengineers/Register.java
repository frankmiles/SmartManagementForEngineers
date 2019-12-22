package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    TextView tv_Name,tv_email,tv_branch,tv_Sem,tv_RegNumber;
    EditText et_Name,et_email,et_RegNumber;
    Button btnRegister_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
                startActivity(new Intent(Register.this,Login.class));
                finish();
            }
        });
    }
}
