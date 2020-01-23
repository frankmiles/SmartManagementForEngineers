package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.XMLManager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMain;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class XMLDownloader extends AsyncTask<Void,Void,Void> {

    Activity activity;
    Context context;
    String url;
    String fileName;

    private static long downloadID;

    public XMLDownloader(Activity activity, Context context,String url,String fileName) {
        this.activity = activity;
        this.context = context;
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    protected Void doInBackground(Void... voids){

        ActivityCompat.requestPermissions(activity, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        File file = new File(Environment.DIRECTORY_DOWNLOADS);
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setDestinationInExternalPublicDir(file.getPath(),fileName+".xml");
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager != null ? downloadManager.enqueue(request) : -1;
        Log.d("msg","Downloading...");

        return null;
    }

    public static long getDownloadID() {
        return downloadID;
    }

    public static void setDownloadID(long downloadID) {
        XMLDownloader.downloadID = downloadID;
    }
}
