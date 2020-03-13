package com.freeoda.franktirkey.smartmanagementforengineers.chat.chatWorkers;

import com.freeoda.franktirkey.smartmanagementforengineers.BackendlessApplication;

public class ChatConfig {

    public static final String APPLICATION_ID = BackendlessApplication.APPLICATION_ID;
    public static final String API_KEY=BackendlessApplication.API_KEY;
    public static final String USER_NAME=BackendlessApplication.getUser().getName();
    public static final String DEFAULT_CHANNEL = "Default";
}
