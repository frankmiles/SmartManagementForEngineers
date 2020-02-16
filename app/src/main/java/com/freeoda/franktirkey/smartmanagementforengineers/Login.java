package com.freeoda.franktirkey.smartmanagementforengineers;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
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
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    TextView tvUsername,tvPass,getTvUsername_feedback,tvPass_feedback;
    EditText etUsername,etPass;
    static Button btnRegister,btnSignin;

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

        btnbusy();
        ValidLoginCheck();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnbusy();
                startActivity(new Intent(Login.this,Register.class));
                finish();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnbusy();
                btnSignin.setText("Loading...");
                String user = etUsername.getText().toString()+"";
                String pass = etPass.getText().toString()+"";
                Backendless.UserService.login(user, pass, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(Login.this,"LogedIn",Toast.LENGTH_SHORT).show();
                        BackendlessApplication.backendlessUser = response;
                        btnbusy();
                        getSubject();
                    }
                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Login.this,fault.getDetail(),Toast.LENGTH_LONG).show();
                        Log.println(Log.ASSERT,"Backendless_error",fault.getDetail()+"");
                        btnready();
                    }
                },true);
            }
        });


        /*Valid Login*/

        /*End of Valid Login*/
    }

    private void getSubject(){
        final String whereClause = "email = '"+BackendlessApplication.backendlessUser.getEmail()+"'";

        final DataQueryBuilder dqb = DataQueryBuilder.create();
        dqb.setWhereClause(whereClause);

        Backendless.Data.of("User").find(dqb, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                if (response != null){

                    btnSignin.setText(" ");
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
                    BackendlessApplication.setUser(signedInUser);
//                    btnSignin.animate()
//                            .scaleXBy(ViewGroup.LayoutParams.MATCH_PARENT + 20)
//                            .scaleYBy(ViewGroup.LayoutParams.MATCH_PARENT + 20)
//                            .alphaBy(0)
//                            .alphaBy(0)
//                            .setDuration(1000)
//                            .alphaBy(0)
//                            .setListener(new Animator.AnimatorListener() {
//                                @Override
//                                public void onAnimationStart(final Animator animator) {
//                                    btnRegister.setVisibility(View.INVISIBLE);
//                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//                                        btnSignin.setText("SMFE");
//                                        btnSignin.setTextSize(5);
//                                        int colorFrom = getResources().getColor(R.color.colorLightPrimary);
//                                        int colorTo = getResources().getColor(R.color.colorAccent);
//                                        ValueAnimator valueAnimator = ValueAnimator.ofObject(
//                                                new ArgbEvaluator(), colorFrom,colorTo);
//                                        valueAnimator.setDuration(900);
//                                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                                            @Override
//                                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                                                btnSignin.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
//                                            }
//                                        });
//                                        valueAnimator.start();
//                                    }else {
//                                        btnSignin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                                    }
//                                }
//
//                                @Override
//                                public void onAnimationEnd(Animator animator) {
//                                    startActivity(new Intent(Login.this, MainActivity.class));
//                                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_from_left);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onAnimationCancel(Animator animator) {
//
//                                }
//
//                                @Override
//                                public void onAnimationRepeat(Animator animator) {
//
//                                }
//                            })
//                            .start();

                    startActivity(new Intent(Login.this, MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_from_left);
                    finish();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msg","error: "+fault.getMessage()); //tODO FOR TESTING PURPOSE
            }
        });
    }

    public void ValidLoginCheck() {

        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if(response){
                    Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            btnbusy();
                            Toast.makeText(Login.this,"LogedIn",Toast.LENGTH_SHORT).show();
                            Log.d("msg","LogedIn");
                            BackendlessApplication.backendlessUser = response;
                            getSubject();
                        }
                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.d("msg","Failed to logedin");
                            Toast.makeText(Login.this,fault.getCode(),Toast.LENGTH_LONG).show();
                            btnready();
                        }
                    });
                }else {
                    btnready();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msg","Log in failed");
                Toast.makeText(Login.this,fault.getCode(),Toast.LENGTH_LONG).show();
                btnready();
            }
        });

    }

    private void btnready(){ //TO make button Available to click
        btnRegister.setEnabled(true);
        btnSignin.setEnabled(true);
        btnRegister.setClickable(true);
        btnSignin.setClickable(true);
        btnSignin.setText("SIGNIN");
        btnRegister.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnSignin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    private void btnbusy(){ //TO make button Unavailable to click
        btnRegister.setEnabled(false);
        btnSignin.setEnabled(false);
        btnRegister.setClickable(false);
        btnSignin.setClickable(false);
        btnSignin.setText("Loading...");
        btnRegister.setBackgroundColor(getResources().getColor(R.color.colorLightPrimary));
        btnSignin.setBackgroundColor(getResources().getColor(R.color.colorLightPrimary));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finishAffinity();
    }
}
