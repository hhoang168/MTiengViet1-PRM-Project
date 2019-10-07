package com.example.tiengviet1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VocabularyDetailAdapter extends BaseAdapter {

    private Activity activity;
    private List<VocabularyDTO> vocabularies;

    public VocabularyDetailAdapter(Activity activity, List<VocabularyDTO> vocabularies) {
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
        view = inflater.inflate(R.layout.vocabulary_detail_item,null);
        TextView txtChuCai = view.findViewById(R.id.txtChuCai);
        ImageView imgMota = view.findViewById(R.id.imgMota);
        Glide.with(view).load(vocabularies.get(i).getImage().getImgPath()).into(imgMota);
        txtChuCai.setText(vocabularies.get(i).getDescription());
        return view;
    }
}
