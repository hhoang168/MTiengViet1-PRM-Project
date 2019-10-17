package com.example.tiengviet1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiengviet1.R;
import com.example.tiengviet1.activity.InQuizProgressActivity;
import com.example.tiengviet1.dto.QuizDTO;
import com.example.tiengviet1.dto.VocabularyDTO;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class QuizAdapter extends PagerAdapter {

    private Activity activity;
    private Context context;
    private List<QuizDTO> quizzes;
    List<QuizDTO> tempList;

    @Override
    public int getCount() {
        return tempList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public QuizAdapter(Activity activity, Context context, List<QuizDTO> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
        this.activity = activity;
        tempList = new ArrayList<>();
        Set<QuizDTO> uniqueElements = new HashSet<QuizDTO>(quizzes);
        tempList.clear();
        tempList.addAll(uniqueElements);
        Collections.sort(tempList);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_quiz_type,container,false);
        final Button btnTestOverview = itemView.findViewById(R.id.btnTestOverview);
        btnTestOverview.setText(tempList.get(position).getQuizType() + "");
        container.addView(itemView);
        btnTestOverview.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InQuizProgressActivity.class);
                intent.putExtra("listQuizzes", (Serializable) quizzes);
                intent.putExtra("topic",btnTestOverview.getText().toString());
                activity.startActivity(intent);
            }
        });
        return itemView;
    }
}
