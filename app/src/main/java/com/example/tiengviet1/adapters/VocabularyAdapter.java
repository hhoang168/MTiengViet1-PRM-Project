package com.example.tiengviet1.adapters;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.tiengviet1.R;
import com.example.tiengviet1.activity.VocabularyDetailActivity;
import com.example.tiengviet1.dto.VocabularyDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VocabularyAdapter extends BaseAdapter {
    private Activity activity;
    private List<VocabularyDTO> vocabularies;
    private MediaPlayer mediaPlayer;
    List<VocabularyDTO> tempList;

    public VocabularyAdapter(Activity activity, List<VocabularyDTO> vocabularies) {
        this.activity = activity;
        this.vocabularies = vocabularies;
        mediaPlayer = MediaPlayer.create(activity, R.raw.book_open_sound);
        tempList = new ArrayList<>();
        Set<VocabularyDTO> uniqueElements = new HashSet<VocabularyDTO>(vocabularies);
        tempList.clear();
        tempList.addAll(uniqueElements);
        Collections.sort(tempList);
    }

    @Override
    public int getCount() {
        return tempList.size();
    }

    @Override
    public Object getItem(int i) {
        return tempList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.vocabulary_item,null);
        final Button button = view.findViewById(R.id.btnCard);

        button.setText("Bài " + tempList.get(i).getTopic());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                Intent intent = new Intent(activity, VocabularyDetailActivity.class);
                intent.putExtra("hidetopic",button.getText().toString());
                intent.putExtra("listVocabulary", (Serializable) vocabularies);
                activity.startActivity(intent);
            }
        });
        return view;
    }
}
