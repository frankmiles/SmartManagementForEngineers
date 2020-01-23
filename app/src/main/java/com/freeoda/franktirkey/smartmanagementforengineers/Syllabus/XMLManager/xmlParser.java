package com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.XMLManager;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMain;
import com.freeoda.franktirkey.smartmanagementforengineers.Syllabus.SyllabusMainModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class xmlParser extends AsyncTask<Void,Void,Void> {

    Activity activity;
    Context context;
    String name;

    static int flag = 0;

    List<SyllabusMainModel> list = new ArrayList<>();

    XmlPullParserFactory pullParserFactory;

    public xmlParser(Activity activity, Context context, String name) {
        this.activity = activity;
        this.context = context;
        this.name = name;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        moveFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/",
                name+".xml",
                context.getFilesDir().getAbsolutePath()+"/");

        try {

            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = new FileInputStream(context.getFilesDir().getAbsolutePath()+"/"+name+".xml");
            Log.d("msg",in_s.toString());

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(in_s,null);

            ArrayList<SyllabusXml> syllabusXmlArrayList = parserXML(parser);

            StringBuilder text = new StringBuilder();

            SyllabusMain.getList().clear();

            for(SyllabusXml sXml:syllabusXmlArrayList){ text
                        .append("id: ")
                        .append(sXml.getModule())

                        .append("topic: ")
                        .append(sXml.getTopic())

                        .append(" detail: ")
                        .append(sXml.getDetail())

                        .append(" url: ")
                        .append(sXml.getUrl())

                        .append(" youtube: ")
                        .append(sXml.getYoutube())

                        .append("\n");

                SyllabusMain.getList().add(new SyllabusMainModel(1,sXml.getTopic().trim()));
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SyllabusMain.UpdateAdapter();
                }
            });

            Log.d("msgData", text.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<SyllabusXml> parserXML(XmlPullParser parser)throws
            Exception {
        ArrayList<SyllabusXml> syllabusXmlArrayList = null;
        int eventType = parser.getEventType();
        SyllabusXml syllabusXml = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    syllabusXmlArrayList = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("Module")){
                        syllabusXml = new SyllabusXml();
                        syllabusXml.module = parser.getAttributeValue(null,"id");
                    }else if (syllabusXml != null){
                        if(name.equals("Topic")){
                            syllabusXml.topic = parser.nextText();
                        }else if (name.equals("Detail")){
                            syllabusXml.detail = parser.nextText();
                        }else if (name.equals("url")){
                            syllabusXml.url = parser.nextText();
                        }else if (name.equals("youtube")){
                            syllabusXml.youtube = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name =parser.getName();
                    if (name.equalsIgnoreCase("Module")&& syllabusXml!= null){
                        syllabusXmlArrayList.add(syllabusXml);
                    }
            }
            eventType = parser.next();
        }
        return syllabusXmlArrayList;
    }

    private void moveFile(String inputPath, String inputFile, String outputPath) {

        Log.d("msg",inputPath);
        Log.d("msg",outputPath);

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath +inputFile);
            out = new FileOutputStream(outputPath+ inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();

        }

        catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
            if(flag <= 0){
                flag = 1;
                moveFile( inputPath, inputFile, outputPath);
            }
        }
        catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        flag = 0;
    }
}
