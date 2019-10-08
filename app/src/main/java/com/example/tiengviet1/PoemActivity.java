package com.example.tiengviet1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PoemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private final String URL = "https://prm391.herokuapp.com/api/poem";
    private final String TAG = "PoemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);
        createView();

        volleyJsonArrayRequest(URL);
    }

    private void createView() {
        mRecyclerView = findViewById(R.id.poemList);
    }

    private void volleyJsonArrayRequest(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<PoemDTO> poemList = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mObj = response.getJSONObject(i);
                        PoemDTO mPoemDTO = new Gson().fromJson(mObj.toString(), PoemDTO.class);
                        poemList.add(mPoemDTO);
                    }
                    PoemAdapter adapter = new PoemAdapter(PoemActivity.this, poemList);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(PoemActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    mRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonArrayRequest onErrorResponse: " + error.getMessage());
            }
        });
        VolleySingleton.getInstance(this).getRequestQueue().add(jsonArrayRequest);
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
}

class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.ViewHolder> {
    Context mContext;
    List<PoemDTO> mPoemList;

    public PoemAdapter(Context mContext, List<PoemDTO> mPoemList) {
        this.mContext = mContext;
        this.mPoemList = mPoemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poem_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtSubject.setText(mPoemList.get(position).getTitle());
        String url = mPoemList.get(position).getImage().getImgPath();
        Glide.with(mContext).load(url).into(holder.imgPoem);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PoemDetailActivity.class);
                ArrayList<PoemDTO> tmpList = new ArrayList<>();
                tmpList.addAll(mPoemList);
                intent.putExtra("index",position);
                intent.putExtra("poemList",(Serializable) tmpList);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPoemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoem;
        TextView txtSubject;
        CardView parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoem = itemView.findViewById(R.id.imgPoemThumbnail);
            txtSubject = itemView.findViewById(R.id.txtPoemSubject);
            parentLayout = itemView.findViewById(R.id.poemItemLayout);
        }
    }
}
