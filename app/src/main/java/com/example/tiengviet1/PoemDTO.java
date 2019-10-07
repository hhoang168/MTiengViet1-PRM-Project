package com.example.tiengviet1;

import java.io.Serializable;

public class PoemDTO implements Serializable {
    private int id;
    private String title;
    private String description;
    private ImageDTO image;

    public PoemDTO() {
    }

    public PoemDTO(int id, String title, String description, ImageDTO image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }
}
