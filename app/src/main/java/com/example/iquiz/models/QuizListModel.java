package com.example.iquiz.models;

import com.google.firebase.firestore.DocumentId;

public class QuizListModel {
    @DocumentId
    private String quiz_id;
    private String name,desc,image,level,visibily;
    private long quesitons;

    public QuizListModel(){

    }
    public QuizListModel(String quiz_id, String name, String desc, String image, String level, String visibily, long quesitons) {
        this.quiz_id = quiz_id;
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.level = level;
        this.visibily = visibily;
        this.quesitons = quesitons;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVisibily() {
        return visibily;
    }

    public void setVisibily(String visibily) {
        this.visibily = visibily;
    }

    public long getQuesitons() {
        return quesitons;
    }

    public void setQuesitons(long quesitons) {
        this.quesitons = quesitons;
    }
}
