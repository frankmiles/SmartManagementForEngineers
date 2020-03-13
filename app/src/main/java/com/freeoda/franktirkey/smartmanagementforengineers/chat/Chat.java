package com.freeoda.franktirkey.smartmanagementforengineers.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    RecyclerView rv_Chat;

    List<Chat_rvModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final Context context = this;

        rv_Chat = findViewById(R.id.rv_Chat);

        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));
        list.add(new Chat_rvModel("tom"));

        rv_Chat.setLayoutManager(new LinearLayoutManager(this));

        interface_RecyclerViewClickListener_Chat irvcl = new interface_RecyclerViewClickListener_Chat() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
            }
        };

        final Chat_rvAdapter chat_rvAdapter = new Chat_rvAdapter(list,irvcl);
        rv_Chat.setAdapter(chat_rvAdapter);
        chat_rvAdapter.notifyDataSetChanged();

    }
}
