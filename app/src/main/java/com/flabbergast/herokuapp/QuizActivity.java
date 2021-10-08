package com.flabbergast.herokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Question> pitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        //Get intent values

        String title = (String) getIntent().getSerializableExtra("title");
        String image = (String) getIntent().getSerializableExtra("image");
        String questions = (String) getIntent().getSerializableExtra("questions");

        //Parse questions

        JSONArray arr = null;
        try {
            arr = new JSONArray(questions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Question> pitanja = new ArrayList<>();

        for (int j = 0; j < arr.length(); j++) {
            //Parse through the questions
            Question pitanje = null;
            try {
                pitanje = new Question(arr.getJSONObject(j).getInt("id"),
                        arr.getJSONObject(j).getString("question"),
                        arr.getJSONObject(j).getJSONArray("answers"),
                        arr.getJSONObject(j).getInt("correct_answer"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pitanja.add(pitanje);
        }
        this.pitanja = pitanja;


        //Now comes for initialization of the labels and buttons

        TextView TVTitle = (TextView) findViewById(R.id.quizTitle);

        ImageView quizPicture = (ImageView) findViewById(R.id.imageView2);
        Picasso.get().load(image).placeholder(R.drawable.myplaceholderimage).fit().centerCrop().into(quizPicture);


        /**
        try{
            Bitmap bm = null;
            URL aURL = new URL(image);
            HttpURLConnection conn = (HttpURLConnection) aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream(); //crashes here
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            quizPicture.setImageBitmap(bm);
            conn.disconnect();
        }catch (IOException e) {
            e.printStackTrace();
        } */

        TextView TVQuestion = (TextView) findViewById(R.id.QuestionTV);
        Button btn1 = (Button) findViewById(R.id.quizAnswer1);
        Button btn2 = (Button) findViewById(R.id.quizAnswer2);
        Button btn3 = (Button) findViewById(R.id.quizAnswer3);
        Button btn4 = (Button) findViewById(R.id.quizAnswer4);

        //Adds quiz and questions
        TVTitle.setText(title);

        TVQuestion.setText(pitanja.get(0).getQuestion());
        try {
            btn1.setText(pitanja.get(0).getAnswers().getString(0));
            btn2.setText(pitanja.get(0).getAnswers().getString(1));
            btn3.setText(pitanja.get(0).getAnswers().getString(2));
            btn4.setText(pitanja.get(0).getAnswers().getString(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void btnPressed1(View view){
        pressedButton(0);
    }
    public void btnPressed2(View view){
        pressedButton(1);
    }
    public void btnPressed3(View view){
        pressedButton(2);
    }
    public void btnPressed4(View view){
        pressedButton(3);
    }

    public void pressedButton(int btnNumber){
        Button btn1 = (Button) findViewById(R.id.quizAnswer1);
        Button btn2 = (Button) findViewById(R.id.quizAnswer2);
        Button btn3 = (Button) findViewById(R.id.quizAnswer3);
        Button btn4 = (Button) findViewById(R.id.quizAnswer4);

        switch(btnNumber){
            case 0:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn1.setBackgroundColor(0x4F00FF00);
                } else{
                    btn1.setBackgroundColor(0x4FFF0000);
                }
                break;
            case 1:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn2.setBackgroundColor(0x4F00FF00);
                } else{
                    btn2.setBackgroundColor(0x4FFF0000);
                }
                break;
            case 2:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn3.setBackgroundColor(0x4F00FF00);
                } else{
                    btn3.setBackgroundColor(0x4FFF0000);
                }
                break;
            case 3:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn4.setBackgroundColor(0x4F00FF00);
                } else{
                    btn4.setBackgroundColor(0x4FFF0000);
                }
                break;
        }

        //Disables buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                pitanja.remove(0);
                TextView TVQuestion = (TextView) findViewById(R.id.QuestionTV);
                TVQuestion.setText(pitanja.get(0).getQuestion());
                try {
                    btn1.setText(pitanja.get(0).getAnswers().getString(0));
                    btn1.setBackgroundColor(-3355444);
                    btn1.setEnabled(true);
                    btn2.setText(pitanja.get(0).getAnswers().getString(1));
                    btn2.setBackgroundColor(-3355444);
                    btn2.setEnabled(true);
                    btn3.setText(pitanja.get(0).getAnswers().getString(2));
                    btn3.setBackgroundColor(-3355444);
                    btn3.setEnabled(true);
                    btn4.setText(pitanja.get(0).getAnswers().getString(3));
                    btn4.setBackgroundColor(-3355444);
                    btn4.setEnabled(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, 3000);   //3 seconds
    }
}