package com.example.tiengviet1.dto;

import java.io.Serializable;

public class QuizDTO implements Serializable, Comparable<QuizDTO> {

    private int id;
    private String quizType;
    private String question;
    private String arrAnswer;
    private String rightAnswer;
    private ImageDTO img;


    public QuizDTO(int id, String quizType, String question, String arrAnswer, String rightAnswer, ImageDTO img) {
        this.id = id;
        this.quizType = quizType;
        this.question = question;
        this.arrAnswer = arrAnswer;
        this.rightAnswer = rightAnswer;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getArrAnswer() {
        return arrAnswer;
    }

    public void setArrAnswer(String arrAnswer) {
        this.arrAnswer = arrAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public ImageDTO getImg() {
        return img;
    }

    public void setImg(ImageDTO img) {
        this.img = img;
    }

    public QuizDTO() {
    }



    @Override
    public int hashCode() {
        return quizType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuizDTO))
            return false;

        QuizDTO quiz = (QuizDTO) obj;
        return quiz.quizType.equals(quizType);
    }

    @Override
    public int compareTo(QuizDTO quizDTO) {
        return getId() - quizDTO.getId();
    }
}
