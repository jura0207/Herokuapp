package com.flabbergast.herokuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    btn1.setBackgroundColor(-16711936);
                } else{
                    btn1.setBackgroundColor(-65536);
                }
                break;
            case 1:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn2.setBackgroundColor(-16711936);
                } else{
                    btn2.setBackgroundColor(-65536);
                }
                break;
            case 2:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn3.setBackgroundColor(-16711936);
                } else{
                    btn3.setBackgroundColor(-65536);
                }
                break;
            case 3:
                if (btnNumber==pitanja.get(0).getCorrect_answer()){
                    btn4.setBackgroundColor(-16711936);
                } else{
                    btn4.setBackgroundColor(-65536);
                }
                break;
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                btn1.setText("TEST");
                pitanja.remove(0);
                TextView TVQuestion = (TextView) findViewById(R.id.QuestionTV);
                TVQuestion.setText(pitanja.get(0).getQuestion());
                try {
                    btn1.setText(pitanja.get(0).getAnswers().getString(0));
                    btn1.setBackgroundColor(-3355444);
                    btn2.setText(pitanja.get(0).getAnswers().getString(1));
                    btn2.setBackgroundColor(-3355444);
                    btn3.setText(pitanja.get(0).getAnswers().getString(2));
                    btn3.setBackgroundColor(-3355444);
                    btn4.setText(pitanja.get(0).getAnswers().getString(3));
                    btn4.setBackgroundColor(-3355444);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, 3000);   //3 seconds
    }


}