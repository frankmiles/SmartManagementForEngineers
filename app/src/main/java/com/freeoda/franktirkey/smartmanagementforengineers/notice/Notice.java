package com.freeoda.franktirkey.smartmanagementforengineers.notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notice extends AppCompatActivity {

    TextView tv_notice_Headline;
    RecyclerView rv_notice_main;
    NoticeAdapter noticeAdapter;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        tv_notice_Headline = findViewById(R.id.tv_notice_Headline);
        rv_notice_main = findViewById(R.id.rv_notice_main);

        String noticeHeadLines = "Notice updated < 2 min...";
        tv_notice_Headline.setText(noticeHeadLines);

        list = new ArrayList();

//        list.add(new NoticeModel("Test Title","Test Body","11-08-15","https://wwww.franktirkey.com"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));
//        list.add(new NoticeModel("Test Title2","Test Body2","11-08-15 2","https://wwww.franktirkey.com/2"));

        rv_notice_main.setLayoutManager(new LinearLayoutManager(Notice.this));
        noticeAdapter = new NoticeAdapter(Notice.this,list);
        rv_notice_main.setAdapter(noticeAdapter);

        NoticeFetcher noticeFetcher = new NoticeFetcher(this,list,noticeAdapter);
        noticeFetcher.execute();
    }
}