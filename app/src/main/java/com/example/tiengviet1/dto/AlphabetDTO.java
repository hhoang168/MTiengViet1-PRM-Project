package com.example.tiengviet1.dto;

import java.io.Serializable;
import java.util.List;

public class AlphabetDTO implements Serializable {
    private int id;
    private String letter;
    private String thumbnail;
    private String audioPath;
    private List<ImageDTO> listImages;

    public AlphabetDTO() {
    }

    public AlphabetDTO(int id, String letter, String thumbnail, String audioPath, List<ImageDTO> listImages) {
        this.id = id;
        this.letter = letter;
        this.thumbnail = thumbnail;
        this.audioPath = audioPath;
        this.listImages = listImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public List<ImageDTO> getListImages() {
        return listImages;
    }

    public void setListImages(List<ImageDTO> listImages) {
        this.listImages = listImages;
    }

    @Override
    public String toString() {
        return "IDx:" + id + " - Letter:" + letter;
    }
}
