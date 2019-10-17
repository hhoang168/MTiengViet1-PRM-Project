package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tiengviet1.activity.AlphabetActivity;
import com.example.tiengviet1.activity.PoemActivity;
import com.example.tiengviet1.activity.QuizActivity;
import com.example.tiengviet1.activity.VocabularyActivity;
import com.example.tiengviet1.fragments.GameFragment;
import com.example.tiengviet1.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    ImageButton btnLearn, btnGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();

        fragmentTransaction.add(R.id.mainFragment, homeFragment, null);
        fragmentTransaction.commit();
    }

    private void setUpView() {
        btnGame = findViewById(R.id.btnGame);
        btnLearn = findViewById(R.id.btnLearn);
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

    public void clickToGame(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GameFragment gameFragment = new GameFragment();
        fragmentTransaction.replace(R.id.mainFragment, gameFragment, null);
        fragmentTransaction.commit();
        btnGame.setBackgroundResource(R.drawable.choi_screenchoi);
        btnLearn.setBackgroundResource(R.drawable.hoc_screenchoi);
    }

    public void clickToLearn(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.mainFragment, homeFragment, null);
        fragmentTransaction.commit();
        btnGame.setBackgroundResource(R.drawable.choi_screenhoc);
        btnLearn.setBackgroundResource(R.drawable.hoc_screenhoc);
    }
}
