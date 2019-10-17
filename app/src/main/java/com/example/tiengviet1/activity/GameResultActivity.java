package com.example.tiengviet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tiengviet1.MainActivity;
import com.example.tiengviet1.R;

public class GameResultActivity extends AppCompatActivity {
    TextView txtMarkResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        txtMarkResult = findViewById(R.id.txtMarkResult);
        Intent intent = getIntent();
        int mark = intent.getIntExtra("MARK", 0);
        txtMarkResult.setText(String.valueOf(mark));
    }

    public void clickToPlayAgain(View view) {
        Intent intent = new Intent(this, TrucXanhGameActivity.class);
        startActivity(intent);
    }

    public void clickToExit(View view) {
        try{
            startActivity(new Intent(GameResultActivity.this,MainActivity.class));
        }finally {
            finish();
        }
    }
}
