package com.freeoda.franktirkey.smartmanagementforengineers.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class ChatMain extends AppCompatActivity {

    TextView chatMan_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        chatMan_tv = findViewById(R.id.chatMan_tv);
        chatMan_tv.setText(getIntent().getStringExtra("data"));
    }
}
