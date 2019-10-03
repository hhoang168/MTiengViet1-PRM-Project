package com.example.tiengviet1;

import java.io.Serializable;
import java.util.List;

public class AlphabetDTO implements Serializable {
    private int id;
    private String letter;
    private List<ImageDTO> listImages;

    public AlphabetDTO() {
    }

    public AlphabetDTO(int id, String letter, List<ImageDTO> listImages) {
        this.id = id;
        this.letter = letter;
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

    public List<ImageDTO> getListImages() {
        return listImages;
    }

    public void setListImages(List<ImageDTO> listImages) {
        this.listImages = listImages;
    }
}
