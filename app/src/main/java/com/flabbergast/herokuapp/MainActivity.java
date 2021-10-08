package com.flabbergast.herokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import org.json.*;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> categoryList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.listView1);
        final ArrayList<Quiz>[] kvizovi = new ArrayList[]{new ArrayList<Quiz>()};

        final String[] str = new String[1];


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://iosquiz.herokuapp.com/api/quizzes";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        str[0] = response.toString();
                        try {
                            kvizovi[0] = parseData(str[0]);
                            Collections.sort(kvizovi[0],
                                    (o1, o2) -> o1.getCategory().compareTo(o2.getCategory())); //Sorts by category
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        QuizListAdapter adapter = new QuizListAdapter(findViewById(android.R.id.content).getRootView(),MainActivity.this, R.layout.quiz_layout, kvizovi[0]);
                        mListView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }



    public ArrayList<Quiz> parseData(String jsonString) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arr = obj.getJSONArray("quizzes");
        ArrayList<Quiz> kvizovi = new ArrayList();

        for (int i = 0; i < arr.length(); i++) {
            //Parse through the quizes
            ArrayList<Question> pitanja = new ArrayList<Question>();

            JSONArray nizPitanja = arr.getJSONObject(i).getJSONArray("questions");

            Quiz kviz = new Quiz(arr.getJSONObject(i).getInt("id"),
                    arr.getJSONObject(i).getString("title"),
                    arr.getJSONObject(i).getString("description"),
                    arr.getJSONObject(i).getString("category"),
                    arr.getJSONObject(i).getInt("level"),
                    arr.getJSONObject(i).getString("image"),
                    arr.getJSONObject(i).getString("questions"));
            kvizovi.add(kviz);
        }

        return kvizovi;
    }




}