package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.XMLManager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMain;

public class XMLDownloader extends AsyncTask<Void,Void,Void> {

    Activity activity;
    Context context;
    String url;
    String fileName;

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

        Log.d("msg","Downloading...");
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(SyllabusMain.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName+".xml");
        Log.d("msg",request.toString());
        long downloadID = downloadManager != null ? downloadManager.enqueue(request) : 0;
        Log.d("msg","Download request completed!...");

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        new xmlParser(activity,activity.getApplicationContext(),fileName).execute();
        Log.d("msg","XMLParser started");
    }
}
