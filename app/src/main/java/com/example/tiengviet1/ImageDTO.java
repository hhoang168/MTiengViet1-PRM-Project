package com.example.tiengviet1;

import java.io.Serializable;

public class ImageDTO implements Serializable {
    private int id;
    private String audioPath;
    private String imagePath;
    private String description;

    public ImageDTO() {
    }

    public ImageDTO(int id, String audioPath, String imagePath, String description) {
        this.id = id;
        this.audioPath = audioPath;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
