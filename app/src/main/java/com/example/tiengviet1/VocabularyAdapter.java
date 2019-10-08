package com.example.tiengviet1;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class VocabularyAdapter extends BaseAdapter {
    private Activity activity;
    private List<VocabularyDTO> vocabularies;

    public VocabularyAdapter(Activity activity, List<VocabularyDTO> vocabularies) {
        this.activity = activity;
        this.vocabularies = vocabularies;
    }

    @Override
    public int getCount() {
        return vocabularies.size();
    }

    @Override
    public Object getItem(int i) {
        return vocabularies.get(i);
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
        button.setText("Bài " + vocabularies.get(i).getTopic());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, VocabularyDetailActivity.class);
                intent.putExtra("hidetopic",button.getText().toString());
                activity.startActivity(intent);
            }
        });
        return view;
    }
}
