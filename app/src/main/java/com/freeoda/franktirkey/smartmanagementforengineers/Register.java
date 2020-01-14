package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Set;

public class Register extends AppCompatActivity {

    Spinner spinner_Register_collage,spinner_Register_branch,spinner_Register_Sem;

    TextView tv_Name,tv_email,tv_branch,tv_Sem,tv_RegNumber;
    EditText et_Name,et_email,et_RegNumber,et_pass;
    Button btnRegister_Submit;


    String getSelectedCollageObjId;
    String getUserObjId;
    List<Collage> collageFromBKs = new ArrayList<>();
    List<String> collageNameList = new ArrayList<>();
    int SelectedCollageposition=0;
    ArrayAdapter<String> dataAdapter;

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

        et_Name = findViewById(R.id.et_Name);
        et_email = findViewById(R.id.et_email);
        et_RegNumber = findViewById(R.id.et_RegNumber);
        et_pass = findViewById(R.id.et_pass);

        btnRegister_Submit = findViewById(R.id.btnRegister_Submit);

        spinnerData(); //setting spinner collage

        btnRegister_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Register.this,Login.class));

                setNewUserBackendless(et_email.getText().toString(),et_pass.getText().toString(),et_Name.getText().toString());

                //finish();
            }
        });

        spinner_Register_collage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                getSelectedCollageObjId = collageFromBKs.get(i).getObjectId();
                SelectedCollageposition = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

                BackendlessApplication.getUser().setOwnerId(response.getProperty("ownerId").toString());
                BackendlessApplication.getUser().setName(response.getProperty("name").toString());
                BackendlessApplication.getUser().setRegNo(et_RegNumber.getText().toString());
                BackendlessApplication.getUser().setSemester(spinner_Register_Sem.getSelectedItem().toString());
                BackendlessApplication.getUser().setCollageId(getSelectedCollageObjId);
                BackendlessApplication.getUser().setEmail(response.getEmail());
                BackendlessApplication.getUser().setBranch(spinner_Register_branch.getSelectedItem().toString());

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
        savingUser.setEmail(BackendlessApplication.getUser().getEmail());
        savingUser.setOwnerId(BackendlessApplication.getUser().getOwnerId());
        savingUser.setUid(BackendlessApplication.getUser().getUid());
        savingUser.setCollageId(getSelectedCollageObjId);
        savingUser.setName(BackendlessApplication.getUser().name);
        savingUser.setRegNo(BackendlessApplication.getUser().getRegNo());
        savingUser.setBranch(BackendlessApplication.getUser().getBranch());
        savingUser.setSemester(BackendlessApplication.getUser().getSemester());

        Backendless.Persistence.save(savingUser, new AsyncCallback<User>() {
            @Override
            public void handleResponse(User response) {
                Toast.makeText(Register.this,"Data Uploaded",Toast.LENGTH_LONG).show();
                Log.d("msgBks","user table added");
//                Relation();
                BksUserTable();
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Register.this,"Server Busy!",Toast.LENGTH_LONG).show();
                Log.d("msgBks","user table failed to add"+fault.getMessage());
            }
        });
    }

    void spinnerData(){

        Backendless.Data.of(Collage.class).find(new AsyncCallback<List<Collage>>() {
            @Override
            public void handleResponse(List<Collage> response) {
                Log.d("msg","Got collage data");
                collageFromBKs = response;

                for(int i = 0;i<response.size();i++){    //Fetching Collage
                    collageNameList.add(response.get(i).getName());
                }
                dataAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_item,collageNameList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_Register_collage.setAdapter(dataAdapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void Relation(){
        BksUserTable();
        HashMap<String, Object> UserObject = new HashMap<String, Object>();
        UserObject.put( "objectId", getUserObjId);

        HashMap<String, Object> collageObject = new HashMap<String, Object>();
        collageObject.put( "objectId", "30B7748C-5F12-574B-FF93-96983DA73F00");
//        collageObject.put( "objectId", collageFromBKs.get(slected_collageFromBKs).getObjectId());

        ArrayList<Map> children = new ArrayList<Map>();
        children.add( collageObject );

        Backendless.Data.of( "User" ).setRelation( UserObject, "collageId", children,
                new AsyncCallback<Integer>()
                {
                    @Override
                    public void handleResponse( Integer response )
                    {
                        Log.d( "msg", "relation has been set" );
                    }

                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Log.d( "msg", "server reported an error - " + fault.getMessage() );
                    }
                } );
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
                                collageObject.put( "objectId",collageFromBKs.get(SelectedCollageposition).getObjectId());

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

                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();

        registerBackendless = null;
    }
}