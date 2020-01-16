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
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.local.UserIdStorageFactory;
import com.freeoda.franktirkey.smartmanagementforengineers.AbsTestingBKs.AbsTestingBks;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;

import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView tvUsername,tvPass,getTvUsername_feedback,tvPass_feedback;
    EditText etUsername,etPass;
    Button btnRegister,btnSignin;

    final String userObjectId = UserIdStorageFactory.instance().getStorage().get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        String m = "(CSE)COMPUTER SCIENCE AND ENGI"; ToDO this is the string Parser;
//        String x = m.substring(1,3);
//        System.out.println(x);

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

                String user = etUsername.getText().toString()+"";
                String pass = etPass.getText().toString()+"";
                btnSignin.setClickable(false);

                Backendless.UserService.login(user, pass, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(Login.this,"LogedIn",Toast.LENGTH_SHORT).show();
                        BackendlessApplication.backendlessUser = response;

                        getSubject();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Login.this,"toast No: 21"+fault.getDetail(),Toast.LENGTH_LONG).show();
                        Log.println(Log.ASSERT,"Backendless_error",fault.getDetail()+"");
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

                            getSubject();
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

    private void getSubject(){
        final String whereClause = "email = '"+BackendlessApplication.backendlessUser.getEmail()+"'";

        final DataQueryBuilder dqb = DataQueryBuilder.create();
        dqb.setWhereClause(whereClause);

        Backendless.Data.of("User").find(dqb, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                if (response != null){
                    Log.d("msg",response.toString()); //tODO FOR TESTING PURPOSE

                    User signedInUser = new User();
                    signedInUser.setUid(Integer.parseInt(response.get(0).get("uid").toString()));
                    signedInUser.setOwnerId(response.get(0).get("ownerId").toString());
                    signedInUser.setName(response.get(0).get("name").toString());
                    signedInUser.setEmail(response.get(0).get("email").toString());
                    signedInUser.setSemester(response.get(0).get("semester").toString());
                    signedInUser.setCollageId(response.get(0).get("collageId").toString());
                    signedInUser.setBranch(response.get(0).get("branch").toString());
                    signedInUser.setRegNo(response.get(0).get("regNo").toString());

                    BackendlessApplication.setUser(signedInUser);
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.d("msg","error: "+fault.getMessage()); //tODO FOR TESTING PURPOSE
            }
        });
    }
}
