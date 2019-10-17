package com.example.tiengviet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tiengviet1.R;
import com.example.tiengviet1.adapters.QuizAdapter;
import com.example.tiengviet1.adapters.VocabularyAdapter;
import com.example.tiengviet1.dto.ImageDTO;
import com.example.tiengviet1.dto.QuizDTO;
import com.example.tiengviet1.dto.VocabularyDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.tiengviet1.VolleySingleton;

public class QuizActivity extends AppCompatActivity {


    private String quizUrl = "https://prm391.herokuapp.com/api/quiz";
    private static final String TAG = "QuizActivity";
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setUpView();
        VolleyJsonArrayRequest(quizUrl);
    }

    public void setUpView() {
        viewPager = findViewById(R.id.vpTestOverview);
    }
    private void VolleyJsonArrayRequest(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<QuizDTO> quizzes = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        int id = jsonObject.getInt("idQuiz");
                        String quizType = jsonObject.getString("quizType");
                        String question = jsonObject.getString("questions");
                        String arrAnswer = jsonObject.getString("falseAnswer");
                        String trueAnswer = jsonObject.getString("trueAnswer");

                        JSONObject jsonObject1 = jsonObject.getJSONObject("image");
                        int imgID = jsonObject1.getInt("id");
                        String imgPath = jsonObject1.getString("imgPath");
                        String audioPath = jsonObject1.getString("audioPath");
                        String imgDescription = jsonObject1.getString("description");
                        ImageDTO image = new ImageDTO(imgID, imgPath, audioPath, imgDescription);
                        QuizDTO dto = new QuizDTO(id, quizType,question,arrAnswer,trueAnswer,image);
                        quizzes.add(dto);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(quizzes.size());
                pagerAdapter = new QuizAdapter(QuizActivity.this, QuizActivity.this ,quizzes);
                viewPager.setAdapter(pagerAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonArrayRequest onErrorResponse: " + error.getMessage());
            }
        });
        VolleySingleton.getInstance(this).getRequestQueue().add(jsonArrayRequest);
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
