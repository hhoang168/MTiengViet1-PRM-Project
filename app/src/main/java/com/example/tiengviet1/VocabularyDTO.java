package com.example.tiengviet1;

import java.io.Serializable;

public class VocabularyDTO implements Serializable, Comparable<VocabularyDTO> {
    private int id;
    private String topic;
    private String description;
    private ImageDTO image;

    public VocabularyDTO() {

    }
    public VocabularyDTO(int id, String topic, String description, ImageDTO image) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    @Override
    public int hashCode() {
        return topic.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VocabularyDTO))
            return false;

        VocabularyDTO vocalulary = (VocabularyDTO) obj;
        return vocalulary.topic.equals(topic);
    }

    @Override
    public int compareTo(VocabularyDTO vocabularyDTO) {
        return getId() - vocabularyDTO.getId();
    }
}
