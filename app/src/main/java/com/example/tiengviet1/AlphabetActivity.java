package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlphabetActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private String url = "https://prm391.herokuapp.com/api/alphabet";
    private static final String TAG = "AlphabetActivity";
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        setUpView();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                volleyJsonArrayRequest(url);
            }
        });
        volleyJsonArrayRequest(url);
    }

    public void setUpView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
    }

    private void volleyJsonArrayRequest(String url) {
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                swipeRefreshLayout.setRefreshing(false);
                List<AlphabetDTO> tmpList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mObj = response.getJSONObject(i);
                        AlphabetDTO mAlphabetDTO = new Gson().fromJson(mObj.toString(), AlphabetDTO.class);
                        tmpList.add(mAlphabetDTO);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AlphabetAdapter adapter = new AlphabetAdapter(AlphabetActivity.this, tmpList);
                mRecyclerView
                        .setLayoutManager(new GridLayoutManager(AlphabetActivity.this, 4));
                mRecyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(AlphabetActivity.this, "Mất kết nối mạng!", Toast.LENGTH_SHORT).show();
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

    public void clickToGetBack(View view) {
        finish();
    }
}
