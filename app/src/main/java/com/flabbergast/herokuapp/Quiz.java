package com.flabbergast.herokuapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Quiz implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String category;
    private int level;
    private String image;
    private ArrayList<Question> questions;

    public Quiz(int id, String title, String description, String category, int level, String image, ArrayList<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.level = level;
        this.image = image;
        this.questions = questions;
    }

    protected Quiz(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        category = in.readString();
        level = in.readInt();
        image = in.readString();
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeInt(level);
        parcel.writeString(image);
        parcel.writeTypedList(questions);
    }
}
