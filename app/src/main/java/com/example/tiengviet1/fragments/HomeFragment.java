package com.example.tiengviet1.fragments;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tiengviet1.MainActivity;
import com.example.tiengviet1.R;
import com.example.tiengviet1.activity.AlphabetActivity;
import com.example.tiengviet1.activity.PoemActivity;
import com.example.tiengviet1.activity.QuizActivity;
import com.example.tiengviet1.activity.VocabularyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ImageView btnChucai, btnTuvung, btnTho, btnKiemtra;
    private MediaPlayer mediaPlayer;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        setUpView(view);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.bubble_sound);

        btnChucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnim = new AnimationUtils().loadAnimation(getContext(), R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getContext(), AlphabetActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                btnChucai.startAnimation(bounceAnim);

                mediaPlayer.start();
            }
        });

        btnTuvung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnim = new AnimationUtils().loadAnimation(getContext(), R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getContext(), VocabularyActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                btnTuvung.startAnimation(bounceAnim);

                mediaPlayer.start();
            }
        });

        btnTho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnim = new AnimationUtils().loadAnimation(getContext(), R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getContext(), PoemActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                btnTho.startAnimation(bounceAnim);

                mediaPlayer.start();
            }
        });

        btnKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnim = new AnimationUtils().loadAnimation(getContext(), R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(getContext(), QuizActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                btnKiemtra.startAnimation(bounceAnim);

                mediaPlayer.start();
            }
        });
        return view;
    }


    private void setUpView(View view) {
        btnChucai = view.findViewById(R.id.imgChucai);
        btnTuvung = view.findViewById(R.id.imgTuvung);
        btnTho = view.findViewById(R.id.imgTho);
        btnKiemtra = view.findViewById(R.id.imgKiemtra);
    }

}
