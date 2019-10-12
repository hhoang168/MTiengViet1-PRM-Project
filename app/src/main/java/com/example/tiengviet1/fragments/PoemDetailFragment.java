package com.example.tiengviet1.fragments;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiengviet1.R;
import com.example.tiengviet1.dto.PoemDTO;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoemDetailFragment extends Fragment {

    private TextView txtPoemTitle, txtPoemContent;
    private ImageView imgPoemPicture;
    private CardView mCardview;
    private MediaPlayer mediaPlayer;
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
               mediaPlayer = setUpMedia(dto.getImage().getAudioPath());
               mediaPlayer.start();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!= null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private MediaPlayer setUpMedia(String url){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        return MediaPlayer.create(getContext(),Uri.parse(url));
    }
}
