package com.example.tiengviet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tiengviet1.R;
import com.example.tiengviet1.VolleySingleton;
import com.example.tiengviet1.adapters.VocabularyDetailAdapter;
import com.example.tiengviet1.dto.ImageDTO;
import com.example.tiengviet1.dto.VocabularyDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDetailActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter = null;
    private ViewPager mViewPager;
    private MediaPlayer mediaPlayer;
    TextView txtBaiHoc;
    Button btnMucLuc;
    String findTopic = "";
    Button btnNext, btnPre;
    List<VocabularyDTO> vocabularies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_detail);
        final Intent intent = this.getIntent();
        setUpView();
        findTopic = intent.getStringExtra("hidetopic");
        vocabularies = (List<VocabularyDTO>) intent.getSerializableExtra("listVocabulary");
        txtBaiHoc.setText(findTopic);
        ArrayList<VocabularyDTO> tempList = new ArrayList<>();
        for (int i = 0; i < vocabularies.size(); i++) {
            String topic = vocabularies.get(i).getTopic();
            if (findTopic.contains(topic)) {
                tempList.add(vocabularies.get(i));
            }
        }
        pagerAdapter = new VocabularyDetailAdapter(VocabularyDetailActivity.this, tempList);
        mViewPager.setAdapter(pagerAdapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
            }
        });
        btnMucLuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        txtBaiHoc = findViewById(R.id.txtBaiHoc);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);
        btnMucLuc = findViewById(R.id.btnMucluc);
        mediaPlayer = MediaPlayer.create(VocabularyDetailActivity.this, R.raw.book_flip_sound);
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
