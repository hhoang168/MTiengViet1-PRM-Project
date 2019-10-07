package com.example.tiengviet1;

import java.io.Serializable;

public class ImageDTO implements Serializable {
    private int id;
    private String audioPath;
    private String imgPath;
    private String description;

    public ImageDTO() {
    }

    public ImageDTO(int id, String imgPath, String audioPath, String description) {
        this.id = id;
        this.audioPath = audioPath;
        this.imgPath = imgPath;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
