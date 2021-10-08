package com.flabbergast.herokuapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class QuizListAdapter extends ArrayAdapter<Quiz> {

    private static final String TAG = "QuizListAdapter";
    private Context mContext;
    int mResource;
    View view;
    private ArrayList<String> categories = new ArrayList<>();
    private String color;

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
        String questions = getItem(position).getQuestions();
        Quiz quiz = new Quiz(id, title, description, category, level, image, questions);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
        TextView tvCategory = (TextView) convertView.findViewById(R.id.textViewCategory);


        //Sets background color
        String tempColor = this.color;
        if (!this.categories.contains(category)){
            this.categories.add(category);
            tvCategory.setText(category);
            tvCategory.setVisibility(View.VISIBLE);
            Random random = new Random();
            int nextInt = random.nextInt(0xffffff + 1);
            String colorCode = String.format("%06x", nextInt);
            String clr = ""+colorCode;
            this.color = "#2F"+clr;
            tempColor = "#2F"+clr;
        }

        convertView.setBackgroundColor(Color.parseColor(this.color));


        tvTitle.setText(title);
        tvDescription.setText(description);


        //Sets image using Picasso
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
        Picasso.get().load(image).placeholder(R.drawable.myplaceholderimage).into(imageView);


        //Sets difficulty

        ImageView imageViewDifficulty1 = (ImageView) convertView.findViewById(R.id.imageViewDiff1);
        imageViewDifficulty1.setImageResource(R.drawable.diff1);
        ImageView imageViewDifficulty2 = (ImageView) convertView.findViewById(R.id.imageViewDiff2);
        imageViewDifficulty2.setImageResource(R.drawable.diff1);
        ImageView imageViewDifficulty3 = (ImageView) convertView.findViewById(R.id.imageViewDiff3);
        imageViewDifficulty3.setImageResource(R.drawable.diff1);

        switch (level){
            case 1: imageViewDifficulty1.setVisibility(View.VISIBLE);
                    break;
            case 2: imageViewDifficulty1.setVisibility(View.VISIBLE);
                    imageViewDifficulty2.setVisibility(View.VISIBLE);
                    break;
            case 3: imageViewDifficulty1.setVisibility(View.VISIBLE);
                    imageViewDifficulty2.setVisibility(View.VISIBLE);
                    imageViewDifficulty3.setVisibility(View.VISIBLE);
                    break;
        }

        String finalTempColor = tempColor;
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                Intent myIntent = new Intent(mContext, QuizActivity.class);
                myIntent.putExtra("title", title);
                myIntent.putExtra("image", image);
                myIntent.putExtra("questions", questions);
                myIntent.putExtra("color", finalTempColor);
                mContext.startActivity(myIntent);
                return false;//always return true to consume event, but this opens two activities
            }

        });

        return convertView;
    }
}
