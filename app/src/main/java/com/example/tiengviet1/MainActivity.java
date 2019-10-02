package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView btnChucai, btnTuvung, btnTho, btnKiemtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();


        btnChucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                btnChucai.startAnimation(bounceAnim);
                changeActivity(AlphabetActivity.class);
            }
        });

        btnTuvung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                btnTuvung.startAnimation(bounceAnim);
                changeActivity(VocabularyActivity.class);
            }
        });

        btnTho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                btnTho.startAnimation(bounceAnim);
                changeActivity(PoemActivity.class);
            }
        });

        btnKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation bounceAnim = new AnimationUtils().loadAnimation(MainActivity.this, R.anim.bounce);
                btnKiemtra.startAnimation(bounceAnim);
                changeActivity(QuizActivity.class);
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

    private void changeActivity(Class activity) {
        startActivity(new Intent(MainActivity.this, activity));
    }
}
