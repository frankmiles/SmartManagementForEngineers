package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.backendless.persistence.DataQueryBuilder;
import com.freeoda.franktirkey.smartmanagementforengineers.Collage.Collage;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.User;
import com.freeoda.franktirkey.smartmanagementforengineers.LocalDBForBKs.getLocalDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.callback.PasswordCallback;

public class Register extends AppCompatActivity {

    private boolean MainFlag = false;

    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-])(?=\\S+$).{8,24}$";

    Spinner spinner_Register_collage,spinner_Register_branch,spinner_Register_Sem;

    TextView tv_Name,tv_email,tv_branch,tv_Sem,tv_RegNumber,tv_PassHint;
    EditText et_Name,et_email,et_RegNumber,et_pass;
    Button btnRegister_Submit;


    String getSelectedCollageObjId;
    List<Collage> collageFromBKs = new ArrayList<>();
    List<String> collageNameList = new ArrayList<>();
    int SelectedCollagePosition=0;
    ArrayAdapter<String> CollageDataAdapter;
    ArrayAdapter<String> BranchDataAdapter;
    ArrayAdapter<String> SemDataAdapter;

    BackendlessUser registerBackendless = new BackendlessUser();

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
        tv_PassHint = findViewById(R.id.tv_PassHint);

        et_Name = findViewById(R.id.et_Name);
        et_email = findViewById(R.id.et_email);
        et_RegNumber = findViewById(R.id.et_RegNumber);
        et_pass = findViewById(R.id.et_pass);

        btnRegister_Submit = findViewById(R.id.btnRegister_Submit);
        btnRegister_Submit.setAlpha(0);

        btnDisable();
        tv_PassHint.setSelected(true);

        BranchDataAdapter = new ArrayAdapter<String>(Register.this, R.layout.spinner_txt_values,
                getResources().getStringArray(R.array.Branch));
        BranchDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Register_branch.setAdapter(BranchDataAdapter);

        SemDataAdapter = new ArrayAdapter<String>(Register.this, R.layout.spinner_txt_values,
                getResources().getStringArray(R.array.Sem));
        SemDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Register_Sem.setAdapter(SemDataAdapter);

        CollageData(); //setting spinner collage

        btnRegister_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validRegistration();

                if(MainFlag)
                {
                    setNewUserBackendless(et_email.getText().toString().trim(),
                            et_pass.getText().toString().trim(),et_Name.getText().toString().trim());
                }

            }
        });

        spinner_Register_collage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                getSelectedCollageObjId = collageFromBKs.get(i).getObjectId();
                SelectedCollagePosition = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        et_pass.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
//                PorterDuff.Mode.SRC_ATOP);
//        tv_PassHint.setText(et_pass.getText().toString().trim());
//        validRegistration();

        et_Name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                validRegistration();

                return false;
            }
        });

        et_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                validRegistration();
            }
        });

        et_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                validRegistration();

                return false;
            }
        });

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                validRegistration();
            }
        });

        et_RegNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                validRegistration();

                return false;
            }
        });

        et_RegNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                validRegistration();
            }
        });

        et_pass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                validRegistration();

                return false;
            }
        });

        et_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                validRegistration();
            }
        });
    }

    private void setNewUserBackendless(@NonNull String email, @NonNull String password, @NonNull String name){

        registerBackendless.setEmail(email);
        registerBackendless.setPassword(password);
        registerBackendless.setProperty("name",name);
        Backendless.UserService.register(registerBackendless, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {

                Toast.makeText(Register.this,"responce:-true ",Toast.LENGTH_SHORT).show();

                BackendlessApplication
                        .getUser()
                        .setOwnerId(response
                                .getProperty("ownerId")
                                .toString().trim());
                BackendlessApplication
                        .getUser()
                        .setName(response
                                .getProperty("name")
                                .toString().trim());
                BackendlessApplication
                        .getUser()
                        .setRegNo(et_RegNumber
                                .getText()
                                .toString().trim());
                BackendlessApplication
                        .getUser()
                        .setSemester(spinner_Register_Sem
                                .getSelectedItem()
                                .toString().trim());
                BackendlessApplication
                        .getUser()
                        .setCollageId(getSelectedCollageObjId);
                BackendlessApplication
                        .getUser()
                        .setEmail(response
                                .getEmail());
                BackendlessApplication
                        .getUser()
                        .setBranch(spinner_Register_branch
                                .getSelectedItem()
                                .toString().trim());

                BackendlessApplication
                        .db
                        .userDao()   //TODO this is for testing only Running on Main.Thread
                        .deleteAll();

                BackendlessApplication
                        .db
                        .userDao()  //TODO this is for testing only Running on Main.Thread
                        .insertAll(BackendlessApplication.getUser());

                uploadToBKs();

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
        User savingUser = new User();

        savingUser
                .setEmail(BackendlessApplication
                        .getUser()
                        .getEmail());
        savingUser
                .setOwnerId(BackendlessApplication
                        .getUser()
                        .getOwnerId());
        savingUser
                .setUid(BackendlessApplication
                        .getUser()
                        .getUid());
        savingUser
                .setCollageId(getSelectedCollageObjId);
        savingUser
                .setName(BackendlessApplication
                        .getUser()
                        .name);
        savingUser
                .setRegNo(BackendlessApplication
                        .getUser()
                        .getRegNo());
        savingUser
                .setBranch(BackendlessApplication
                        .getUser()
                        .getBranch());
        savingUser
                .setSemester(BackendlessApplication
                        .getUser()
                        .getSemester());

        Backendless.Persistence.save(savingUser, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {
                Toast.makeText(Register.this,"Data Uploaded",Toast.LENGTH_LONG).show();
                Log.d("msgBks","user table added");
                BksUserTable();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Register.this,"Server Busy!",Toast.LENGTH_LONG).show();
                Log.d("msgBks","user table failed to add"+fault.getMessage());
            }
        });
    }

    void CollageData(){

        Backendless.Data.of(Collage.class).find(new AsyncCallback<List<Collage>>() {
            @Override
            public void handleResponse(List<Collage> response) {
                Log.d("msg","Got collage data");
                collageFromBKs = response;

                for(int i = 0;i<response.size();i++){    //Fetching Collage
                    collageNameList.add(response.get(i).getName());
                }
                CollageDataAdapter = new ArrayAdapter<String>(Register.this, R.layout.spinner_txt_values,collageNameList);
                CollageDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Register_collage.setAdapter(CollageDataAdapter);

                btnRegister_Submit.setAlpha(1); //This is to be validated to the Input
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Log.d("msg","error: "+fault.getMessage());
            }
        });
    }

    private void btnDisable(){
        btnRegister_Submit.setEnabled(false);
        btnRegister_Submit.setBackgroundColor(getResources().getColor(R.color.colorLightPrimary));
    }

    private void btnEnable(){
        btnRegister_Submit.setEnabled(true);
        btnRegister_Submit.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    public void validRegistration(){

        boolean flagName,flagEmail,flagCollage_RegNo,flagPassword;
        flagName=false;
        flagEmail=false;
        flagCollage_RegNo=false;
        flagPassword=false;

        String name = et_Name != null ? et_Name.getText().toString().trim() : "";
        String email = et_email != null ? et_email.getText().toString().trim() : "";
        int collage_RegNo = et_pass != null ? (et_RegNumber.getText().toString().trim().equals("") ?
                0 : Integer.parseInt(et_RegNumber.getText().toString().trim())) : 0;
        String passWord = et_pass != null ? et_pass.getText().toString().trim():"";

        if(Pattern.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",name)){
            flagName = true;
            et_Name.getBackground().setColorFilter(getResources().getColor(android.R.color.holo_green_light),
                    PorterDuff.Mode.SRC_ATOP);
        }
        else {
            //Name Field Error
            Log.d("msg","Error in Name: "+ name);
            MainFlag = false;
            et_Name.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP);
            btnDisable();

        }
        if (Pattern.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$",email)){
            flagEmail = true;
            et_email.getBackground().setColorFilter(getResources().getColor(android.R.color.holo_green_light),
                    PorterDuff.Mode.SRC_ATOP);
        }
        else {
            //Email Field Error
            Log.d("msg","Error in Email: "+ email);
            MainFlag = false;
            et_email.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP);
            btnDisable();

        }
        if(collage_RegNo != 0){
            flagCollage_RegNo = true;
            et_RegNumber.getBackground().setColorFilter(getResources().getColor(android.R.color.holo_green_light),
                    PorterDuff.Mode.SRC_ATOP);
        }
        else {
            //Collage Reg Error
            Log.d("msg","Error in CollageRegNo: "+collage_RegNo);
            MainFlag = false;
            et_RegNumber.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP);
            btnDisable();
        }

        if(Pattern.compile(PASSWORD_PATTERN).matcher(passWord).matches()){ //Matches the password Pattern
            flagPassword = true;
            et_pass.getBackground().setColorFilter(getResources().getColor(android.R.color.holo_green_light),
                    PorterDuff.Mode.SRC_ATOP);
        }else{
             //Error in Password
            Log.d("msg","Error in Password: "+passWord);
            MainFlag = false;
            et_pass.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP);
            btnDisable();
        }

        if(flagName && flagEmail && flagPassword && flagCollage_RegNo){

            MainFlag = true;
            btnEnable();
        }else {
            btnDisable();
        }

    }

    public void BksUserTable(){
        String whereClause = "email = '"+et_email.getText().toString()+"'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( whereClause );

        Backendless.Data.of( "User" ).find( queryBuilder,
                new AsyncCallback<List<Map>>(){
                    @Override
                    public void handleResponse( List<Map> data ) //Extract User from table
                    {
                        if(data.size()>0 && data.get(0).containsKey("objectId")){
                            Log.d("msg","UserobjId"+data.get(0).get("objectId").toString());
                            { //TODO test phase

                                final HashMap<String, Object> UserObject = new HashMap<String, Object>();
                                UserObject.put( "objectId", data.get(0).get("objectId").toString());

                                final HashMap<String, Object> collageObject = new HashMap<String, Object>();
//                                collageObject.put( "objectId", "30B7748C-5F12-574B-FF93-96983DA73F00");
                                collageObject.put( "objectId",collageFromBKs.get(SelectedCollagePosition).getObjectId());

                                ArrayList<Map> children = new ArrayList<Map>();
                                children.add( collageObject );

                                Backendless.Data.of( "User" ).
                                        setRelation( UserObject, "collageId", children, //set relation with collage
                                        new AsyncCallback<Integer>()
                                        {
                                            @Override
                                            public void handleResponse( Integer response )
                                            {
                                                Log.d( "msg", "relation has been set from "+UserObject+" to "+collageObject );
                                                new getLocalDB(Register.this).execute();

                                                startActivity(new Intent(Register.this, Login.class));
                                                finish();
                                            }

                                            @Override
                                            public void handleFault( BackendlessFault fault )
                                            {
                                                Log.d( "msg", "server reported an error - " + fault.getMessage() );
                                            }
                                        } );
                            }
                        }
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {

                        Log.d("msgQuery",fault.getMessage());
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Register.this,Login.class));
        finish();
    }
}