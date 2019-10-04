package com.example.tiengviet1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {
    Context mContext;
    List<String> imagePaths;

    public AlphabetAdapter(Context mContext, List<String> imagePaths) {
        this.mContext = mContext;
        this.imagePaths = imagePaths;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alphabet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = imagePaths.get(position);
        Glide.with(mContext).load(url).into(holder.imgAlphabet);
    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlphabet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlphabet = itemView.findViewById(R.id.imgAlphabet);
        }
    }
}
