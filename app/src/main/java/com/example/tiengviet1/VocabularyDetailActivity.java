package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class VocabularyDetailActivity extends AppCompatActivity {

    private String vocabularyUrl = "https://prm391.herokuapp.com/api/vocabulary";
    private static final String TAG = "VocabularyActivity";
    private ListView mListViewDetail;
    TextView txtBaiHoc;
    String findTopic = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_detail);
        Intent intent = this.getIntent();
        setUpView();
        findTopic = intent.getStringExtra("hidetopic");
        txtBaiHoc.setText(findTopic.replace("_",": "));
        VolleyJsonArrayRequest(vocabularyUrl);
    }

    private void setUpView() {
        mListViewDetail = findViewById(R.id.mListViewDetail);
        txtBaiHoc = findViewById(R.id.txtBaiHoc);
    }

    private void VolleyJsonArrayRequest(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<VocabularyDTO> vocabularies = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        int id = jsonObject.getInt("id");
                        String topic = jsonObject.getString("topic");
                        String description = jsonObject.getString("description");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("image");
                        int imgID = jsonObject1.getInt("id");
                        String imgPath = jsonObject1.getString("imgPath");
                        String audioPath = jsonObject1.getString("audioPath");
                        String imgDescription = jsonObject1.getString("description");
                        ImageDTO image = new ImageDTO(imgID, imgPath, audioPath, imgDescription);
                        VocabularyDTO vocabulary = new VocabularyDTO(id, topic, description, image);
                        vocabularies.add(vocabulary);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                ArrayList<VocabularyDTO> foundList = new ArrayList<>();
                for (int i = 0; i < vocabularies.size(); i++){
                    String topic = vocabularies.get(i).getTopic();
                    if(findTopic.contains(topic)) {
                        foundList.add(vocabularies.get(i));
                    }
                }
                System.out.println(foundList.size() + "Size");
                VocabularyDetailAdapter vocabularyAdapter = new VocabularyDetailAdapter(VocabularyDetailActivity.this,foundList);
                mListViewDetail.setAdapter(vocabularyAdapter);
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
        if(hasFocus){
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
