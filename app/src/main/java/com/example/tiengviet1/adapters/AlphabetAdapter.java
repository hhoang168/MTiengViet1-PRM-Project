package com.example.tiengviet1.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tiengviet1.activity.AlphabetDetailActivity;
import com.example.tiengviet1.R;
import com.example.tiengviet1.dto.AlphabetDTO;

import java.util.List;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {
    private Context mContext;
    private List<AlphabetDTO> alphabetList;
    private MediaPlayer mediaPlayer;

    public AlphabetAdapter(Context mContext, List<AlphabetDTO> alphabetList) {
        this.mContext = mContext;
        this.alphabetList = alphabetList;
        mediaPlayer = MediaPlayer.create(mContext, R.raw.button_sound);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alphabet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String url = alphabetList.get(position).getThumbnail();
        Glide.with(mContext).load(url).into(holder.imgAlphabet);
        holder.imgAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(mContext, AlphabetDetailActivity.class);
                intent.putExtra("dto", alphabetList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alphabetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlphabet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlphabet = itemView.findViewById(R.id.imgAlphabet);
        }
    }
}
