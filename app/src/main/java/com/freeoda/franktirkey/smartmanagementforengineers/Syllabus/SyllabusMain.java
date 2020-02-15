package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.freeoda.franktirkey.smartmanagementforengineers.R;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.XMLManager.XMLDownloader;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.XMLManager.xmlParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SyllabusMain extends YouTubeBaseActivity {

    TextView syllabusMain_tv,tv_cv_Syllabus_Main,tv_SubjectName_SyllabusMain;
    RecyclerView rv_syllabus_main_subjectSelect;
    FloatingActionButton fb_Syllabus_main;
    CardView cv_Syllabus_main;

    ShimmerFrameLayout container;

    static List<SyllabusMainModel> list = new ArrayList<SyllabusMainModel>();

    static SyllabusMainAdaper adapter;

    BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);

            if(XMLDownloader.getDownloadID() == id){
                callParser();
                Log.d("msg","Downloaded!");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_main);

        Log.d("msg","data Recived througn Intent (URL): "+getIntent().getExtras().getString("url"));
        Log.d("msg","data Recived througn Intent (NAME): "+getIntent().getExtras().getString("name"));

        tv_SubjectName_SyllabusMain = findViewById(R.id.tv_SubjectName_SyllabusMain);
        syllabusMain_tv = findViewById(R.id.syllabusMain_tv);

        rv_syllabus_main_subjectSelect = findViewById(R.id.rv_syllabus_main_subjectSelect);
        fb_Syllabus_main = findViewById(R.id.fb_Syllabus_main);
        cv_Syllabus_main = findViewById(R.id.cv_Syllabus_main);
        tv_cv_Syllabus_Main = findViewById(R.id.tv_cv_Syllabus_Main);

        tv_cv_Syllabus_Main.setText(getIntent().getExtras().getString("name"));

        container = findViewById(R.id.shimmer_view_container);
        container.setVisibility(View.GONE);

        list.add(new SyllabusMainModel("module","topic", "detail", "url", "youtube"));
//        list.add(new SyllabusMainModel(002,"Check your internet!"));
//        list.add(new SyllabusMainModel(003,"'Even a Talent can be"));
//        list.add(new SyllabusMainModel(004,"bitten by plan!'"));


        fb_Syllabus_main.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                rv_syllabus_main_subjectSelect.setVisibility(View.VISIBLE);
                cv_Syllabus_main.setVisibility(View.GONE);
                fb_Syllabus_main.setVisibility(View.GONE);
            }
        });

        interface_RecyclerViewClickListner_Syllabus onClickView = new interface_RecyclerViewClickListner_Syllabus() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                rv_syllabus_main_subjectSelect.setVisibility(View.GONE);
                cv_Syllabus_main.setVisibility(View.VISIBLE);
                fb_Syllabus_main.setVisibility(View.VISIBLE);
            }
        };

        rv_syllabus_main_subjectSelect.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SyllabusMainAdaper(list,onClickView);
        rv_syllabus_main_subjectSelect.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        getSyllabusXML();
    }

    private void getSyllabusXML() {
//        new XMLDownloader(SyllabusMain.this, getApplicationContext(),
//                "https://backendlessappcontent.com/A32C8534-709A-4E2C-829B-0614A16E5DF3/" +
//                        "D2C8202D-06C9-4427-AFF2-8D444BBA8A77/files/syllabusXML/sample.xml",
//                getIntent().getStringExtra("name")).execute();

        new XMLDownloader(SyllabusMain.this, getApplicationContext(),
                getIntent().getStringExtra("url").toString(),
                getIntent().getStringExtra("name")).execute();

        Log.d("msg", "XMLDownloader Started");
    }

    private void callParser(){
        new xmlParser(this,getApplicationContext(),getIntent().getStringExtra("name")).execute();
        Log.d("msg","xmlParser Executed");
    }

    public static List<SyllabusMainModel> getList() {
        return list;
    }

    public static void UpdateAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }
}