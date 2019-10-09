package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class PoemDetailActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private List<PoemDTO> poemList;
    private int index;
    private Button btnNext, btnPrev;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);

        Intent intent = this.getIntent();
        poemList = (List<PoemDTO>) intent.getSerializableExtra("poemList");
        index = intent.getIntExtra("index", 0);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PoemDetailFragment detailFragment = new PoemDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("index", (Serializable) poemList.get(index));
        detailFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.contentLayout, detailFragment, null);
        fragmentTransaction.commit();

        mediaPlayer = MediaPlayer.create(PoemDetailActivity.this, R.raw.pop_sound);
    }

    public void clickToGetBack(View view) {
        finish();
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

    public void clickForPrevPoem(View view) {
        if (index <= 0) {
            index = poemList.size() - 1;
        } else {
            index--;
        }
        //play sound
        mediaPlayer.start();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PoemDetailFragment detailFragment = new PoemDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("index", (Serializable) poemList.get(index));
        detailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.contentLayout, detailFragment);
        fragmentTransaction.commit();
    }

    public void clickForNextPoem(View view) {
        if (index >= poemList.size() - 1) {
            index = 0;
        } else {
            index++;
        }
        //play sound
        mediaPlayer.start();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PoemDetailFragment detailFragment = new PoemDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("index", (Serializable) poemList.get(index));
        detailFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.contentLayout, detailFragment);
        fragmentTransaction.commit();
    }

}
