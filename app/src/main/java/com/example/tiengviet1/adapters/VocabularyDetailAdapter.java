package com.example.tiengviet1.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.tiengviet1.R;
import com.example.tiengviet1.dto.VocabularyDTO;

import java.io.IOException;
import java.util.List;

public class VocabularyDetailAdapter extends PagerAdapter {

    private Context context;
    private List<VocabularyDTO> vocabularies;

    public VocabularyDetailAdapter(Context context, List<VocabularyDTO> vocabularies) {
        this.context = context;
        this.vocabularies = vocabularies;
    }

    @Override
    public int getCount() {
        return vocabularies.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_vocabulary_detail,container,false);
        TextView txtChuCai = itemView.findViewById(R.id.txtChuCai);
        ImageView imgMota = itemView.findViewById(R.id.imgMota);
        ImageView btnSound = itemView.findViewById(R.id.imgAmThanh);
        txtChuCai.setText(vocabularies.get(position).getDescription());
        Glide.with(itemView).load(vocabularies.get(position).getImage().getImgPath()).into(imgMota);
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(vocabularies.get(position).getImage().getAudioPath());
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
