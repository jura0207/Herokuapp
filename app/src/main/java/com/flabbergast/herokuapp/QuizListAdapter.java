package com.flabbergast.herokuapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.LinkedList;

public class QuizListAdapter extends ArrayAdapter<Quiz> {

    private static final String TAG = "QuizListAdapter";
    private Context mContext;
    int mResource;
    View view;

    public QuizListAdapter(View view, Context context, int resource, ArrayList<Quiz> kvizovi) {
        super(context, resource, kvizovi);
        mContext = context;
        mResource = resource;
        this.view = view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String category = getItem(position).getCategory();
        int level = getItem(position).getLevel();
        String image = getItem(position).getImage();
        ArrayList<Question> questions = getItem(position).getQuestions();
        Quiz quiz = new Quiz(id, title, description, category, level, image, questions);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
        //ImageView ivImage = (ImageView) convertView.findViewById(R.id.imageView);

        tvTitle.setText(title);
        tvDescription.setText(description);


        //Sets image

        SimpleDraweeView draweeView = (SimpleDraweeView) convertView.findViewById(R.id.imageView1);
        Uri imageUri = Uri.parse(image);
        draweeView.setImageURI(imageUri);

        //Sets difficulty

        SimpleDraweeView draweeViewDifficulty1 = (SimpleDraweeView) convertView.findViewById(R.id.imageViewDiff1);
        draweeViewDifficulty1.setActualImageResource(R.drawable.diff1);
        SimpleDraweeView draweeViewDifficulty2 = (SimpleDraweeView) convertView.findViewById(R.id.imageViewDiff2);
        draweeViewDifficulty2.setActualImageResource(R.drawable.diff1);
        SimpleDraweeView draweeViewDifficulty3 = (SimpleDraweeView) convertView.findViewById(R.id.imageViewDiff3);
        draweeViewDifficulty3.setActualImageResource(R.drawable.diff1);

        switch (level){
            case 1: draweeViewDifficulty1.setVisibility(View.VISIBLE);
                    break;
            case 2: draweeViewDifficulty1.setVisibility(View.VISIBLE);
                    draweeViewDifficulty2.setVisibility(View.VISIBLE);
                    break;
            case 3: draweeViewDifficulty1.setVisibility(View.VISIBLE);
                    draweeViewDifficulty2.setVisibility(View.VISIBLE);
                    draweeViewDifficulty3.setVisibility(View.VISIBLE);
                    break;
        }

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                tvTitle.setText("clicked");
                Intent myIntent = new Intent(mContext, QuizActivity.class);
                myIntent.putExtra("title", title);
                myIntent.putExtra("image", image);
                //myIntent.putExtra("questions", questions);
                //myIntent.putParcelableArrayListExtra("questions", questions);
                mContext.startActivity(myIntent);
                //MainActivity.setQuiz(view, title, image, questions);
                return true;//always return true to consume event
            }

        });

        return convertView;
    }
}
