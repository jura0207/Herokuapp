package com.flabbergast.herokuapp;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    private int id;
    private String question;
    private JSONArray answers;
    private int correct_answer;
    private List<String> lista;


    public Question(int id, String question, JSONArray answers, int correct_answer) throws JSONException {
        this.id = id;
        this.question = question;
        this.answers = answers;

        List<String> lista = new ArrayList<>();
        for (int i = 0; i < answers.length(); i++) {
            lista.add(answers.getString(i));
        }
        this.lista = lista;

        this.correct_answer = correct_answer;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        correct_answer = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public JSONArray getAnswers() {
        return answers;
    }

    public void setAnswers(JSONArray answers) {
        this.answers = answers;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeInt(correct_answer);
        parcel.writeList(lista);
    }
}
