package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tiengviet1.activity.AlphabetActivity;
import com.example.tiengviet1.activity.PoemActivity;
import com.example.tiengviet1.activity.QuizActivity;
import com.example.tiengviet1.activity.VocabularyActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView btnChucai, btnTuvung, btnTho, btnKiemtra;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bubble_sound);

        btnChucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, AlphabetActivity.class);
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
                Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
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
                Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, PoemActivity.class);
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
                Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                bounceAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
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
    }

    private void setUpView() {
        btnChucai = findViewById(R.id.imgChucai);
        btnTuvung = findViewById(R.id.imgTuvung);
        btnTho = findViewById(R.id.imgTho);
        btnKiemtra = findViewById(R.id.imgKiemtra);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
