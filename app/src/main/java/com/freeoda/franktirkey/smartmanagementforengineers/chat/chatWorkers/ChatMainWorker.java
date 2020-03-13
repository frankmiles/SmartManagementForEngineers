package com.freeoda.franktirkey.smartmanagementforengineers.chat.chatWorkers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.backendless.rt.messaging.Channel;
import com.freeoda.franktirkey.smartmanagementforengineers.R;

public class ChatMainWorker extends AppCompatActivity {

    public static final String TAG = "RTChat";
    private EditText et_message;
    private TextView tv_messages;
    private Channel channel;
    private String color = ColorPickerUtility.next();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        et_message = findViewById(R.id.message);
        tv_messages = findViewById(R.id.messages);

        final String name = ChatConfig.USER_NAME;
        channel = Backendless.Messaging.subscribe(ChatConfig.DEFAULT_CHANNEL);
        channel.addJoinListener(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Backendless.Messaging.publish(ChatConfig.DEFAULT_CHANNEL, wrapToColor(name) + " joined", new AsyncCallback<MessageStatus>() {
                    @Override
                    public void handleResponse(MessageStatus response) {
                        Log.d(TAG, "Sent joined " + response);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.d("msg",fault.getMessage());
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msg",fault.getMessage());
            }
        });
        channel.addMessageListener(new AsyncCallback<String>() {
            @Override
            public void handleResponse(String response) {
                tv_messages.append(Html.fromHtml("<br/>" + response));
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("msg",fault.getMessage());
            }
        });

        et_message.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if ( actionId == EditorInfo.IME_ACTION_SEND || event.getKeyCode() == KeyEvent.KEYCODE_ENTER ) {

                    et_message.setEnabled(false);

                    Backendless.Messaging.publish(ChatConfig.DEFAULT_CHANNEL, wrapToColor("[" + name + "]") + ": " + et_message.getText().toString(), new AsyncCallback<MessageStatus>() {
                        @Override
                        public void handleResponse(MessageStatus response) {
                            Log.d(TAG, "Sent et_message " + response);
                            et_message.setText("", TextView.BufferType.EDITABLE);
                            et_message.setEnabled(true);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            et_message.setEnabled(true);
                        }
                    });
                    handled = true;
                }

                return handled;
            }
        });

    }

    private void handleFault(BackendlessFault fault) {
        Log.e(TAG, fault.toString());
    }

    private String wrapToColor(String value) {
        return "<font color='" + color + "'>" + value + "</font>";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (channel != null)
            channel.leave();
    }

}