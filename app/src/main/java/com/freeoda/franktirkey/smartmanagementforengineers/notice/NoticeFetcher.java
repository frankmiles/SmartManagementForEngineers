package com.freeoda.franktirkey.smartmanagementforengineers.notice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticeFetcher extends AsyncTask<Void,Void,Void> {
    Context context;
    String JsonURL = "https://smart-management-for-engineers.herokuapp.com/";
    RequestQueue requestQueue;
    JSONObject obj;
    List list;
    NoticeAdapter adapter;

    public NoticeFetcher(Context context, List list, NoticeAdapter adapter) {
        this.context = context;
        this.list = list;
        this.adapter = adapter;
        list.clear();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        requestQueue = Volley.newRequestQueue(context);
        final StringRequest request = new StringRequest(Request.Method.POST, JsonURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    obj = new JSONObject(response);
                    Log.d("JSON_response", "onResponse: "+obj.getString("date"));
                    String date = obj.getString("date");

                    //THis is for arrays
                    JSONArray arr = obj.getJSONArray("data");
                    for(int i = 0;i < arr.length(); i++){
                        JSONObject child_obj = new JSONObject(arr.get(i).toString());
                        Log.d("JSON_DataArray", "onResponse: " + child_obj.get("title").toString());
                        list.add(new NoticeModel(
                                child_obj.get("title").toString(),
                                child_obj.get("body").toString(),
                                date,
                                child_obj.get("detail").toString()
                                ));
                    }
                    adapter.notifyDataSetChanged();
                    //TIll here

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "onErrorResponse: "+error.toString());
            }
        });
        requestQueue.add(request);

        return null;
    }
}
