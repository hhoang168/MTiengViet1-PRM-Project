package com.example.tiengviet1;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoemDetailFragment extends Fragment {

    TextView txtPoemTitle, txtPoemContent;
    ImageView imgPoemPicture;
    CardView mCardview;

    public PoemDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poem_detail, container, false);
        LinearLayout layout = view.findViewById(R.id.btnBack);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        txtPoemTitle = view.findViewById(R.id.txtPoemTitle);
        txtPoemContent = view.findViewById(R.id.txtPoemContent);
        imgPoemPicture = view.findViewById(R.id.imgPoem);
        mCardview = view.findViewById(R.id.bodyCard);
        Bundle bundle = getArguments();
        final PoemDTO dto = (PoemDTO) bundle.getSerializable("index");
        txtPoemTitle.setText(dto.getTitle());
        txtPoemContent.setText(dto.getDescription());
        String url = dto.getImage().getImgPath();
        Glide.with(getActivity()).load(url).into(imgPoemPicture);
        mCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(dto.getImage().getAudioPath());
            }
        });
        return view;
    }

    private void playAudio(String path) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), Uri.parse(path));
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mediaPlayer.start();
    }
}
