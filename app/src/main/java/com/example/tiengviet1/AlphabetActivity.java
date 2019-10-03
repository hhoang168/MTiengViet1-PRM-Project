package com.example.tiengviet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AlphabetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        GridView gridView = (GridView) findViewById(R.id.alphabetGridView);
        ImageAdapter adapter = new ImageAdapter(this);
        Intent intent = this.getIntent();
        ArrayList<String> imagePaths = (ArrayList<String>) intent.getStringArrayListExtra("imagePaths");
        adapter.setImagePaths(imagePaths);
        // Instance of ImageAdapter Class
        gridView.setAdapter(adapter);
    }
}
